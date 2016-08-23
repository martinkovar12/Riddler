package martinkovar12.riddler.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PersistenceService
{
	protected static final SimpleDateFormat s_simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	@NonNull
	public <T> List<T> query(Context context, Class<T> clazz, String whereClause, String[] whereArgs)
	{
		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getReadableDatabase();

		String tableName = getTableName(clazz);
		Field[] fields = ClassHelper.getAllDeclaredFields(clazz);
		String sql = createSelectStatement(tableName, fields) + whereClause;
		Cursor cursor = database.rawQuery(sql, whereArgs);
		List<T> list = new ArrayList<>();

		try
		{
			while (cursor.moveToNext())
			{
				T entity = instantiateNewEntity(clazz);
				for (Field field : fields)
				{
					if (field.isAnnotationPresent(Column.class))
					{
						setField(cursor, entity, field);
					}
				}

				list.add(entity);
			}
		}
		finally
		{
			cursor.close();
		}

		return list;
	}


	@NonNull
	protected String createSelectStatement(String tableName, Field[] fields)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");

		for (Field field : fields)
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				sb.append("it.").append(columnName).append(",");
			}
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append(" FROM ").append(tableName).append(" AS it ");
		return sb.toString();
	}

	@NonNull
	protected <T> T instantiateNewEntity(Class<T> clazz)
	{
		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		throw new IllegalStateException("Unable to instantiate new entity: " + clazz.getSimpleName());
	}

	protected void setField(Cursor cursor, Object entity, Field field)
	{
		final Column column = field.getAnnotation(Column.class);
		final String columnName = column.name();
		final int columnIndex = cursor.getColumnIndexOrThrow(columnName);
		final Class<?> fieldType = field.getType();
		Object fieldValue = null;

		if (fieldType.equals(int.class) || fieldType.equals(Integer.class))
		{
			fieldValue = cursor.getInt(columnIndex);
		}
		else if (fieldType.equals(boolean.class) || fieldType.equals(Boolean.class))
		{
			fieldValue = cursor.getInt(columnIndex) == 1;
		}
		else if (fieldType.equals(String.class))
		{
			fieldValue = cursor.getString(columnIndex);
		}
		else if (fieldType.equals(Date.class))
		{
			try
			{
				fieldValue = s_simpleDateFormat.parse(cursor.getString(columnIndex));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			throw new IllegalStateException("Column '" + columnName + "' has invalid type: " + fieldType);
		}

		try
		{
			field.set(entity, fieldValue);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}

	public long insert(Context context, BaseEntity baseEntity)
	{
		Date now = new Date();
		baseEntity.setModifiedOn(now);
		baseEntity.setInsertedOn(now);

		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();

		String tableName = getTableName(baseEntity);
		ContentValues values = getValues(baseEntity);
		return database.insert(tableName, null, values);
	}

	public long update(Context context, BaseEntity baseEntity)
	{
		Date now = new Date();
		baseEntity.setModifiedOn(now);

		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();

		String tableName = getTableName(baseEntity);
		ContentValues values = getValues(baseEntity);
		String whereClause = BaseColumns._ID + " = ?";
		String[] whereArgs = {String.valueOf(baseEntity.getId())};
		return database.update(tableName, values, whereClause, whereArgs);
	}

	@NonNull
	protected String getTableName(BaseEntity baseEntity)
	{
		return getTableName(baseEntity.getClass());
	}

	@NonNull
	protected String getTableName(Class clazz)
	{
		Table table = (Table) clazz.getAnnotation(Table.class);
		return table.name();
	}

	@NonNull
	protected ContentValues getValues(BaseEntity baseEntity)
	{
		ContentValues values = new ContentValues();
		Field[] fields = ClassHelper.getAllDeclaredFields(baseEntity.getClass());
		for (Field field : fields)
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String name = column.name();
				if (BaseColumns._ID.equals(name))
				{
					continue; // ID neupdatujeme.
				}

				Object value = getValue(baseEntity, field);
				Class<?> type = field.getType();
				if (type.equals(int.class))
				{
					values.put(name, (int) value);
				}
				else if (type.equals(Integer.class))
				{
					values.put(name, (Integer) value);
				}
				else if (type.equals(boolean.class))
				{
					values.put(name, (boolean) value ? 1 : 0);
				}
				else if (type.equals(Boolean.class))
				{
					values.put(name, (Boolean) value ? 1 : 0);
				}
				else if (type.equals(String.class))
				{
					values.put(name, (String) value);
				}
				else if (type.equals(Date.class))
				{
					values.put(name, s_simpleDateFormat.format((Date) value));
				}
				else
				{
					throw new IllegalStateException("Column '" + name + "' has unsupported type: " + type);
				}
			}
		}
		return values;
	}

	protected Object getValue(BaseEntity baseEntity, Field field)
	{
		try
		{
			field.setAccessible(true);
			return field.get(baseEntity);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}

		throw new IllegalStateException("Unable to get value.");
	}
}
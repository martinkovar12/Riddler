package martinkovar12.riddler.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PersistenceService
{
	private static final SimpleDateFormat s_simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

	public void query(Context context, Class<? extends BaseEntity> clazz)
	{
		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = database.rawQuery();
	}

	public long insert(Context context, BaseEntity baseEntity)
	{
		Date now = new Date();
		baseEntity.setModifiedOn(now);
		baseEntity.setInsertedOn(now);

		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		String table = getTable(baseEntity);
		ContentValues values = getValues(baseEntity);
		return database.insert(table, null, values);
	}

	public long update(Context context, BaseEntity baseEntity)
	{
		Date now = new Date();
		baseEntity.setModifiedOn(now);

		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		String table = getTable(baseEntity);
		ContentValues values = getValues(baseEntity);
		String whereClause = BaseColumns._ID + " = ?";
		String[] whereArgs = {String.valueOf(baseEntity.getId())};
		return database.update(table, values, whereClause, whereArgs);
	}

	protected String getTable(BaseEntity baseEntity)
	{
		Class<? extends BaseEntity> clazz = baseEntity.getClass();
		Table table = clazz.getAnnotation(Table.class);
		return table.name();
	}

	protected ContentValues getValues(BaseEntity baseEntity)
	{
		ContentValues values = new ContentValues();
		Class<? extends BaseEntity> clazz = baseEntity.getClass();
		for (Field field : clazz.getDeclaredFields())
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
					values.put(name, (boolean) value);
				}
				else if (type.equals(Boolean.class))
				{
					values.put(name, (Boolean) value);
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
					throw new IllegalStateException("Column '" + name + "' has invalid type: " + type);
				}
			}
		}
		return values;
	}

	protected String[] getProjection(Class<? extends BaseEntity> clazz)
	{
		List<String> list = new ArrayList<>();
		for (Field field : clazz.getDeclaredFields())
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				list.add(column.name());
			}
		}

		String[] projection = new String[list.size()];
		return list.toArray(projection);
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

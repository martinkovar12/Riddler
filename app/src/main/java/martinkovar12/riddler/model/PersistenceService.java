package martinkovar12.riddler.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class PersistenceService
{
	public long insert(Context context, BaseEntity baseEntity)
	{
		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(context);
		SQLiteDatabase database = helper.getWritableDatabase();
		ContentValues values = createContentValues(baseEntity);
		return database.insert(, null, values);
	}

	protected String createName(BaseEntity baseEntity)
	{
		Class<? extends BaseEntity> clazz = baseEntity.getClass();
		Table table = clazz.getAnnotation(Table.class);
		return table.name();
	}

	protected ContentValues createContentValues(BaseEntity baseEntity)
	{
		ContentValues values = new ContentValues();
		Class<? extends BaseEntity> clazz = baseEntity.getClass();
		for (Field field : clazz.getDeclaredFields())
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String name = column.name();
				Class<?> type = field.getType();
				if (BaseColumns._ID.equals(name))
				{
				}
				else if (type.equals(int.class) || type.equals(Integer.class) || type.equals(boolean.class) || type.equals(Boolean.class))
				{
				}
				else if (type.equals(String.class))
				{
				}
				else if (type.equals(BigDecimal.class))
				{
				}
				else
				{
					throw new IllegalStateException("Column '" + name + "' has invalid type: " + type);
				}
			}
		}
		return values;
	}
}

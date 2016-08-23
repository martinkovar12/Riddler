package martinkovar12.riddler.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

public class BaseSQLiteOpenHelper extends SQLiteOpenHelper
{
	//region Constants
	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "Data.db";
	//endregion

	//region Constructors
	public BaseSQLiteOpenHelper(Context context)
	{
		super(context, DB_NAME, null, DB_VERSION);
	}
	//endregion

	//region Implementations
	public void onCreate(SQLiteDatabase db)
	{
		for (Class clazz : getEntityClasses())
		{
			db.execSQL(getCreateTableStatement(clazz));
		}
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		for (Class clazz : getEntityClasses())
		{
			db.execSQL(getDropTableStatement(clazz));
		}

		onCreate(db);
	}
	//endregion

	//region Methods
	protected Class[] getEntityClasses()
	{
		return new Class[] {TeamEntity.class};
	}

	protected String getCreateTableStatement(Class clazz)
	{
		Table table = (Table) clazz.getAnnotation(Table.class);
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(table.name()).append(" (");

		appendColumns(clazz, sb);

		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

	protected void appendColumns(Class clazz, StringBuilder sb)
	{
		Field[] fields = ClassHelper.getAllDeclaredFields(clazz);
		for (Field field : fields)
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String name = column.name();
				sb.append(name);
				if (BaseColumns._ID.equals(name))
				{
					sb.append(" INTEGER PRIMARY KEY,");
					continue;
				}

				Class<?> type = field.getType();
				if (type.equals(int.class) || type.equals(Integer.class)
						|| type.equals(boolean.class) || type.equals(Boolean.class))
				{
					sb.append(" INTEGER,");
				}
				else if (type.equals(String.class) || type.equals(Date.class))
				{
					sb.append(" TEXT,");
				}
				else if (type.equals(BigDecimal.class))
				{
					sb.append(" REAL,");
				}
				else
				{
					throw new IllegalStateException("Column '" + name + "' has unsupported type: " + type);
				}
			}
		}
	}

	protected String getDropTableStatement(Class clazz)
	{
		Table table = (Table) clazz.getAnnotation(Table.class);
		return "DROP TABLE IF EXISTS " + table.name();
	}
	//endregion
}
package martinkovar12.riddler.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;

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
	protected Iterable<Class> getEntityClasses()
	{
		ArrayList<Class> classes = new ArrayList<>();
		classes.add(TeamEntity.class);
		return classes;
	}

	protected String getCreateTableStatement(Class clazz)
	{
		Table table = (Table) clazz.getAnnotation(Table.class);
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(table.name()).append(" (");

		while (clazz != null)
		{
			addColumns(clazz, sb);
			clazz = clazz.getSuperclass();
		}

		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}

	protected void addColumns(Class clazz, StringBuilder sb)
	{
		for (Field field : clazz.getDeclaredFields())
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String name = column.name();
				Class<?> type = field.getType();
				sb.append(name);
				if (BaseColumns._ID.equals(name))
				{
					sb.append(" INTEGER PRIMARY KEY,");
				}
				else if (type.equals(int.class) || type.equals(Integer.class) || type.equals(boolean.class) || type.equals(Boolean.class))
				{
					sb.append(" INTEGER,");
				}
				else if (type.equals(String.class))
				{
					sb.append(" TEXT,");
				}
				else if (type.equals(BigDecimal.class))
				{
					sb.append(" REAL,");
				}
				else
				{
					throw new IllegalStateException("Column '" + name + "' has invalid type: " + type);
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
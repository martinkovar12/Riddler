package martinkovar12.riddler;

import android.provider.BaseColumns;
import android.text.TextUtils;

import org.junit.Test;

import java.lang.reflect.Field;

import martinkovar12.riddler.model.Column;
import martinkovar12.riddler.model.Table;
import martinkovar12.riddler.model.TeamEntity;

public class TeamEntityTest
{
	private static final String INT = " INTEGER";
	private static final String REA = " REAL";
	private static final String TXT = " TEXT";
	private static final String SQL_CRE_TAB = "CREATE TABLE ";
	private static final String SQL_DRO_TAB = "DROP TABLE IF EXISTS ";
	private static final String SQL_SYS_COLS = " PRIMARY KEY,";

	@Test
	public void testFields()
	{
		createCreateTableStatement(TeamEntity.class);
	}

	public String createCreateTableStatement(Class clazz)
	{
		Table table = (Table) clazz.getAnnotation(Table.class);
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ")
				.append(table.name())
				.append(" (");

		for (Field field : clazz.getSuperclass().getDeclaredFields())
		{
			if (field.isAnnotationPresent(Column.class))
			{
				Column column = field.getAnnotation(Column.class);
				String name = column.name();
				sb.append(name);
				if (TextUtils.equals(name, BaseColumns._ID))
				{
					sb.append(INT).append(" PRIMARY KEY,");
				}
			}
		}

		sb.append(")");
		return sb.toString();
	}
}
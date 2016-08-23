package martinkovar12.riddler.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassHelper
{
	public static Field[] getAllDeclaredFields(Class clazz)
	{
		List<Field> fields = new ArrayList<>();
		while (clazz != null)
		{
			addDeclaredFields(clazz, fields);
			clazz = clazz.getSuperclass();
		}

		return fields.toArray(new Field[fields.size()]);
	}

	private static void addDeclaredFields(Class clazz, List<Field> fields)
	{
		Collections.addAll(fields, clazz.getDeclaredFields());
	}
}
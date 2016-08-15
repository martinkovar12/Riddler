package martinkovar12.riddler;

import org.junit.Test;

import java.lang.reflect.Field;

import martinkovar12.riddler.model.TeamEntity;

public class TeamEntityTest
{
	@Test
	public void testFields()
	{
		for (Field field : TeamEntity.class.getFields())
		{
			int a = 10;
		}
	}
}
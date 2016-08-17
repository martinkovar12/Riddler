package martinkovar12.riddler.model;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaseSQLiteOpenHelperTest
{
	@Mock
	Context m_context;

	@Test
	public void test()
	{
		BaseSQLiteOpenHelper helper = new BaseSQLiteOpenHelper(m_context);
		for (Class clazz : helper.getEntityClasses())
		{
			String createTableStatement = helper.getCreateTableStatement(clazz);
			String dropTableStatement = helper.getDropTableStatement(clazz);
		}
	}
}

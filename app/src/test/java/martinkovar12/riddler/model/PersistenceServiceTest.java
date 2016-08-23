package martinkovar12.riddler.model;

import android.content.ContentValues;

import org.junit.Test;

import java.util.Date;

public class PersistenceServiceTest
{
	@Test
	public void getTableName()
	{
		PersistenceService persistenceService = new PersistenceService();
		TeamEntity teamEntity = new TeamEntity();
		String tableName = persistenceService.getTableName(teamEntity);
	}

	@Test
	public void getValues()
	{
		PersistenceService persistenceService = new PersistenceService();
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setGameId(10);
		teamEntity.setScore(100);
		teamEntity.setOnTurn(true);
		Date now = new Date();
		teamEntity.setModifiedOn(now);
		teamEntity.setInsertedOn(now);
		ContentValues values = persistenceService.getValues(teamEntity);
	}
}
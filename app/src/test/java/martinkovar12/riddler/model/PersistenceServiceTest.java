package martinkovar12.riddler.model;

import org.junit.Test;

public class PersistenceServiceTest
{
	@Test
	public void testGetName()
	{
		PersistenceService persistenceService = new PersistenceService();
		TeamEntity teamEntity = new TeamEntity();
		persistenceService.getTable(teamEntity);
	}

	@Test
	public void testContentValues()
	{
		PersistenceService persistenceService = new PersistenceService();
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setGameId(10);
		teamEntity.setScore(100);
		teamEntity.setOnTurn(true);
		persistenceService.getValues(teamEntity);
	}
}
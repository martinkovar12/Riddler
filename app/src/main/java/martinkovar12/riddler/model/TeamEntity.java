package martinkovar12.riddler.model;

@Table(name = "Team")
public class TeamEntity extends BaseEntity
{
	//region Fields
	@Column(name = "gameId")
	private int m_gameId;
	@Column(name = "score")
	private int m_score;
	@Column(name = "onTurn")
	private boolean m_onTurn;
	//endregion

	//region Getters
	public int getGameId()
	{
		return m_gameId;
	}

	public int getScore()
	{
		return m_score;
	}

	public boolean isOnTurn()
	{
		return m_onTurn;
	}
	//endregion
}
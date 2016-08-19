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

	//region Properties
	public int getGameId()
	{
		return m_gameId;
	}

	public void setGameId(int gameId)
	{
		m_gameId = gameId;
	}

	public int getScore()
	{
		return m_score;
	}

	public void setScore(int score)
	{
		m_score = score;
	}

	public boolean isOnTurn()
	{
		return m_onTurn;
	}

	public void setOnTurn(boolean onTurn)
	{
		m_onTurn = onTurn;
	}
	//endregion
}
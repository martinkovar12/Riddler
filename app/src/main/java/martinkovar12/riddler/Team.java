package martinkovar12.riddler;

public class Team
{
	private int m_id;
	private int m_position;
	private int m_score;
	private boolean m_onTurn;

	public Team(int id, int position, int score, boolean onTurn)
	{
		m_id = id;
		m_position = position;
		m_score = score;
		m_onTurn = onTurn;
	}

	public int getId()
	{
		return m_id;
	}

	public int getPosition()
	{
		return m_position;
	}

	public int getScore()
	{
		return m_score;
	}

	public boolean isOnTurn()
	{
		return m_onTurn;
	}
}
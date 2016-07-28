package martinkovar12.riddler;

public class Team {
    private int m_id;
    private int m_position;
    private boolean m_onTurn;

    public Team(int m_id, int m_position, boolean m_onTurn) {
        this.m_id = m_id;
        this.m_position = m_position;
        this.m_onTurn = m_onTurn;
    }

    public int getId() {
        return m_id;
    }

    public int getPosition() {
        return m_position;
    }

    public boolean isOnTurn() {
        return m_onTurn;
    }
}
package martinkovar12.riddler.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TeamEntity extends BaseEntity implements Parcelable
{
	//region Constants
	public static final Parcelable.Creator<TeamEntity> CREATOR = new Parcelable.Creator<TeamEntity>()
	{
		@Override
		public TeamEntity createFromParcel(Parcel source)
		{
			return new TeamEntity(source);
		}

		@Override
		public TeamEntity[] newArray(int size)
		{
			return new TeamEntity[size];
		}
	};
	//endregion

	//region Fields
	private int m_gameId;
	private int m_id;
	private int m_position;
	private int m_score;
	private boolean m_onTurn;
	//endregion

	//region Constructors
	public TeamEntity(int id, int position, int score, boolean onTurn)
	{
		m_id = id;
		m_position = position;
		m_score = score;
		m_onTurn = onTurn;
	}
	//endregion

	//region Parcelable
	public TeamEntity(Parcel in)
	{
		String[] data = new String[4];
		in.readStringArray(data);

		m_id = Integer.parseInt(data[0]);
		m_position = Integer.parseInt(data[1]);
		m_score = Integer.parseInt(data[2]);
		m_onTurn = Boolean.parseBoolean(data[3]);
	}

	//region Getters
	public int getGameId()
	{
		return m_gameId;
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
	//endregion

	public boolean isOnTurn()
	{
		return m_onTurn;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i)
	{
		parcel.writeStringArray(new String[] {String.valueOf(m_id), String.valueOf(m_position), String.valueOf(m_score), String.valueOf(m_onTurn)});
	}
	//endregion
}
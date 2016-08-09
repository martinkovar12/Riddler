package martinkovar12.riddler;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable
{
	//region Nested Classes
	public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>()
	{
		@Override
		public Team createFromParcel(Parcel source)
		{
			return new Team(source);
		}

		@Override
		public Team[] newArray(int size)
		{
			return new Team[size];
		}
	};
	//region Fields
	private int m_id;
	private int m_position;
	private int m_score;
	//endregion
	private boolean m_onTurn;
	//endregion

	//region Constructors
	public Team(int id, int position, int score, boolean onTurn)
	{
		m_id = id;
		m_position = position;
		m_score = score;
		m_onTurn = onTurn;
	}

	//region Parcelable
	public Team(Parcel in)
	{
		String[] data = new String[4];
		in.readStringArray(data);

		m_id = Integer.parseInt(data[0]);
		m_position = Integer.parseInt(data[1]);
		m_score = Integer.parseInt(data[2]);
		m_onTurn = Boolean.parseBoolean(data[3]);
	}

	//region Getters
	public int getId()
	{
		return m_id;
	}

	public int getPosition()
	{
		return m_position;
	}
	//endregion

	public int getScore()
	{
		return m_score;
	}

	public boolean isOnTurn()
	{
		return m_onTurn;
	}

	@Override
	public int describeContents()
	{
		return 0;
	}
	//endregion

	@Override
	public void writeToParcel(Parcel parcel, int i)
	{
		parcel.writeStringArray(new String[] {String.valueOf(m_id), String.valueOf(m_position), String.valueOf(m_score), String.valueOf(m_onTurn)});
	}
	//endregion
}
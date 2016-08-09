package martinkovar12.riddler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import martinkovar12.riddler.R;
import martinkovar12.riddler.Team;

public class Riddle extends AppCompatActivity
{
	public static final String ParameterName_Team = "Team";

	private Team m_team;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riddle);

		Intent intent = getIntent();
		Parcelable parcelable = intent.getParcelableExtra(ParameterName_Team);
		if (parcelable instanceof Team)
		{
			m_team = (Team) parcelable;
		}
		else
		{
			throw new IllegalStateException("Team not found.");
		}
	}
}

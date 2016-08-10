package martinkovar12.riddler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import martinkovar12.riddler.R;
import martinkovar12.riddler.Team;
import martinkovar12.riddler.ui.CountDownTimerTextView;

public class Riddle extends AppCompatActivity
{
	public static final String ParameterName_Team = "Team";

	private Team m_team;
	private boolean m_preparationInProgress;

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

		final ImageView riddleTypeImageView = (ImageView) findViewById(R.id.activity_riddle_riddle_type);
		final TextView riddleTextView = (TextView) findViewById(R.id.activity_riddle_riddle);
		final TextView timerLabelTextView = (TextView) findViewById(R.id.activity_riddle_timer_label);
		final CountDownTimerTextView timerTextView = (CountDownTimerTextView) findViewById(R.id.activity_riddle_timer);

		m_preparationInProgress = true;

		riddleTextView.setText(getResources().getText(R.string.riddle));
		timerLabelTextView.setText(getResources().getText(R.string.preparation));
		timerTextView.start(15000);
		timerTextView.setOnFinishedListener(new CountDownTimerTextView.OnFinishedListener()
		{
			@Override
			public void onFinished(CountDownTimerTextView countDownTimerTextView)
			{
				if (m_preparationInProgress)
				{
					m_preparationInProgress = false;
					timerLabelTextView.setText(getResources().getText(R.string.riddle));
					timerTextView.start(30000);
				}
				else
				{
					//TODO
				}
			}
		});
	}
}

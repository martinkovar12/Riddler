package martinkovar12.riddler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import martinkovar12.riddler.R;
import martinkovar12.riddler.Team;
import martinkovar12.riddler.ui.CountDownTimerTextView;

public class Riddle extends AppCompatActivity
{
	public static final String ParameterName_Team = "Team";

	private Team m_team;
	private TextView m_timerLabelTextView;
	private CountDownTimerTextView m_timerTextView;
	private Button m_skipButton;
	private Button m_successButton;
	private Button m_failButton;

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
		riddleTextView.setText(getResources().getText(R.string.riddle));

		m_timerLabelTextView = (TextView) findViewById(R.id.activity_riddle_timer_label);
		m_timerLabelTextView.setText(getResources().getText(R.string.preparation));

		m_timerTextView = (CountDownTimerTextView) findViewById(R.id.activity_riddle_timer);
		m_timerTextView.start(15000);
		m_timerTextView.setOnFinishedListener(new CountDownTimerTextView.OnFinishedListener()
		{
			@Override
			public void onFinished(CountDownTimerTextView countDownTimerTextView)
			{
				skipPreparation();
			}
		});

		m_skipButton = (Button) findViewById(R.id.activity_riddle_skip);
		m_successButton = (Button) findViewById(R.id.activity_riddle_success);
		m_failButton = (Button) findViewById(R.id.activity_riddle_fail);
	}

	public void onClickSkip(View view)
	{
		skipPreparation();
	}

	public void onClickSuccess(View view)
	{
	}

	public void onClickFail(View view)
	{
	}

	private void skipPreparation()
	{
		m_timerLabelTextView.setText(getResources().getText(R.string.riddle));
		m_timerTextView.start(30000);
		m_timerTextView.removeOnFinishedListener();
		m_timerTextView.setOnFinishedListener(new CountDownTimerTextView.OnFinishedListener()
		{
			@Override
			public void onFinished(CountDownTimerTextView countDownTimerTextView)
			{
			}
		});
		m_skipButton.setVisibility(View.GONE);
		m_successButton.setVisibility(View.VISIBLE);
		m_failButton.setVisibility(View.VISIBLE);
	}
}

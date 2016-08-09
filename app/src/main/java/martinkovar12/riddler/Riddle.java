package martinkovar12.riddler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Riddle extends AppCompatActivity
{
	public static final String ParameterName_Team = "Team";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_riddle);

		Object o = savedInstanceState.get(ParameterName_Team);
	}
}

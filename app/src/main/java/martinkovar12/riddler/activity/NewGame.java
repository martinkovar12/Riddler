package martinkovar12.riddler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import martinkovar12.riddler.R;

public class NewGame extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_new_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void start(View view)
	{
		EditText numberOfTeamsEditText = (EditText) findViewById(R.id.activity_new_game_number_of_teams);
		Intent intent = new Intent(this, Game.class);
		intent.putExtra(Game.ParameterName_NumberOfTeams, Integer.parseInt(numberOfTeamsEditText.getText().toString()));
		startActivity(intent);
	}
}
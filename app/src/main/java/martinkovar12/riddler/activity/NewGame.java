package martinkovar12.riddler.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import martinkovar12.riddler.R;
import martinkovar12.riddler.sql.RiddlerContract;
import martinkovar12.riddler.sql.RiddlerDbHelper;

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
		RiddlerDbHelper dbHelper = new RiddlerDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = RiddlerContract.createInsertSystemContentValues("Game");
		long gameId = db.insert(RiddlerContract.Game.TAB_NAME, null, values);

		for (int i = 1; i <= getNumberOfTeams(); i++)
		{
			values = RiddlerContract.createInsertSystemContentValues("Team " + i);
			values.put(RiddlerContract.Team.COL_GAME_ID, gameId);
			values.put(RiddlerContract.Team.COL_SCORE, 0);
			values.put(RiddlerContract.Team.COL_IS_ON_TURN, i == 1 ? 1 : 0); // First team is on turn.
			db.insert(RiddlerContract.Team.TAB_NAME, null, values);
		}

		Intent intent = new Intent(this, Game.class);
		intent.putExtra(Game.ParameterName_GameId, gameId);
		startActivity(intent);
	}

	private int getNumberOfTeams()
	{
		EditText numberOfTeamsEditText = (EditText) findViewById(R.id.activity_new_game_number_of_teams);
		return Integer.parseInt(numberOfTeamsEditText.getText().toString());
	}
}
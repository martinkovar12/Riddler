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
		EditText numberOfTeamsEditText = (EditText) findViewById(R.id.activity_new_game_number_of_teams);
		int numberOfTeams = Integer.parseInt(numberOfTeamsEditText.getText().toString());

		RiddlerDbHelper dbHelper = new RiddlerDbHelper(this);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = RiddlerContract.createInsertSystemContentValues("A Team");
		long teamId = db.insert(RiddlerContract.Team.TAB_NAME, null, values);

		Intent intent = new Intent(this, Game.class);
		intent.putExtra(Game.ParameterName_NumberOfTeams, numberOfTeams);
		startActivity(intent);
	}
}
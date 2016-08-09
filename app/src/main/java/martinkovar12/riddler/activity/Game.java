package martinkovar12.riddler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import martinkovar12.riddler.R;
import martinkovar12.riddler.Team;
import martinkovar12.riddler.TeamsAdapter;

public class Game extends AppCompatActivity
{
	public static final String ParameterName_NumberOfTeams = "NumberOfTeams";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_game_teams);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		RecyclerView.Adapter adapter = new TeamsAdapter(this, createTeams());
		recyclerView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_game, menu);
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

	private List<Team> createTeams()
	{
		Intent intent = getIntent();
		int numberOfTeams = intent.getIntExtra(ParameterName_NumberOfTeams, 0);

		List<Team> teams = new ArrayList<>(numberOfTeams);
		for (int i = 0; i < numberOfTeams; i++)
		{
			if (i == 0)
			{
				teams.add(new Team(i, 0, 0, true));
			}
			else
			{
				teams.add(new Team(i, 0, 0, false));
			}
		}
		return teams;
	}
}
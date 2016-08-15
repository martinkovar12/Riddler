package martinkovar12.riddler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import martinkovar12.riddler.activity.Riddle;
import martinkovar12.riddler.model.TeamEntity;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder>
{
	private Context m_context;
	private List<TeamEntity> m_teams;
	private LayoutInflater m_inflater;


	public TeamsAdapter(Context context, List<TeamEntity> teams)
	{
		m_context = context;
		m_teams = teams;
		m_inflater = LayoutInflater.from(m_context);
	}

	@Override
	public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View v = m_inflater.inflate(R.layout.team_item, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		final TeamEntity team = m_teams.get(position);

		holder.m_nameTextView.setText(String.valueOf(team.getId()));
		holder.m_positionTextView.setText(String.valueOf(team.getScore()));
		holder.m_scoreTextView.setText(String.valueOf(team.getScore()));

		if (team.isOnTurn())
		{
			holder.m_startButton.setVisibility(View.VISIBLE);
			holder.m_startButton.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View view)
				{
					Intent intent = new Intent(m_context, Riddle.class);
					//intent.putExtra(Riddle.ParameterName_Team, team);
					m_context.startActivity(intent);
				}
			});
		}
		else
		{
			holder.m_startButton.setVisibility(View.GONE);
		}
	}

	@Override
	public int getItemCount()
	{
		return m_teams.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder
	{
		public TextView m_nameTextView;
		public TextView m_positionTextView;
		public TextView m_scoreTextView;
		public Button m_startButton;

		public ViewHolder(View v)
		{
			super(v);
			m_nameTextView = (TextView) v.findViewById(R.id.team_item_name);
			m_positionTextView = (TextView) v.findViewById(R.id.team_item_position);
			m_scoreTextView = (TextView) v.findViewById(R.id.team_item_score);
			m_startButton = (Button) v.findViewById(R.id.team_item_start);
		}
	}
}
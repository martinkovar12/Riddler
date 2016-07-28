package martinkovar12.riddler;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private List<Team> m_teams;

    public TeamsAdapter(List<Team> teams) {
        m_teams = teams;
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = m_teams.get(position);

        holder.m_idTextView.setText(String.valueOf(team.getId()));
        holder.m_positionTextView.setText(String.valueOf(team.getPosition()));

        if (team.isOnTurn()) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return m_teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView m_idTextView;
        public TextView m_positionTextView;
        public TextView m_onTurnTextView;

        public ViewHolder(View v) {
            super(v);
            m_idTextView = (TextView) v.findViewById(R.id.team_item_id);
            m_positionTextView = (TextView) v.findViewById(R.id.team_item_position);
            m_onTurnTextView = (TextView) v.findViewById(R.id.team_item_on_turn);
        }
    }
}
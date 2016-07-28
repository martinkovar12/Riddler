package martinkovar12.riddler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TeamsAdapter  extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private List<Team> m_teams;

    public TeamsAdapter(List<Team> teams) {
        m_teams = teams;
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_text_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(m_teams.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return m_teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}

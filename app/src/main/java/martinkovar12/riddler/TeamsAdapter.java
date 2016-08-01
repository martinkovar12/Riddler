package martinkovar12.riddler;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {
    private Context m_context;
    private List<Team> m_teams;
    private LayoutInflater m_inflater;


    public TeamsAdapter(Context context, List<Team> teams) {
        m_context = context;
        m_teams = teams;
        m_inflater = LayoutInflater.from(m_context);
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = m_inflater.inflate(R.layout.team_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Team team = m_teams.get(position);

        holder.m_idTextView.setText(String.valueOf(team.getId()));
        holder.m_positionTextView.setText(String.valueOf(team.getPosition()));

        if (team.isOnTurn()) {
            holder.m_startCompoundButton.setEnabled(true);
        } else {
            holder.m_startCompoundButton.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return m_teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View m_expander;
        public int m_expanderOrigHeight;
        public int m_expanderCurrHeight;
        public TextView m_idTextView;
        public TextView m_positionTextView;
        public CompoundButton m_startCompoundButton;
        public TextView m_prepTextView;
        public Button m_prepSkipButton;
        public TextView m_actTextView;
        public Button m_actFailButton;
        public Button m_actSuccButton;

        public ViewHolder(View v) {
            super(v);
            m_expander = v.findViewById(R.id.team_item_expander);
            m_expanderOrigHeight = m_expander.getHeight();
            m_expanderCurrHeight = m_expander.getHeight();
            m_idTextView = (TextView) v.findViewById(R.id.team_item_id);
            m_positionTextView = (TextView) v.findViewById(R.id.team_item_position);
            m_startCompoundButton = (CompoundButton) v.findViewById(R.id.team_item_start);
            m_prepTextView = (TextView) v.findViewById(R.id.team_item_preparation);
            m_prepSkipButton = (Button) v.findViewById(R.id.team_item_preparation_skip);
            m_actTextView = (TextView) v.findViewById(R.id.team_item_activity);
            m_actFailButton = (Button) v.findViewById(R.id.team_item_activity_fail);
            m_actSuccButton = (Button) v.findViewById(R.id.team_item_activity_succes);

            m_startCompoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (m_expanderOrigHeight == 0) {
                        m_expanderOrigHeight = m_expander.getHeight();
                    }

                    if (checked) {
                        animate(m_expanderOrigHeight, 2 * m_expanderOrigHeight);
                    } else {
                        animate(m_expanderCurrHeight, m_expanderOrigHeight);
                    }
                }
            });

            m_prepSkipButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        private void animate(int oldValue, int newValue) {
            m_expanderCurrHeight = newValue;

            ValueAnimator animator = ValueAnimator.ofInt(oldValue, newValue);
            animator.setDuration(300);
            animator.setInterpolator(new LinearInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Object value = animation.getAnimatedValue();
                    if (value instanceof Integer) {
                        m_expander.getLayoutParams().height = (Integer) value;
                        m_expander.requestLayout();
                    }
                }
            });
            animator.start();
        }
    }
}
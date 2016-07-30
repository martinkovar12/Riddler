package martinkovar12.riddler;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> implements View.OnClickListener {

    private Context m_context;
    private List<Team> m_teams;
    private LayoutInflater m_inflater;
    private int m_originalHeight = 0;

    public TeamsAdapter(Context context, List<Team> teams) {
        m_context = context;
        m_teams = teams;
        m_inflater = LayoutInflater.from(m_context);
    }

    @Override
    public TeamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = m_inflater.inflate(R.layout.team_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.itemView.setOnClickListener(this);
        return viewHolder;
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

    @Override
    public void onClick(final View v) {
        if (m_originalHeight == 0) {
            m_originalHeight = v.getHeight();
        }

        ValueAnimator valueAnimator;
        if (v.getHeight() < (m_originalHeight + (int) (m_originalHeight * 1.5))) {
            valueAnimator = ValueAnimator.ofInt(m_originalHeight, m_originalHeight + (int) (m_originalHeight * 1.5));
        } else {
            valueAnimator = ValueAnimator.ofInt(m_originalHeight + (int) (m_originalHeight * 1.5), m_originalHeight);
        }
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.start();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View m_expander;
        public TextView m_idTextView;
        public TextView m_positionTextView;
        public TextView m_onTurnTextView;

        public ViewHolder(View v) {
            super(v);
            m_expander = v.findViewById(R.id.team_item_expander);
            m_idTextView = (TextView) v.findViewById(R.id.team_item_id);
            m_positionTextView = (TextView) v.findViewById(R.id.team_item_position);
            m_onTurnTextView = (TextView) v.findViewById(R.id.team_item_on_turn);
        }
    }

//    public static class ClickListener implements View.OnClickListener
//    {
//        @Override
//        public void onClick(View view) {
//            int itemPosition = mRecyclerView.getChildLayoutPosition(view);
//            String item = mList.get(itemPosition);
//            Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
//        }
//    }
}
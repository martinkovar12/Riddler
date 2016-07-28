package martinkovar12.riddler;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class Game extends ActionBarActivity {

    public static final String ExtraName_NumberOfTeams = "NumberOfTeams";


    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_adapter;
    private RecyclerView.LayoutManager m_layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        int numberOfTeams = intent.getIntExtra(ExtraName_NumberOfTeams, 0);

        m_recyclerView = (RecyclerView) findViewById(R.id.activity_game_teams);

        m_layoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_layoutManager);

        // specify an adapter (see also next example)
        m_adapter = new TeamsAdapter(myDataset);
        m_recyclerView.setAdapter(m_adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
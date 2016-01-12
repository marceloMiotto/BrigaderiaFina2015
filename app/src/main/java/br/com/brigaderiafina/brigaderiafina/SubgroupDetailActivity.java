package br.com.brigaderiafina.brigaderiafina;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class SubgroupDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private static String SUBGROUP_FLAVOURS = "subgrupoDetailFlavoursFragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgroup_detail);

       // toolbar = (Toolbar) findViewById(R.id.ToolBarId);
       // setSupportActionBar(toolbar);
       // Intent intent = getIntent();
       // String subgroupName = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
      //  toolbar.setTitle(subgroupName);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SubgroupDetailActivityFlavoursFragment fragment = new SubgroupDetailActivityFlavoursFragment();
        fragmentTransaction.add(R.id.subgroup_detail_fragment_container, fragment, SUBGROUP_FLAVOURS);
        fragmentTransaction.commit();

    Log.i(Constants.LOG_TAG,"Ok.. call activiy with subgroup");

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subgroup_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package br.com.brigaderiafina.brigaderiafina;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import br.com.brigaderiafina.brigaderiafina.AsyncTasks.FetchVersionTask;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;
import br.com.brigaderiafina.brigaderiafina.utils.MenuHandler;


public class MainActivity extends AppCompatActivity {

    public static String MAIN_LIST_VIEW = "MAIN_LIST_VIEW";
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.ToolBarId);
        setSupportActionBar(toolbar);

        /*
        ** Added from http://stackoverflow.com/questions/4776933/android-application-icon-not-showing-up
         */
        try {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }catch(RuntimeException e){
            Log.e(Constants.LOG_TAG,e.getMessage());
        }
        /*
        ** Added End
         */

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MainActivityFragment fragment = new MainActivityFragment();
        fragmentTransaction.add(R.id.main_fragment_container, fragment, MAIN_LIST_VIEW);
        fragmentTransaction.commit();


        /**
         * Update catalog if necessary
         */
        verifyCatalogVersion();

    }

    protected void verifyCatalogVersion() {
        FetchVersionTask fetchVersionTask = new FetchVersionTask();
        fetchVersionTask.execute(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.global_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        MenuHandler menuHandler = new MenuHandler(this,id);
        menuHandler.getActionMenuHandler();
        return super.onOptionsItemSelected(item);
    }
}

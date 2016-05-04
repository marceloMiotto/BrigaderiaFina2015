package br.com.brigaderiafina.brigaderiafina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class MenuInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_info_group);

        toolbar = (Toolbar) findViewById(R.id.appbarMenuInfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        String [] mMenuArray = getIntent().getStringArrayExtra(Constants.MENU_INFO_ARRAY);
        ListView rv = (ListView) findViewById(R.id.menu_info_list_view);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,R.layout.item_list_menu_info
                ,R.id.menuInfoTextView,mMenuArray);
        rv.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

package br.com.brigaderiafina.brigaderiafina;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.adapters.MenuInfo;
import br.com.brigaderiafina.brigaderiafina.adapters.MenuInfoAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class MenuInfoActivity extends AppCompatActivity {

    private MenuInfoAdapter mAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_info_group);

        toolbar = (Toolbar) findViewById(R.id.appbarMenuInfo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        ListView rv = (ListView) findViewById(R.id.menu_info_list_view);
        MenuInfo menuInfo = new MenuInfo();
        ArrayList<MenuInfo> m = menuInfo.getMenuInfo(getIntent().getIntExtra(Constants.MENU_INFO_ID,-1));
        mAdapter = new MenuInfoAdapter(this,m);
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

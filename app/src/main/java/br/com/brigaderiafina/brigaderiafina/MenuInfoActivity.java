package br.com.brigaderiafina.brigaderiafina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.adapters.EventsAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.MenuInfo;
import br.com.brigaderiafina.brigaderiafina.adapters.MenuInfoAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.Subgroups;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;
import br.com.brigaderiafina.brigaderiafina.utils.MenuHandler;

public class MenuInfoActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_info);

        RecyclerView rv = (RecyclerView) findViewById(R.id.menu_info_recyclerview);
        rv.setHasFixedSize(true);
        MenuInfo menuInfo = new MenuInfo();
        ArrayList<MenuInfo> m = menuInfo.getMenuInfo();
        mAdapter = new MenuInfoAdapter(this,m);
        rv.setAdapter(mAdapter);

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

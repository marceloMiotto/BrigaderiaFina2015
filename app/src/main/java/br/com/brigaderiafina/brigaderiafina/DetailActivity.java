package br.com.brigaderiafina.brigaderiafina;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class DetailActivity extends AppCompatActivity {

    String lineChoosen;
    private Toolbar toolbar;

    public static String DETAIL_VIEW = "DETAIL_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailActivityFragment fragment = new DetailActivityFragment();
        fragmentTransaction.add(R.id.detail_container, fragment, DETAIL_VIEW);
        fragmentTransaction.commit();

        lineChoosen = getIntent().getStringExtra(Constants.LINE_NAME);

        ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout)).setTitle(lineChoosen);
        ImageView imageView = (ImageView) findViewById(R.id.lineTopImageView);
        switch (lineChoosen) {
            case Constants.GOURMET_LINE:
                imageView.setImageResource(R.drawable.linha_gourmet);
                break;
            case Constants.CHOCOLAT_LINE:
                imageView.setImageResource(R.drawable.linha_chocolat);
                break;

            case Constants.DESSERT_LINE:
                imageView.setImageResource(R.drawable.linha_dessert);
                break;
            case Constants.SUGAR_LINE:
                imageView.setImageResource(R.drawable.linha_sugar);
                break;

            case Constants.CAKE_LINE:
                imageView.setImageResource(R.drawable.linha_cake);
                break;

            case Constants.EVENT_LINE:
                imageView.setImageResource(R.drawable.eventos);
                break;
            default:
                imageView.setImageResource(R.drawable.linha_gourmet);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

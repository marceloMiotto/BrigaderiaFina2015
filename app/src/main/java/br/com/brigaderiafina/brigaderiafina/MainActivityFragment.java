package br.com.brigaderiafina.brigaderiafina;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import br.com.brigaderiafina.brigaderiafina.adapters.ImageLinesAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.Lines;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;


public class MainActivityFragment extends Fragment {

    String lineChoosen;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<Lines> lines = Lines.getLines();
        ImageLinesAdapter adapter = new ImageLinesAdapter(getActivity(),lines);
        ListView listView = (ListView) view.findViewById(R.id.item_main_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                       lineChoosen = Constants.GOURMET_LINE;
                       break;
                    case 1:
                       lineChoosen = Constants.CHOCOLAT_LINE;
                       break;

                    case 2:
                       lineChoosen = Constants.DESSERT_LINE;
                       break;
                    case 3:
                       lineChoosen = Constants.SUGAR_LINE;
                       break;

                    case 4:
                       lineChoosen = Constants.CAKE_LINE;
                       break;

                    case 5:
                       lineChoosen = Constants.EVENT_LINE;
                       break;
                    default:
                        lineChoosen = Constants.GOURMET_LINE;
                        break;
                }

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constants.LINE_NAME, lineChoosen);
                Log.i(Constants.LOG_TAG, "Line Name " + lineChoosen + position);
                startActivity(intent);
            }
        });


        return view;
    }


}

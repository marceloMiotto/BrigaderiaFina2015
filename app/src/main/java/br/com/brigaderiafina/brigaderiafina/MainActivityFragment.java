package br.com.brigaderiafina.brigaderiafina;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import br.com.brigaderiafina.brigaderiafina.adapters.ImageLinesAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.Lines;


public class MainActivityFragment extends Fragment {

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
        return view;
    }
}

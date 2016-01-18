package br.com.brigaderiafina.brigaderiafina;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.adapters.Events;
import br.com.brigaderiafina.brigaderiafina.adapters.EventsAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.Subgroups;
import br.com.brigaderiafina.brigaderiafina.adapters.SubgroupsAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;


public class DetailActivityFragment extends Fragment{

    SubgroupsAdapter mSubgroups;
    String           mLineChoosen;
    private RecyclerView.Adapter mAdapter;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Intent intent = getActivity().getIntent();
        mLineChoosen = intent.getStringExtra(Constants.LINE_NAME);
        if(mLineChoosen.equals(Constants.EVENT_LINE)){
            //Card View
            Log.i(Constants.LOG_TAG, "Card View 01");
            rv.setHasFixedSize(true);
            mAdapter = new EventsAdapter(getDataSet());
            rv.setAdapter(mAdapter);


        }else{
            Subgroups sub = new Subgroups(getActivity());
            ArrayList<Subgroups> s = sub.getSubgroups(mLineChoosen);
            mSubgroups = new SubgroupsAdapter(getActivity(),s);
            rv.setAdapter(mSubgroups);
        }


        return view;
    }

    //Events sample data
    private ArrayList<Events> getDataSet() {
        ArrayList results = new ArrayList<Events>();
        for (int index = 0; index < 20; index++) {
            Events obj = new Events("Evento " + index,"Teste " + index);
            results.add(index, obj);
        }
        return results;
    }
}


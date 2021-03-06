package br.com.brigaderiafina.brigaderiafina;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.adapters.Events;
import br.com.brigaderiafina.brigaderiafina.adapters.EventsAdapter;
import br.com.brigaderiafina.brigaderiafina.adapters.Subgroups;
import br.com.brigaderiafina.brigaderiafina.adapters.SubgroupsAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;
import br.com.brigaderiafina.brigaderiafina.utils.DividerItemDecoration;


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
            rv.setHasFixedSize(true);
            Events eve = new Events(getActivity());
            mAdapter = new EventsAdapter(getActivity(),eve.getEvents());
            rv.setAdapter(mAdapter);

        }else{
            Subgroups sub = new Subgroups(getActivity());
            ArrayList<Subgroups> s = sub.getSubgroups(mLineChoosen);
            //Add item decoration
            rv.addItemDecoration(new DividerItemDecoration(getActivity()));
            mSubgroups = new SubgroupsAdapter(getActivity(),mLineChoosen,s);
            rv.setAdapter(mSubgroups);
        }


        return view;
    }

}


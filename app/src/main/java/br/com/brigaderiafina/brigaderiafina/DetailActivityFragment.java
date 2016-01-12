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

import br.com.brigaderiafina.brigaderiafina.adapters.Subgroups;
import br.com.brigaderiafina.brigaderiafina.adapters.subgroupsAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;


public class DetailActivityFragment extends Fragment{

    subgroupsAdapter mSubgroups;
    String                      mLineChoosen;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Intent intent = getActivity().getIntent();

        Subgroups sub = new Subgroups(getActivity());
        mLineChoosen = intent.getStringExtra(Constants.LINE_NAME);
        ArrayList<Subgroups> s = sub.getSubgroups(mLineChoosen);
        mSubgroups = new subgroupsAdapter(getActivity(),s);

        rv.setAdapter(mSubgroups);

        return view;
    }
}


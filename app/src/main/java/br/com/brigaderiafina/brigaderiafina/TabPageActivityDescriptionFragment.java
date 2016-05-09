package br.com.brigaderiafina.brigaderiafina;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.adapters.Flavours;
import br.com.brigaderiafina.brigaderiafina.adapters.FlavoursAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class TabPageActivityDescriptionFragment extends Fragment {

    String mSubgroupChoosen;
    FlavoursAdapter mFlavours;
    String mEventDescription;
    TextView mEventDescriptionText;

    public TabPageActivityDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        mSubgroupChoosen  = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
        mEventDescription = intent.getStringExtra("EventDescription");

        if(!mSubgroupChoosen.contains("Evento")){
            View view = inflater.inflate(R.layout.fragment_subgroup_flavour_detail, container, false);

            RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerviewFlavours);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));


            Flavours fla = new Flavours(getActivity());
            mSubgroupChoosen = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
            ArrayList<Flavours> f = fla.getFlavors(mSubgroupChoosen);
            mFlavours = new FlavoursAdapter(getActivity(),f);

            rv.setAdapter(mFlavours);

            return view;
        }
        else{
            View view = inflater.inflate(R.layout.event_description_row, container, false);
            mEventDescriptionText = (TextView) view.findViewById(R.id.event_description);
            mEventDescriptionText.setText(mEventDescription);
            return view;
        }

    }


}

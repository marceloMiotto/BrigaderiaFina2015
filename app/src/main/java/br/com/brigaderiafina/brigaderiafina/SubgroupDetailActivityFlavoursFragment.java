package br.com.brigaderiafina.brigaderiafina;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import br.com.brigaderiafina.brigaderiafina.adapters.Flavours;
import br.com.brigaderiafina.brigaderiafina.adapters.FlavoursAdapter;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class SubgroupDetailActivityFlavoursFragment extends Fragment {

    String mSubgroupChoosen;
    FlavoursAdapter mFlavours;

    public SubgroupDetailActivityFlavoursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_subgroup_detail, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.subgroup_flavours_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        Intent intent = getActivity().getIntent();

        Flavours fla = new Flavours(getActivity());
        mSubgroupChoosen = intent.getStringExtra(Constants.SUBGROUP_DETAILS);
        ArrayList<Flavours> f = fla.getFlavors(mSubgroupChoosen);
        mFlavours = new FlavoursAdapter(getActivity(),f);
        //TODO remove this If part
        if(mFlavours !=null){
            Log.i(Constants.LOG_TAG,"Ok exists..");
        }
        rv.setAdapter(mFlavours);

        return view;
    }


}
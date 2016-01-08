package br.com.brigaderiafina.brigaderiafina;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailActivityFragment extends Fragment{

    int mSubgroupCount;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                return new ViewHolder(getActivity().getLayoutInflater().inflate(R.layout.item_list_detail, parent, false));
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                viewHolder.subgroupName.setText("Brigadeiros");
                viewHolder.subgroupPrice.setText("R$ 1,80.");
            }

            @Override
            public int getItemCount() {
                return mSubgroupCount;
            }
        });


        return view;
    }

   private static class ViewHolder extends RecyclerView.ViewHolder {

       ImageView subgroupPhoto;
       TextView  subgroupName;
       TextView  subgroupPrice;

    public ViewHolder(View itemView) {
        super(itemView);
        subgroupPhoto = (ImageView) itemView.findViewById(R.id.subgroupPhotoImageView);
        subgroupName = (TextView) itemView.findViewById(R.id.subgroupNameTextView);
        subgroupPrice = (TextView) itemView.findViewById(R.id.subgroupPriceTextView);
    }
}
}


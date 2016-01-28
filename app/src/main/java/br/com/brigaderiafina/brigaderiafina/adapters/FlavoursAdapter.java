package br.com.brigaderiafina.brigaderiafina.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.utils.Util;

public class FlavoursAdapter extends  RecyclerView.Adapter<FlavoursAdapter.CustomViewHolder>{

    private List<Flavours> flavoursList;
    private Context mContext;
    private Util mUtil = new Util();

    public FlavoursAdapter(Context context, List<Flavours> flavoursList) {
        this.flavoursList = flavoursList;
        //test
        Flavours fa = new Flavours(context);
        for(int i=0;i<this.flavoursList.size();i++){
            fa = this.flavoursList.get(i);
        }
        this.mContext = context;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flavours_item_list_detail,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {

        Flavours fav = flavoursList.get(position);
        viewHolder.subgroupFlavour.setText(fav.flavours);
    }

    @Override
    public int getItemCount() {

        return (flavoursList != null ? flavoursList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView subgroupFlavour;

        public CustomViewHolder(View itemView) {
            super(itemView);
            subgroupFlavour = (TextView) itemView.findViewById(R.id.flavourTextView);

        }

    }
}

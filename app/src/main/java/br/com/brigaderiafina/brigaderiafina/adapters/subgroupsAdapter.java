package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import br.com.brigaderiafina.brigaderiafina.R;

public class SubgroupsAdapter extends RecyclerView.Adapter<SubgroupsAdapter.CustomViewHolder> {

    private List<Subgroups> subgroupsList;
    private Context         mContext;
    private int             mSubgroupCount;

    public SubgroupsAdapter(Context context, List<Subgroups> subgroupsList) {
        this.subgroupsList = subgroupsList;
        this.mContext = context;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detail,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {

        Subgroups sub = subgroupsList.get(position);
        viewHolder.subgroupName.setText(sub.subgroupName);
        viewHolder.subgroupPrice.setText(sub.subgroupPrice);

    }

    @Override
    public int getItemCount() {
        return (subgroupsList != null ? subgroupsList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{

        ImageView subgroupPhoto;
        TextView  subgroupName;
        TextView  subgroupPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            subgroupPhoto = (ImageView) itemView.findViewById(R.id.subgroupPhotoImageView);
            subgroupName = (TextView) itemView.findViewById(R.id.subgroupNameTextView);
            subgroupPrice = (TextView) itemView.findViewById(R.id.subgroupPriceTextView);
        }

    }

}
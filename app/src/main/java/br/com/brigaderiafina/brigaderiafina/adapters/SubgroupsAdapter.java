
package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.TabPageActivity;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;
import br.com.brigaderiafina.brigaderiafina.utils.Util;

import com.squareup.picasso.Picasso;

public class SubgroupsAdapter extends RecyclerView.Adapter<SubgroupsAdapter.CustomViewHolder> {

    private List<Subgroups> subgroupsList;
    private Context mContext;
    private Util mUtil = new Util();

    public SubgroupsAdapter(Context context, List<Subgroups> subgroupsList) {
        this.subgroupsList = subgroupsList;
        this.mContext = context;

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_detail,null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder viewHolder, int position) {

        Subgroups sub = subgroupsList.get(position);
        viewHolder.subgroupName.setText(sub.subgroupName);
        viewHolder.subgroupPrice.setText("R$ "+mUtil.currencyFormat(sub.subgroupPrice));
        Picasso.with(mContext).load(sub.subgroupPhoto).into(viewHolder.subgroupPhoto);
    }

    @Override
    public int getItemCount() {
        return (subgroupsList != null ? subgroupsList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView subgroupPhoto;
        TextView subgroupName;
        TextView  subgroupPrice;

        public CustomViewHolder(View itemView) {
            super(itemView);
            subgroupPhoto = (ImageView) itemView.findViewById(R.id.subgroupPhotoImageView);
            subgroupName = (TextView) itemView.findViewById(R.id.subgroupNameTextView);
            subgroupPrice = (TextView) itemView.findViewById(R.id.subgroupPriceTextView);
            subgroupName.setOnClickListener(this);
            subgroupPrice.setOnClickListener(this);
            subgroupPhoto.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Subgroups sub = subgroupsList.get(position);
            Intent intent = new Intent(mContext, TabPageActivity.class);
            intent.putExtra(Constants.SUBGROUP_DETAILS,sub.subgroupName);
            mContext.startActivity(intent);

        }
    }

}




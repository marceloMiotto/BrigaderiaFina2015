package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.TabPageActivity;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.DataObjectHolder> {

    private static String LOG_TAG = "EventsAdapter";
    private ArrayList<Events> mEvents;
    private Context mContext;
    private StringBuilder url = new StringBuilder(0);

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView  eventTitle;
        TextView  eventType;
        ImageView eventMainPhoto;

        public DataObjectHolder(View itemView) {
            super(itemView);
            eventTitle     = (TextView) itemView.findViewById(R.id.event_title);
            eventType      = (TextView) itemView.findViewById(R.id.event_type);
            eventMainPhoto = (ImageView) itemView.findViewById(R.id.event_main_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            Events evs = mEvents.get(position);
            Intent intent = new Intent(mContext, TabPageActivity.class);
            intent.putExtra(Constants.SUBGROUP_DETAILS, evs.mEventTitle);
            mContext.startActivity(intent);
        }
    }

    public EventsAdapter(Context context,ArrayList<Events> myDataset) {
        mContext = context;
        mEvents = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.eventTitle.setText(mEvents.get(position).mEventTitle);
        holder.eventType.setText(mEvents.get(position).mEventType);
        url.setLength(0);
        url.append(Constants.SITE+mEvents.get(position).mEventMainPhoto);
        Log.e("Debug91","url "+url.toString());
        Picasso.with(mContext).load(url.toString()).into(holder.eventMainPhoto);
    }

    public void addItem(Events dataObj, int index) {
        mEvents.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mEvents.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {

        return mEvents.size();
    }

}

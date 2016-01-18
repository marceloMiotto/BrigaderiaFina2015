package br.com.brigaderiafina.brigaderiafina.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;

public class EventsAdapter extends RecyclerView
        .Adapter<EventsAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "EventsAdapter";
    private ArrayList<Events> mEvents;
    private static EventsClickListener eventsClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            eventsClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(EventsClickListener eventsClickListener) {
        this.eventsClickListener = eventsClickListener;
    }

    public EventsAdapter(ArrayList<Events> myDataset) {
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
        holder.label.setText(mEvents.get(position).getmText1());
        holder.dateTime.setText(mEvents.get(position).getmText2());
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

    public interface EventsClickListener {
        public void onItemClick(int position, View v);
    }
}

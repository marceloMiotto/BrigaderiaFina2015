package br.com.brigaderiafina.brigaderiafina.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.brigaderiafina.brigaderiafina.R;
import br.com.brigaderiafina.brigaderiafina.TabPageActivity;
import br.com.brigaderiafina.brigaderiafina.utils.Constants;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.DataObjectHolder> {

    private static String LOG_TAG = "EventsAdapter";
    private ArrayList<Events> mEvents;
    private Context mContext;

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            int position = getAdapterPosition();
//            eventsClickListener.onItemClick(getAdapterPosition(), v);
            Log.i(Constants.LOG_TAG,"onClick debug1");
            Log.i(Constants.LOG_TAG,"Adpater Position "+getAdapterPosition());

            Events evs = mEvents.get(position);
            Intent intent = new Intent(mContext, TabPageActivity.class);
            intent.putExtra(Constants.SUBGROUP_DETAILS, evs.getmText1());
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

}

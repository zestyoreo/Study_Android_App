package com.example.studyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    LinkedList<event> mEventList;
    LayoutInflater mInflater;
    DataBase db;
    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleItemView;
        TextView descItemView;
        TextView startItemView;
        TextView endItemView;
        TextView IDItemView;
        EventListAdapter mAdapter;
        int deleted = 0;
        public EventViewHolder(View itemView, EventListAdapter adapter) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.title);
            descItemView = itemView.findViewById(R.id.desc);
            startItemView = itemView.findViewById(R.id.start);
            endItemView = itemView.findViewById(R.id.end);
            IDItemView = itemView.findViewById(R.id.Id);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(deleted == 2){
                String s = "[Done/Deleted!]" + titleItemView.getText();
                titleItemView.setText (s);
                db.Delete(Integer.parseInt(""+IDItemView.getText()));
            }
            deleted++;
        }
    }

    public EventListAdapter(Context context, LinkedList<event> eventList) {
        mInflater = LayoutInflater.from(context);
        db = DataBase.getInstance(context.getApplicationContext());
        this.mEventList = eventList;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView =
                mInflater.inflate(R.layout.item_list,
                        parent, false);
        return new EventViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.EventViewHolder holder, int position) {
        event mCurrent = mEventList.get(position);
        holder.titleItemView.setText(mCurrent.getTitle());
        holder.descItemView.setText(mCurrent.getDesc());
        holder.IDItemView.setText(String.valueOf(mCurrent.getID()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
        holder.startItemView.setText("Starting :" + dateFormat.format(mCurrent.getStart()));
        holder.endItemView.setText("Ending :" + dateFormat.format(mCurrent.getEnd()));
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }
}

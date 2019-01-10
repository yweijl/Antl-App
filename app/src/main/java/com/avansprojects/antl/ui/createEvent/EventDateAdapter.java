package com.avansprojects.antl.ui.createEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;

import java.util.ArrayList;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventDateAdapter extends RecyclerView.Adapter<EventDateAdapter.EventDateCardViewHolder> {

    class EventDateCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView mEventDate;
        private Button mRemoveDateButton;
        private Button mAddDateButton;

        EventDateCardViewHolder(CardView cardView) {
            super(cardView);
            mEventDate = cardView.findViewById(R.id.eventDate);
            mRemoveDateButton = cardView.findViewById(R.id.removeDateFromEvent);
            mAddDateButton = cardView.findViewById(R.id.addDateToEvent);

            mAddDateButton.setOnClickListener(view -> {
                i = getAdapterPosition();
                mEventDateList.add(Integer.toString(i));
                notifyItemInserted(getAdapterPosition() + 1);
                notifyItemRangeChanged(getAdapterPosition(), mEventDateList.size());
                notifyDataSetChanged();
            });

            mRemoveDateButton.setOnClickListener(view -> {
                i = getAdapterPosition();
                mEventDateList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition() - 1);
                notifyItemRangeChanged(getAdapterPosition(), mEventDateList.size());
                notifyDataSetChanged();
                if (mEventDateList.size() == 0){
                    mEventDateList.add(Integer.toString(i));
                }
            });
        }
    }

    private int i;
    private List<String> mEventDateList = new ArrayList<String>(){{add("Insert Date here");}};

    public EventDateAdapter(){
    }

    public void addItem() {
        mEventDateList.add("");
        notifyItemInserted(mEventDateList.size());
    }

    @Override
    public EventDateCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_event_date_card_fragment, parent, false);
        return new EventDateCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EventDateCardViewHolder holder, int position) {
        if (mEventDateList.size() - 1 == position ) {
            holder.mAddDateButton.setVisibility(View.VISIBLE);
            holder.mRemoveDateButton.setVisibility(View.GONE);
        } else {
            holder.mAddDateButton.setVisibility(View.GONE);
            holder.mRemoveDateButton.setVisibility(View.VISIBLE);
        }
            String test = mEventDateList.get(position);
            holder.mEventDate.setText(test);
    }

    void setEvents(List<String> dateList){
        mEventDateList = dateList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mEventDateList != null)
            return mEventDateList.size();
        else return 0;
    }
}
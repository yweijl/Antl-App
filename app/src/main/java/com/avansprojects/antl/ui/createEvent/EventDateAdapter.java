package com.avansprojects.antl.ui.createEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;
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
            mEventDate = cardView.findViewById(R.id.firstEventDate);
            mRemoveDateButton = cardView.findViewById(R.id.removeDateFromEvent);
            mAddDateButton = cardView.findViewById(R.id.addDateToEvent);

            mAddDateButton.setOnClickListener(view -> {
                mEventDateList.add("");
                notifyItemInserted(getAdapterPosition() + 1);
                notifyItemRangeChanged(getAdapterPosition(), mEventDateList.size());
            });

            mRemoveDateButton.setOnClickListener(view -> {
                mEventDateList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), mEventDateList.size());
            });
        }
    }

    private List<String> mEventDateList;

    public EventDateAdapter(List<String> eventDateList) {
        mEventDateList = eventDateList;
    }

    public void addItem() {
        mEventDateList.add("");
        notifyItemInserted(mEventDateList.size() - 1);
    }

    @Override
    public EventDateCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.create_event_date_card_fragment, parent, false);
        return new EventDateCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EventDateCardViewHolder holder, int position) {

        if (mEventDateList != null) {
            holder.mEventDate.setText(mEventDateList.get(position));
        }
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
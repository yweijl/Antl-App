package com.avansprojects.antl.ui.createEvent;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventDateAdapter extends RecyclerView.Adapter<EventDateAdapter.EventDateCardViewHolder> {

    class EventDateCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView mEventDate;
        private Button mRemoveDateButton;

        EventDateCardViewHolder(CardView cardView) {
            super(cardView);

            mEventDate = cardView.findViewById(R.id.eventDate);

            mRemoveDateButton = cardView.findViewById(R.id.removeDateFromEvent);
            mRemoveDateButton.setOnClickListener(view -> {
                mEventDateList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition() - 1);
                notifyItemRangeChanged(getAdapterPosition(), mEventDateList.size());
                notifyDataSetChanged();
            });
        }
    }

    private List<Date> mEventDateList = new ArrayList<>();

    public List<Date> getEventDates(){
        return mEventDateList;
    }

    public void addItem(Date date) {
        mEventDateList.add(date);
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
            Date date = mEventDateList.get(position);
            holder.mEventDate.setText(CalendarHelper.DateTimeToString(date));
    }

    void setEvents(List<Date> dateList){
        Collections.sort(dateList);
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
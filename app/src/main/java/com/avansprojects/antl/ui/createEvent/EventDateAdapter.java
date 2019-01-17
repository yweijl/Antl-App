package com.avansprojects.antl.ui.createEvent;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.CalendarHelper;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.listeners.ViewModelListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventDateAdapter extends RecyclerView.Adapter<EventDateAdapter.EventDateCardViewHolder> {

    private List<EventDate> mEventDateList;
    private ViewModelListener mListener;

    public EventDateAdapter(ViewModelListener listener) {
        mListener = listener;
        mEventDateList = new ArrayList<>();
    }

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
                setEvents();
            });
        }
    }

    public void addItem(EventDate date) {
        mEventDateList.add(date);
        setEvents();
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
            EventDate date = mEventDateList.get(position);
            holder.mEventDate.setText(CalendarHelper.DateTimeToString(date.getEventDate()));
    }

    void setEvents(){
        Collections.sort(mEventDateList);
        mListener.dataIsChanged();
        notifyDataSetChanged();
    }

    List<EventDate> getEventDates(){
        return  mEventDateList;
    }

    @Override
    public int getItemCount()
    {
        if (mEventDateList != null)
            return mEventDateList.size();
        else return 0;
    }
}
package com.avansprojects.antl.ui.eventOverview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.avansprojects.antl.R;
import com.avansprojects.antl.dummy.DummyEvents;
import java.util.List;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EventOverviewAdapter extends RecyclerView.Adapter<EventOverviewAdapter.EventCardViewHolder> {

    private List<DummyEvents> mEventsList;

    public EventOverviewAdapter(List<DummyEvents> mEventsList) {
        this.mEventsList = mEventsList;
    }

    @Override
    public EventOverviewAdapter.EventCardViewHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_overview_card_fragment, parent, false);
        return new EventCardViewHolder(cardView);
    }

    class EventCardViewHolder extends RecyclerView.ViewHolder {
        TextView eventName;
        TextView location;
        TextView day;
        TextView month;
        TextView attendingUsers;

        EventCardViewHolder(CardView cardView) {
            super(cardView);
            eventName = cardView.findViewById(R.id.eventOverviewName);
            location = cardView.findViewById(R.id.eventOverviewLocation);
            day = cardView.findViewById(R.id.eventOverviewDay);
            month = cardView.findViewById(R.id.eventOverviewMonth);
            attendingUsers = cardView.findViewById(R.id.eventOverviewAttendingUsers);
        }
    }

    @Override
    public void onBindViewHolder(EventCardViewHolder holder, int position) {
        DummyEvents event = mEventsList.get(position);

        holder.eventName.setText(event.getName());
        holder.location.setText(event.getLocation());
        holder.day.setText(event.getDay());
        holder.month.setText("Dec");
        holder.attendingUsers.setText(event.getAttendingUsers());
    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }
}

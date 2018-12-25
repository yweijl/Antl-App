package com.avansprojects.antl.ui.eventOverview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.avansprojects.antl.R;
import com.avansprojects.antl.helpers.GlideApp;
import com.avansprojects.antl.helpers.PartialDateConverter;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.bumptech.glide.Glide;

import java.util.List;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


public class EventOverviewAdapter extends RecyclerView.Adapter<EventOverviewAdapter.EventCardViewHolder> {

    class EventCardViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventName;
        private final TextView location;
        private final TextView day;
        private final TextView month;
        private final TextView attendingUsers;
        private ImageView picture;

        EventCardViewHolder(CardView cardView) {
            super(cardView);
            eventName = cardView.findViewById(R.id.eventOverviewName);
            location = cardView.findViewById(R.id.eventOverviewLocation);
            day = cardView.findViewById(R.id.eventOverviewDay);
            month = cardView.findViewById(R.id.eventOverviewMonth);
            attendingUsers = cardView.findViewById(R.id.eventOverviewAttendingUsers);
            picture = cardView.findViewById(R.id.eventOverviewPicture);
        }
    }

    private List<Event> _eventsList;
    private Fragment _fragment;

    public EventOverviewAdapter(Fragment fragment) {
        _fragment = fragment;
    }

    @Override
    public EventOverviewAdapter.EventCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_overview_card_fragment, parent, false);
        return new EventCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(EventCardViewHolder holder, int position) {
        if (_eventsList != null) {

            Event current = _eventsList.get(position);
            holder.eventName.setText(current.getName());
            holder.location.setText(current.getLocation());
            holder.day.setText(PartialDateConverter.getDay(current.getMainDateTime()));
            holder.month.setText(PartialDateConverter.getMonth(current.getMainDateTime()));
            holder.attendingUsers.setText("13");
            GlideApp.with(_fragment).load(R.drawable.event).centerCrop().into(holder.picture);

        } else {
            holder.eventName.setText("No Events");
        }
    }

    void setEvents(List<Event> events){
        _eventsList = events;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (_eventsList != null)
            return _eventsList.size();
        else return 0;
    }
}

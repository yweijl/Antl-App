package com.avansprojects.antl.ui.friendOverview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.entities.Relationship;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FriendOverviewAdapter extends RecyclerView.Adapter<FriendCardViewHolder> {

    private List<Relationship> _relationshipList;
    private Fragment _fragment;

    public FriendOverviewAdapter(Fragment fragment) {
        _fragment = fragment;
    }

    @Override
    public FriendCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_overview_card_fragment, parent, false);
        return new FriendCardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(FriendCardViewHolder holder, int position) {
        if (_relationshipList != null) {

            Relationship current = _relationshipList.get(position);
            holder.friendName.setText(String.valueOf(current.getUserIdOne()));

        } else {
            holder.friendName.setText("No Friends");
        }
    }

    void setRelationships(List<Relationship> relationships){
        _relationshipList = relationships;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (_relationshipList != null)
            return _relationshipList.size();
        else return 0;
    }
}

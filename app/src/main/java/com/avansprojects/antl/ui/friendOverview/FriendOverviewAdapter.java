package com.avansprojects.antl.ui.friendOverview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.entities.Friend;
import com.avansprojects.antl.ui.friendAddMenu.FriendAddViewModel;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class FriendOverviewAdapter extends RecyclerView.Adapter<FriendCardViewHolder> {

    private List<Friend> mFriendList;
    private Fragment _fragment;
    private FriendCardViewHolder mCardViewHolder;

    public FriendOverviewAdapter(Fragment fragment) {
        _fragment = fragment;
    }

    @Override
    public FriendCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_overview_card_fragment, parent, false);
        return mCardViewHolder =  new FriendCardViewHolder(cardView, _fragment.getContext(), ViewModelProviders.of(_fragment).get(FriendAddViewModel.class));
    }

    @Override
    public void onBindViewHolder(FriendCardViewHolder holder, int position) {
        if (mFriendList != null) {

            Friend current = mFriendList.get(position);
            holder.friendName.setText(String.valueOf(current.getUserName()));
            holder.mWebserverId = current.getWebServerId();

        } else {
            holder.friendName.setText("No Friends");
        }
    }

    void setFriends(List<Friend> relationships){
        mFriendList = relationships;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mFriendList != null)
            return mFriendList.size();
        else return 0;
    }
}

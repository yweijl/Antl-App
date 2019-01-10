package com.avansprojects.antl.ui.friendOverview;

import android.widget.TextView;

import com.avansprojects.antl.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FriendCardViewHolder extends RecyclerView.ViewHolder {
    public final TextView friendName;

    FriendCardViewHolder(CardView cardView) {
        super(cardView);
        friendName = cardView.findViewById(R.id.friendName);
    }
}

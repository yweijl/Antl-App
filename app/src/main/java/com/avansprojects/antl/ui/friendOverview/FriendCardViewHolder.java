package com.avansprojects.antl.ui.friendOverview;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TextView;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.repositories.ContactRepository;
import com.avansprojects.antl.ui.friendAddMenu.FriendAddViewModel;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class FriendCardViewHolder extends RecyclerView.ViewHolder{
    public final TextView friendName;
    public String mWebserverId;
    private Context mContext;
    private CardView mCardView;
    private FriendAddViewModel mViewModel;

    FriendCardViewHolder(CardView cardView, Context context, FriendAddViewModel viewModel) {
        super(cardView);
        mContext = context;
        mCardView = cardView;
        mViewModel = viewModel;

        friendName = cardView.findViewById(R.id.friendName);

        MaterialButton deleteButton = cardView.findViewById(R.id.removeFriendButton);
        deleteButton.setOnClickListener(view -> confirmDelete(mCardView));
    }

    public void confirmDelete(CardView v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.AlertDialog);
        builder.setTitle("Delete friend")
                .setMessage("ARE YOU SURE YOU WANT TO REMOVE " + friendName.getText().toString().toUpperCase() + " FROM YOUR LIST?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mViewModel.deleteFriend(friendName.getText().toString(), mWebserverId);
                    }
                }
                )
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create().show();
    }
}


package com.avansprojects.antl.ui.friendOverview;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.avansprojects.antl.R;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DeleteFriendDialogue extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface DeleteFriendDialogueListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    DeleteFriendDialogueListener mListener;

    // Override the Fragment.onAttach() method to instantiate the DeleteFriendDialogueListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DeleteFriendDialogueListener so we can send events to the host
            mListener = (DeleteFriendDialogueListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(
                    "must implement DeleteFriendDialogueListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete friend?")
                .setPositiveButton("Yes", (dialog, id) -> mListener.onDialogPositiveClick(DeleteFriendDialogue.this))
                .setNegativeButton(R.string.cancel, (dialog, id) -> mListener.onDialogNegativeClick(DeleteFriendDialogue.this));
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
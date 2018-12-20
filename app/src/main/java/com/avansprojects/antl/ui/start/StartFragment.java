package com.avansprojects.antl.ui.start;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avansprojects.antl.R;

public class StartFragment extends Fragment {

    private StartViewModel mViewModel;
    private TextView textView;

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.start_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StartViewModel.class);
        textView = getView().findViewById(R.id.message);
        // Create the observer which updates the UI.
        final Observer<String> messageObserver = newMessage -> {
            // Update the UI, in this case, a TextView.
            textView.setText(newMessage);
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mViewModel.get_data().observe(this, messageObserver);
    }

}

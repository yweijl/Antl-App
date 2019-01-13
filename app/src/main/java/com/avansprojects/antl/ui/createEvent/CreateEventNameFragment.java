package com.avansprojects.antl.ui.createEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avansprojects.antl.R;
import com.avansprojects.antl.infrastructure.entities.Event;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class CreateEventNameFragment extends Fragment {

    private CreateEventViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.create_event_name_fragment, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(CreateEventViewModel.class);
        mViewModel.getEvent().observe(this, event ->{
            event.setName(((TextView) getActivity().findViewById(R.id.enterEventName)).getText().toString());
        });
    }
}

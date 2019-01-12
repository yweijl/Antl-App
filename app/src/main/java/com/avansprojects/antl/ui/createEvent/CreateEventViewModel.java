package com.avansprojects.antl.ui.createEvent;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class CreateEventViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository mEventRepository;
    private EventDateRepository mEventDateRepository;

    public CreateEventViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
    }

    void insert(Event event) { mEventRepository.insert(event); }
}

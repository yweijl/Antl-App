package com.avansprojects.antl.ui.createEvent;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CreateEventViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository _EventRepository;

    public CreateEventViewModel(@NonNull Application application) {
        super(application);
        _EventRepository = new EventRepository(application);
    }

    void insert(Event event) { _EventRepository.insert(event); }
}

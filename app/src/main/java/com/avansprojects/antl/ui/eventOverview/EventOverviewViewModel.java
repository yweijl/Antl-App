package com.avansprojects.antl.ui.eventOverview;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EventOverviewViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private EventRepository _EventRepository;
    private LiveData<List<Event>> _AllEvents;

    public EventOverviewViewModel(@NonNull Application application) {
        super(application);
        _EventRepository = new EventRepository(application);
        _AllEvents = _EventRepository.getAllEvents();
    }

    public LiveData<List<Event>> getAllEvents() {
        return _AllEvents;
    }

    public void insert(Event event) { _EventRepository.insert(event); }
}

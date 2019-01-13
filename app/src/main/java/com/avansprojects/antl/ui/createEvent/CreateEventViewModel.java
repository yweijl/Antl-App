package com.avansprojects.antl.ui.createEvent;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.infrastructure.repositories.EventDateRepository;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CreateEventViewModel extends AndroidViewModel {

    private EventRepository mEventRepository;
    private EventDateRepository mEventDateRepository;
    private MutableLiveData<List<EventDate>> mLiveEventDateData;
    private MutableLiveData<Event> mEvent;


    public CreateEventViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
        mEventDateRepository = new EventDateRepository(application);
            }

    public LiveData<Event> getEvent() {
        if (mEvent == null) {
            mEvent = new MutableLiveData<>();
        }
        return mEvent;
    }

    public void setEvent(Event event){
        mEvent.postValue(event);
    }

    public LiveData<List<EventDate>> getEventDates() {
        if (mLiveEventDateData == null) {
            mLiveEventDateData = new MutableLiveData<>();
        }
        return mLiveEventDateData;
    }

    public void setEventDates(List<EventDate> eventDates) {
        mLiveEventDateData.setValue(eventDates);
    }

    void insert(Event event, List<EventDate> eventDates) {
        mEventRepository.insert(event);
        insertAll(eventDates);
    }

    void insert(Event event) {
        mEventRepository.insert(event);
    }

    void insertAll(List<EventDate> eventDates) {
        for (EventDate eventDate: eventDates
             ) {
            mEventDateRepository.insert(eventDate);
        }
    }

    public void saveEvent() {
        Event event = mEvent.getValue();
        insert(mEvent.getValue(), mLiveEventDateData.getValue());
    }
}

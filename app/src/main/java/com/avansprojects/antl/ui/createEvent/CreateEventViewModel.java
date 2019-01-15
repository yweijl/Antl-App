package com.avansprojects.antl.ui.createEvent;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.infrastructure.repositories.EventDateRepository;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;
import com.avansprojects.antl.listeners.AsyncTaskListener;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CreateEventViewModel extends AndroidViewModel implements AsyncTaskListener {

    private EventRepository mEventRepository;
    private EventDateRepository mEventDateRepository;
    private MutableLiveData<List<EventDate>> mLiveEventDateData;
    private MutableLiveData<Event> mEvent;
    private String mPicturePath;


    public CreateEventViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
        mEventDateRepository = new EventDateRepository(application);
            }

    void saveEvent(String name, String description, String location) {
        Event event = new Event();
        event.setName(name);
        event.setPicturePath(mPicturePath);
        event.setDescription(description);
        event.setLocation(location);
        event.setMainDateTime(mLiveEventDateData.getValue().get(0).getEventDate());
        insertEvent(event);
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

    void setEventDates(List<EventDate> eventDates) {
        if (mLiveEventDateData == null){
            mLiveEventDateData = new MutableLiveData<>();
        }
        mLiveEventDateData.setValue(eventDates);
    }

    private void insertEvent(Event event) {
        mEventRepository.insertRetrieveId(event, this);
    }

    private void insertEventDates(int id) {
        List<EventDate> eventDates = mLiveEventDateData.getValue();

        for (EventDate eventDate: eventDates
             ) { eventDate.setEventId(id);
        }
        mEventDateRepository.insertAll(eventDates);
    }

    @Override
    public void entityIdInsertListener(long id) {
        insertEventDates((int)id);
    }

    public String getmPicturePath() {
        return mPicturePath;
    }

    public void setmPicturePath(String mPicturePath) {
        this.mPicturePath = mPicturePath;
    }
}
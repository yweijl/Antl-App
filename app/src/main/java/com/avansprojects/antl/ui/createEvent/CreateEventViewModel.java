package com.avansprojects.antl.ui.createEvent;

import android.app.Application;

import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventDateDto;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.infrastructure.repositories.EventDateRepository;
import com.avansprojects.antl.infrastructure.repositories.EventRepository;
import com.avansprojects.antl.listeners.AsyncTaskListener;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CreateEventViewModel extends AndroidViewModel implements AsyncTaskListener {

    private EventRepository mEventRepository;
    private EventDateRepository mEventDateRepository;
    private MutableLiveData<List<EventDate>> mLiveEventDateData;
    private List<EventDateDto> mEventDateDtoList;
    private MutableLiveData<Event> mLiveEvent;
    private Event mEvent;
    private String mPicturePath;


    public CreateEventViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
        mEventDateRepository = new EventDateRepository(application);
            }

    void saveEvent(String name, String description, String location) {
        mEvent = new Event();
        mEvent.setName(name);
        mEvent.setPicturePath(mPicturePath);
        mEvent.setDescription(description);
        mEvent.setLocation(location);
        mEvent.setMainDateTime(mLiveEventDateData.getValue().get(0).getEventDate());
        insertEvent(mEvent);
    }

    public LiveData<Event> getmEvent() {
        if (mLiveEvent == null) {
            mLiveEvent = new MutableLiveData<>();
        }
        return mLiveEvent;
    }

    public void setmEvent(Event mEvent){
        mLiveEvent.postValue(mEvent);
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
        mEventDateDtoList = new ArrayList<>();
        for (EventDate eventDate: eventDates
             ) { eventDate.setEventId(id);
             mapEventDateToDto(eventDate);
        }
        mLiveEventDateData.setValue(eventDates);
        mEventDateRepository.insertAll(eventDates);
    }

    private void mapEventDateToDto(EventDate eventDate) {
        mEventDateDtoList.add(new EventDateDto(eventDate.getEventDate(), eventDate.getEventId()));
    }

    @Override
    public void entityIdInsertListener(long id) {
        insertEventDates((int)id);
        CreateEventDto eventDto = createEventDto();
        mEventDateRepository.post(eventDto);

    }

    private CreateEventDto createEventDto() {
        return new CreateEventDto(
                "", mEvent.getName(),mEvent.getDescription()
                , mEvent.getPicturePath(),mEvent.getMainDateTime(), mEvent.getLocation(),mEventDateDtoList,true
        );
    }

    public String getmPicturePath() {
        return mPicturePath;
    }

    public void setmPicturePath(String mPicturePath) {
        this.mPicturePath = mPicturePath;
    }
}
package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventSyncDto;
import com.avansprojects.antl.infrastructure.dtos.UpdateEventDto;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.services.IEventApi;
import com.avansprojects.antl.listeners.AsyncTaskListener;
import com.avansprojects.antl.listeners.CompareDataListener;
import com.avansprojects.antl.listeners.UpdateEventDateListener;
import com.avansprojects.antl.retrofit.AntlRetrofit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventRepository implements CompareDataListener {

    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;
    private Retrofit mRetrofit;
    private IEventApi mEventApi;
    private UpdateEventDateListener mUpdateEventDateListener;

    public EventRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvents = mEventDao.getAllEvents();
        mRetrofit = AntlRetrofit.getRetrofit();
        mEventApi = mRetrofit.create(IEventApi.class);
    }

    public void insert(Event event) {
        new insertAsyncTask(mEventDao).execute(event);
    }

    public void insertRetrieveId(Event event, AsyncTaskListener listener) {
        new insertRetrieveIdAsyncTask(listener, mEventDao).execute(event);
    }

    public void updateExternalId(EventSyncDto eventSyncDto, int eventId) {
        new updateExternalIdAsyncTask(mEventDao, eventSyncDto.externalId, eventSyncDto.eventHash, eventId).execute();
    }

    public void post(CreateEventDto eventDto, int eventId) {
        mEventApi.post(eventDto).enqueue(new Callback<EventSyncDto>() {
            @Override
            public void onResponse(Call<EventSyncDto> call, Response<EventSyncDto> response) {
                java.lang.String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
                if (response.raw().code() == 401 || response.raw().code() == 500){
                    return;
                }
                    updateExternalId(response.body(), eventId);
            }

            @Override
            public void onFailure(Call<EventSyncDto> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());

            }
        });
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }

    public void syncData(UpdateEventDateListener eventListener) {
        mUpdateEventDateListener = eventListener;
        mEventApi.sync().enqueue(new Callback<List<EventSyncDto>>() {
            @Override
            public void onResponse(Call<List<EventSyncDto>> call, Response<List<EventSyncDto>> response) {
                java.lang.String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
                    compareData(response.body());
            }

            @Override
            public void onFailure(Call<List<EventSyncDto>> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
    }

    @Override
    public void dataListener(UpdateEventDto updateEventDto) {
        updateData(updateEventDto);
    }

    @Override
    public void insertEvent(CreateEventDto createEventDto) {
        new insertEventWithDatesTask(mEventDao, mUpdateEventDateListener, createEventDto).execute();
    }

    private static class insertEventWithDatesTask extends AsyncTask<Void, Void, Long> {
        private EventDao mAsyncTaskDao;
        private CreateEventDto mEventDto;
        private UpdateEventDateListener mUpdateEventDateListener;

        insertEventWithDatesTask(EventDao dao,UpdateEventDateListener updateEventDateListener, CreateEventDto eventDto) {
            mAsyncTaskDao = dao;
            mEventDto = eventDto;
            mUpdateEventDateListener = updateEventDateListener;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            Event event = new Event(
                    mEventDto.name,
                    mEventDto.externalId,
                    mEventDto.mainDateTime,
                    mEventDto.location,
                    mEventDto.description,
                    mEventDto.imagePath,
                    mEventDto.isOwner,
                    mEventDto.hash);
            return mAsyncTaskDao.insertRetrieveId(event);
        }

        @Override
        protected void onPostExecute(Long result) {
            if (mEventDto.eventDates == null) return;
            mUpdateEventDateListener.insertEventDate(result, mEventDto.eventDates);
        }
    }

    @Override
    public void updateEvent(int id, CreateEventDto createEventDto) {
        new updateLocalEventTask(mEventDao, createEventDto).execute();
        mUpdateEventDateListener.updateEventDate(id, createEventDto.eventDates);
    }

    private static class insertAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao mAsyncTaskDao;

        insertAsyncTask(EventDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Event... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertRetrieveIdAsyncTask extends AsyncTask<Event, Void, Long> {
        private AsyncTaskListener mAsyncTaskListener;
        private EventDao mAsyncTaskDao;

        insertRetrieveIdAsyncTask(AsyncTaskListener listener, EventDao dao) {
            mAsyncTaskListener = listener;
            mAsyncTaskDao = dao;
        }

        @Override
        protected Long doInBackground(final Event... params) {
            return mAsyncTaskDao.insertRetrieveId(params[0]);
        }

        @Override
        protected void onPostExecute(Long result) {
            mAsyncTaskListener.insertEventDispatcher(result);
        }
    }

    private static class updateExternalIdAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao mAsyncTaskDao;
        private int mEventId;
        private String mExternalId;
        private int mHash;

        updateExternalIdAsyncTask(EventDao dao, String externalId, int hash, int eventId) {
            mAsyncTaskDao = dao;
            mEventId = eventId;
            mExternalId = externalId;
            mHash = hash;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.updateHashAndExternalID(mExternalId, mHash, mEventId);
            return null;
        }
    }

    private void compareData(List<EventSyncDto> serverEvents) {
        if (serverEvents == null){
            return;
        }

        new compareDataTask(mEventDao,this ,serverEvents).execute();
    }

    private static class compareDataTask extends AsyncTask<Void, Void, List<EventSyncDto>> {

        private EventDao mEventDao;
        private List<EventSyncDto> mLocalEventSyncDtoList;
        private List<EventSyncDto> mServerEvents;
        private CompareDataListener mCompareDataListener;

        public compareDataTask(EventDao dao, CompareDataListener listener ,List<EventSyncDto> serverEvents) {
            mEventDao = dao;
            mServerEvents = serverEvents;
            mCompareDataListener = listener;
        }

        @Override
        protected List<EventSyncDto> doInBackground(Void... voids) {
            if (mServerEvents.size() > 0) {
                return mEventDao.getEventSyncList();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<EventSyncDto> result) {
            mLocalEventSyncDtoList = result;
            if (mLocalEventSyncDtoList != null) {
                compareData();
            }
        }

        private void compareData(){
            UpdateEventDto updateEventDto = new UpdateEventDto();
//            for (EventSyncDto localEvent : mLocalEventSyncDtoList
//            ) {
//                if (localEvent == null) break;
//                if (mServerEvents.stream().anyMatch(x -> x.externalId == localEvent.externalId && x.eventHash != localEvent.eventHash))
//                    updateEventDto.externalIdList.add(localEvent.externalId);
//            }

            for (EventSyncDto serverEvent : mServerEvents
            ) {
                if (serverEvent == null) break;
                if (!mLocalEventSyncDtoList.stream().anyMatch(x -> x.externalId == serverEvent.externalId))
                    updateEventDto.externalIdList.add(serverEvent.externalId);
            }
            mCompareDataListener.dataListener(updateEventDto);
        }
    }

    private void updateData(UpdateEventDto updateEventDto) {
        mEventApi.syncGetList(updateEventDto).enqueue(new Callback<List<CreateEventDto>>() {
            @Override
            public void onResponse(Call<List<CreateEventDto>> call, Response<List<CreateEventDto>> response) {
                java.lang.String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
                updateLocalEvents(response.body());
            }

            @Override
            public void onFailure(Call<List<CreateEventDto>> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
    }

    private void updateLocalEvents(@NonNull List<CreateEventDto> eventDtos) {

        for (CreateEventDto eventDto : eventDtos
        ) {
            new getIdFromEventTask(mEventDao, eventDto, this).execute();
        }
    }

    private static class getIdFromEventTask extends AsyncTask<Void, Void, Integer> {
        private EventDao mAsyncTaskDao;
        private CreateEventDto mEvent;
        private CompareDataListener mCompareDataListener;

        getIdFromEventTask(EventDao dao, CreateEventDto event, CompareDataListener eventListener) {
            mAsyncTaskDao = dao;
            mEvent = event;
            mCompareDataListener = eventListener;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            mAsyncTaskDao.getIdFromEvent(mEvent.externalId);
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if (result == null){
                mCompareDataListener.insertEvent(mEvent);
            } else {
                mCompareDataListener.updateEvent(result, mEvent);
            }
        }
    }

    private static class updateLocalEventTask extends AsyncTask<Void, Void, Void> {
        private EventDao mAsyncTaskDao;
        private CreateEventDto mEventDto;

        updateLocalEventTask(EventDao dao, CreateEventDto eventDto) {
            mAsyncTaskDao = dao;
            mEventDto = eventDto;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.updateByExternalId(
                    mEventDto.mainDateTime,
                    mEventDto.location,
                    mEventDto.imagePath,
                    mEventDto.description,
                    mEventDto.isOwner,
                    mEventDto.hash,
                    mEventDto.externalId);
            return null;
        }
    }
}
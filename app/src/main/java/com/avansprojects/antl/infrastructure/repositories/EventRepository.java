package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventDateDto;
import com.avansprojects.antl.infrastructure.dtos.EventSyncDto;
import com.avansprojects.antl.infrastructure.dtos.UpdateEventDto;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.interfaces.IEventApi;
import com.avansprojects.antl.listeners.AsyncTaskListener;
import com.avansprojects.antl.listeners.CompareDataListener;
import com.avansprojects.antl.listeners.UpdateEventListener;
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
    private UpdateEventListener mUpdateEventListener;

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

    public void syncData(UpdateEventListener eventListener) {
        mUpdateEventListener = eventListener;
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
//            List<EventSyncDto> syncDtoList = mapToEventSyncDto(localEvents);
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
            for (EventSyncDto localEvent : mLocalEventSyncDtoList
            ) {
                if (localEvent == null) break;
                if (mServerEvents.stream().anyMatch(x -> x.externalId == localEvent.externalId) &&
                        mServerEvents.stream().anyMatch(x -> x.eventHash != localEvent.eventHash))
                    updateEventDto.externalIdList.add(localEvent.externalId);
            }

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
            new updateLocalEventTask(mEventDao, eventDto).execute();
            new getIdFromEventTask(mEventDao, eventDto.eventDates, mUpdateEventListener).execute(eventDto.externalId);
        }
    }

    private static class getIdFromEventTask extends AsyncTask<String, Void, Integer> {
        private EventDao mAsyncTaskDao;
        List<EventDateDto> mEventDates;
        private UpdateEventListener mUpdateEventListener;

        getIdFromEventTask(EventDao dao, List<EventDateDto> eventDates, UpdateEventListener eventListener) {
            mAsyncTaskDao = dao;
            mEventDates = eventDates;
            mUpdateEventListener = eventListener;
        }

        @Override
        protected Integer doInBackground(String... params) {
            mAsyncTaskDao.getIdFromEvent(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            mUpdateEventListener.updateEventDate(result, mEventDates);
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
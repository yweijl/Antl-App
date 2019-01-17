package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.listeners.AsyncTaskListener;

import java.util.List;
import androidx.lifecycle.LiveData;

public class EventRepository {

    private EventDao mEventDao;
    private LiveData<List<Event>> mAllEvents;

    public EventRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mEventDao = db.eventDao();
        mAllEvents = mEventDao.getAllEvents();
    }

    public void insert(Event event) {
        new insertAsyncTask(mEventDao).execute(event);
    }
    public void insertRetrieveId(Event event, AsyncTaskListener listener) {
        new insertRetrieveIdAsyncTask(listener, mEventDao).execute(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return mAllEvents;
    }

    private static class insertAsyncTask extends AsyncTask<Event, Void, Void>{
        private AsyncTaskListener mAsyncTaskListener;
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

    private static class insertRetrieveIdAsyncTask extends AsyncTask<Event, Void, Long>{
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
        protected void onPostExecute(Long result){
            mAsyncTaskListener.entityIdInsertListener(result);
        }
    }
}

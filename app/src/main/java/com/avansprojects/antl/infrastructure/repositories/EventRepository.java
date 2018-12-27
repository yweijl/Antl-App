package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Event;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EventRepository {

    private EventDao _EventDao;
    private LiveData<List<Event>> _AllEvents;

    public EventRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        _EventDao = db.eventDao();
        _AllEvents = _EventDao.getAllEvents();
    }

    public void insert(Event event) {
        new insertAsyncTask(_EventDao).execute(event);
    }

    public LiveData<List<Event>> getAllEvents() {
        return _AllEvents;
    }

    private static class insertAsyncTask extends AsyncTask<Event, Void, Void> {

        private EventDao _AsyncTaskDao;

        insertAsyncTask(EventDao dao) {
            _AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Event... params) {
            _AsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.EventDateDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.EventDate;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EventDateRepository {

    private EventDateDao mEventDateDao;
    private LiveData<List<EventDate>> mAllEventDates;

    public EventDateRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mEventDateDao = db.eventDateDao();
        mAllEventDates = mEventDateDao.getAllEventDates();
    }

    public void insert(EventDate eventDate) {new insertAsyncTask(mEventDateDao).execute(eventDate);}

    public LiveData<List<EventDate>> getAllEvents() {
        return mAllEventDates;
    }

    private static class insertAsyncTask extends AsyncTask<EventDate, Void, Void> {

        private EventDateDao mAsyncTaskDao;

        insertAsyncTask(EventDateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EventDate... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

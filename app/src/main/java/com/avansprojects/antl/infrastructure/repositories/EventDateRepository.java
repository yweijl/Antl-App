package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;
import com.avansprojects.antl.infrastructure.daos.EventDateDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.dtos.EventDateDto;
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

    public void insertAll(List<EventDate> eventDates) {new insertAllAsyncTask(mEventDateDao).execute(eventDates);}

    public LiveData<List<EventDate>> getAllEvents() {
        return mAllEventDates;
    }

    public void updateExternalId(int eventId, List<EventDateDto> eventDateList) {
        new updateAsyncTask(mEventDateDao, eventDateList, eventId).execute();
    }

    private static class updateAsyncTask extends AsyncTask<Void, Void, Void>{
        private EventDateDao mAsyncTaskDao;
        private List<EventDateDto> mEventDateDto;
        private int mEventId;

        public updateAsyncTask(EventDateDao mAsyncTaskDao, List<EventDateDto> mEventDateDto, int mEventId) {
            this.mAsyncTaskDao = mAsyncTaskDao;
            this.mEventDateDto = mEventDateDto;
            this.mEventId = mEventId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (EventDateDto eventDate: mEventDateDto
            ) {
                mAsyncTaskDao.syncEventDates(mEventId, eventDate.dateTime);
            }
            return null;
        }
    }

    private static class insertAllAsyncTask extends AsyncTask<List<EventDate>, Void, Void> {

        private EventDateDao mAsyncTaskDao;

        insertAllAsyncTask(EventDateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<EventDate>... params) {
            List<EventDate> eventDateList = params[0];
            mAsyncTaskDao.insertAll(eventDateList);
            return null;
        }
    }
}
package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.FriendDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Friend;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactRepository {

    private FriendDao mContacts;
    private LiveData<List<Friend>> mAllContacts;

    public ContactRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mContacts = db.contactDao();
        mAllContacts = mContacts.getAll();
    }

    public void insert(Friend friend) {
        new insertAsyncTask(mContacts).execute(friend);
    }

    public void deleteAll(){new deleteAsyncTask(mContacts).execute();}

    public LiveData<List<Friend>> getAllRelationships() {
        return  mAllContacts;
    }

    private static class insertAsyncTask extends AsyncTask<Friend, Void, Void> {

        private FriendDao _AsyncTaskDao;

        insertAsyncTask(FriendDao dao) {
            _AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Friend... params) {
            _AsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private FriendDao _AsyncTaskDao;

        deleteAsyncTask(FriendDao dao){_AsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Void... voids) {
            _AsyncTaskDao.deleteAll();
            return null;
        }
    }
}

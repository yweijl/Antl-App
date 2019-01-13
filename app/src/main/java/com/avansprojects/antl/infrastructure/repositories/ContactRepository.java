package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.ContactDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Contact;

import java.util.List;

import androidx.lifecycle.LiveData;

public class ContactRepository {

    private ContactDao mContacts;
    private LiveData<List<Contact>> mAllContacts;

    public ContactRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mContacts = db.contactDao();
        mAllContacts = mContacts.getAll();
    }

    public void insert(Contact contact) {
        new insertAsyncTask(mContacts).execute(contact);
    }

    public void deleteAll(){new deleteAsyncTask(mContacts).execute();}

    public LiveData<List<Contact>> getAllRelationships() {
        return  mAllContacts;
    }

    private static class insertAsyncTask extends AsyncTask<Contact, Void, Void> {

        private ContactDao _AsyncTaskDao;

        insertAsyncTask(ContactDao dao) {
            _AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Contact... params) {
            _AsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private ContactDao _AsyncTaskDao;

        deleteAsyncTask(ContactDao dao){_AsyncTaskDao = dao;}

        @Override
        protected Void doInBackground(Void... voids) {
            _AsyncTaskDao.deleteAll();
            return null;
        }
    }
}

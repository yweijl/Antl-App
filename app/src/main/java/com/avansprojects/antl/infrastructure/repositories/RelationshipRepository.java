package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.avansprojects.antl.infrastructure.daos.EventDao;
import com.avansprojects.antl.infrastructure.daos.RelationshipDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.Event;
import com.avansprojects.antl.infrastructure.entities.Relationship;

import java.util.List;

import androidx.lifecycle.LiveData;

public class RelationshipRepository {

    private RelationshipDao _RelationshipDao;
    private LiveData<List<Relationship>> allRelationships;

    public RelationshipRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        _RelationshipDao = db.relationshipDao();
        allRelationships = _RelationshipDao.getAllRelationships();
    }

    public void insert(Relationship relationship) {
        new insertAsyncTask(_RelationshipDao).execute(relationship);
    }

    public LiveData<List<Relationship>> getAllRelationships() {
        return  allRelationships;
    }

    private static class insertAsyncTask extends AsyncTask<Relationship, Void, Void> {

        private RelationshipDao _AsyncTaskDao;

        insertAsyncTask(RelationshipDao dao) {
            _AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Relationship... params) {
            _AsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

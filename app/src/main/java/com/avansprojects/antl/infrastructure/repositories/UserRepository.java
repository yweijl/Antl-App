package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.infrastructure.daos.UserDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.entities.User;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mUsers;

    public UserRepository(Application application) {
        AntlDatabase db = AntlDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mUsers = mUserDao.getAll();
    }

    public void insert(User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    public LiveData<List<User>> getUsers() {
        Log.i("UserRepository", mUsers.toString());
        return mUsers;
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao _AsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            _AsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            _AsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

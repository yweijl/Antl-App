package com.avansprojects.antl.infrastructure.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.avansprojects.antl.infrastructure.daos.EventDateDao;
import com.avansprojects.antl.infrastructure.database.AntlDatabase;
import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.entities.EventDate;
import com.avansprojects.antl.infrastructure.interfaces.IEventApi;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.RegistrationRequestDTO;
import com.avansprojects.antl.ui.login.services.LoginService;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    public void post(CreateEventDto eventDto) {
        Retrofit retrofit = AntlRetrofit.getRetrofit();

        IEventApi service = retrofit.create(IEventApi.class);
        Call<String> call = service.Post(eventDto);
        retrofit2.Response<String> result = null;
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                java.lang.String result = response.toString();
                Log.d(this.getClass().toString(), "Message received: " + result);
            }
            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });
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
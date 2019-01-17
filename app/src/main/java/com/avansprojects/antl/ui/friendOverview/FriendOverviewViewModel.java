package com.avansprojects.antl.ui.friendOverview;

import android.app.Application;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.avansprojects.antl.AntlApp;
import com.avansprojects.antl.infrastructure.entities.Friend;
import com.avansprojects.antl.infrastructure.repositories.ContactRepository;
import com.avansprojects.antl.retrofit.AntlRetrofit;
import com.avansprojects.antl.ui.login.dto.FriendDto;
import com.avansprojects.antl.ui.login.services.FriendService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FriendOverviewViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private ContactRepository mContactRepository;
    private LiveData<List<Friend>> allContacts;

    public FriendOverviewViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepository(application);
        allContacts = mContactRepository.getAllRelationships();
        RefreshContacts();
    }

    private void RefreshContacts() {
        Retrofit retrofit = AntlRetrofit.getRetrofit();
        SharedPreferences sharedPrefs = AntlApp.getContext().getSharedPreferences("antlPrefs", MODE_PRIVATE);

        FriendService service = retrofit.create(FriendService.class);
        Call<List<FriendDto>> call = service.getFriends("Bearer " + sharedPrefs.getString("token", ""));
        retrofit2.Response<FriendDto> result = null;
        call.enqueue(new Callback<List<FriendDto>>() {
            @Override
            public void onResponse(Call<List<FriendDto>> call, Response<List<FriendDto>> response) {
                String result = response.toString();

                if (response.code() == 200) {
                    mContactRepository.deleteAll();
                    int i = 0;
                    result = response.toString();
                    Log.i("UserResult", result);
                    for (FriendDto friend:response.body()
                         ) {
                        Friend contact = new Friend(i++);
                        contact.userName = friend.getUserName();
                        contact.webServerId = friend.getExternalId();
                        try {
                            mContactRepository.insert(contact);
                        }catch (SQLiteConstraintException e) {
                            Log.e("Contacts", e.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<FriendDto>> call, Throwable throwable) {
                Log.e(this.getClass().toString(), throwable.toString());
            }
        });

    }

    public LiveData<List<Friend>> getAllContacts() {
        return allContacts;
    }

    public void insert(Friend relationship) { mContactRepository.insert(relationship); }
}

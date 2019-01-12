package com.avansprojects.antl.ui.friendOverview;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Contact;
import com.avansprojects.antl.infrastructure.repositories.ContactRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FriendOverviewViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private ContactRepository mContactRepository;
    private LiveData<List<Contact>> allContacts;

    public FriendOverviewViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepository(application);
        allContacts = mContactRepository.getAllRelationships();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public void insert(Contact relationship) { mContactRepository.insert(relationship); }
}

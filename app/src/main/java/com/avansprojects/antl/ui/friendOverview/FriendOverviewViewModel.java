package com.avansprojects.antl.ui.friendOverview;

import android.app.Application;

import com.avansprojects.antl.infrastructure.entities.Relationship;
import com.avansprojects.antl.infrastructure.repositories.RelationshipRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class FriendOverviewViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private RelationshipRepository relationshipRepository;
    private LiveData<List<Relationship>> allRelationships;

    public FriendOverviewViewModel(@NonNull Application application) {
        super(application);
        relationshipRepository = new RelationshipRepository(application);
        allRelationships = relationshipRepository.getAllRelationships();
    }

    public LiveData<List<Relationship>> getAllRelationships() {
        return allRelationships;
    }

    public void insert(Relationship relationship) { relationshipRepository.insert(relationship); }
}

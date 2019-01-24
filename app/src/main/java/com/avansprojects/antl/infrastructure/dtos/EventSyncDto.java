package com.avansprojects.antl.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;

public class EventSyncDto {
    @SerializedName("externalId")
    @ColumnInfo(name = "external_id")
    public String externalId;
    @SerializedName("hash")
    @ColumnInfo(name = "hash")
    public int eventHash;

    public EventSyncDto(String externalId, int eventHash) {
        this.externalId = externalId;
        this.eventHash = eventHash;
    }
}

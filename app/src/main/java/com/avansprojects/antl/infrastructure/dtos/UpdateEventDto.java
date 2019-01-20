package com.avansprojects.antl.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UpdateEventDto {
    public UpdateEventDto() {
        this.externalIdList = new ArrayList<>();
    }

    @SerializedName("externalId")
    public List<String> externalIdList;
}

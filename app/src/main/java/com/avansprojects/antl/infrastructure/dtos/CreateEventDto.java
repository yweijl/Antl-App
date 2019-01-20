
package com.avansprojects.antl.infrastructure.dtos;

import java.util.Date;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CreateEventDto {

    @SerializedName("externalId")
    public String externalId;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("imagePath")
    public String imagePath;
    @SerializedName("mainDateTime")
    public Date mainDateTime;
    @SerializedName("location")
    public String location;
    @SerializedName("eventDates")
    public List<EventDateDto> eventDates;
    @SerializedName("isOwner")
    public boolean isOwner;
    @SerializedName("hash")
    public String hash;

    /**
     * 
     * @param location
     * @param imagePath
     * @param mainDateTime
     * @param description
     * @param name
     * @param eventDates
     * @param externalId
     * @Param hash
     */
    public CreateEventDto(String externalId, String name, String description, String imagePath, Date mainDateTime, String location, List<EventDateDto> eventDates, boolean isOwner, String hash) {
        super();
        this.externalId = externalId;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.mainDateTime = mainDateTime;
        this.location = location;
        this.eventDates = eventDates;
        this.isOwner = isOwner;
        this.hash = hash;
    }

    public CreateEventDto(String name, String description, String imagePath, Date mainDateTime, String location, List<EventDateDto> eventDates, boolean isOwner) {
        super();
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.mainDateTime = mainDateTime;
        this.location = location;
        this.eventDates = eventDates;
        this.isOwner = isOwner;
    }

}

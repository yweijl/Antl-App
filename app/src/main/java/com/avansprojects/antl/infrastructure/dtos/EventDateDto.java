
package com.avansprojects.antl.infrastructure.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class EventDateDto {

    @SerializedName("dateTime")
    public Date dateTime;
    @SerializedName("eventId")
    public Integer eventId;

    /**
     * 
     * @param eventId
     * @param dateTime
     */
    public EventDateDto(Date dateTime, Integer eventId) {
        super();
        this.dateTime = dateTime;
        this.eventId = eventId;
    }

}

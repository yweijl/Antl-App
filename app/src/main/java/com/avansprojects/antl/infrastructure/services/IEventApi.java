package com.avansprojects.antl.infrastructure.services;

import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventDateDto;
import com.avansprojects.antl.infrastructure.dtos.EventSyncDto;
import com.avansprojects.antl.infrastructure.dtos.UpdateEventDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IEventApi {
    @POST("/api/event")
    Call<EventSyncDto> post(@Body CreateEventDto request);

    @GET("/api/event/{id}")
    Call<EventDateDto> get(@Body CreateEventDto request);

    @POST("/api/event/sync/")
    Call<List<CreateEventDto>> syncGetList(@Body UpdateEventDto updateEventDto);

    @GET("/api/event/sync/")
    Call<List<EventSyncDto>> sync();
}

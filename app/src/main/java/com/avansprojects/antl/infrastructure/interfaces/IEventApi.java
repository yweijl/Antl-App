package com.avansprojects.antl.infrastructure.interfaces;

import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.EventDateDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IEventApi {
    @POST("/api/event")
    Call<String> Post(@Body CreateEventDto request);

    @GET("/api/event/{id}")
    Call<EventDateDto> Get(@Body CreateEventDto request);

    @GET("/api/event/get/{id}")
    Call<List<EventDateDto>> GetList(@Body CreateEventDto request);
}

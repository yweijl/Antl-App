package com.avansprojects.antl.listeners;

import com.avansprojects.antl.infrastructure.dtos.EventDateDto;

import java.util.List;

public interface UpdateEventDateListener {
   void updateEventDate(long eventId,  List<EventDateDto> eventDateList);
   void insertEventDate(long eventId, List<EventDateDto> eventDateList);
}

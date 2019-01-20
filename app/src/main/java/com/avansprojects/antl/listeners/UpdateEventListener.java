package com.avansprojects.antl.listeners;

import com.avansprojects.antl.infrastructure.dtos.EventDateDto;
import java.util.List;

public interface UpdateEventListener {
   void updateEventDate(int eventId, List<EventDateDto> eventDateList);
}

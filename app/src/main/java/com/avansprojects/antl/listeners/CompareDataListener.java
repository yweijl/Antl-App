package com.avansprojects.antl.listeners;

import com.avansprojects.antl.infrastructure.dtos.CreateEventDto;
import com.avansprojects.antl.infrastructure.dtos.UpdateEventDto;

public interface CompareDataListener {
    void dataListener(UpdateEventDto updateEventDto);
    void insertEvent(CreateEventDto createEventDto);
    void updateEvent(int id, CreateEventDto createEventDto);
}

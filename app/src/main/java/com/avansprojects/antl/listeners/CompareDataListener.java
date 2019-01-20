package com.avansprojects.antl.listeners;

import com.avansprojects.antl.infrastructure.dtos.UpdateEventDto;

public interface CompareDataListener {
    void dataListener(UpdateEventDto updateEventDto);
}

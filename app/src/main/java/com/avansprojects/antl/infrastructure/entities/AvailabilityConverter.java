package com.avansprojects.antl.infrastructure.entities;

import androidx.room.TypeConverter;

public class AvailabilityConverter {

    @TypeConverter
    public static Availability toAvailabilty(int availability) {
        if (availability == Availability.Attending.getCode()) {
            return Availability.Attending;
        } else if (availability == Availability.NotAttending.getCode()) {
            return Availability.NotAttending;
        } else if (availability == Availability.Maybe.getCode()) {
            return Availability.Maybe;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static int toInteger(Availability availability) {
        return availability.getCode();
    }
}

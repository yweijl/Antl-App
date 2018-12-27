package com.avansprojects.antl.infrastructure.entities;

import androidx.room.TypeConverters;

@TypeConverters(AvailabilityConverter.class)
public enum Availability {

        Attending(0),
        NotAttending(1),
        Maybe(2);

        private int code;

        Availability(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
}

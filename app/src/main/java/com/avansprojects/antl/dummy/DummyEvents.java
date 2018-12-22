package com.avansprojects.antl.dummy;

import java.util.ArrayList;
import java.util.List;

public class DummyEvents {
    private String name;
    private String location;
    private String day;
    private String attendingUsers;

    public DummyEvents() {
    }

    public DummyEvents(String name, String location, String day, String attendingUsers) {
        this.name = name;
        this.location = location;
        this.day = day;
        this.attendingUsers = attendingUsers;
    }

    public List<DummyEvents> GetEventsList(){
        List<DummyEvents> eventsList = new ArrayList<>();

        eventsList.add(new DummyEvents("New years Eve",
                "den haag", "22", "24"));

        eventsList.add(new DummyEvents("berlin trip",
                "Berlin", "5", "4"));

        eventsList.add(new DummyEvents("Birthday",
                "Amsterdam", "12", "89"));

        return eventsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAttendingUsers() {
        return attendingUsers;
    }

    public void setAttendingUsers(String attendingUsers) {
        this.attendingUsers = attendingUsers;
    }
}

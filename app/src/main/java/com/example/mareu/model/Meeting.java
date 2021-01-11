package com.example.mareu.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Meeting implements Serializable {
    private Room room;
    private String subject;
    private Calendar calendar;
    private List<String> participantMailList;

    public Meeting() {
    }

    public Meeting(Room room, String subject, Calendar calendar, List<String> participantMailList) {
        this.room = room;
        this.subject = subject;
        this.calendar = calendar;
        this.participantMailList = participantMailList;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<String> getParticipantMailList() {
        return participantMailList;
    }

    public void setParticipantMailList(List<String> participantMailList) {
        this.participantMailList = participantMailList;
    }
}

package com.example.mareu.model;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Serializable {
    private String place;
    private String subject;
    private int date;
    private int hour;
    private List<String> participantMailList;

    public Meeting() {
    }

    public Meeting(String place, String subject, int date, int hour, List<String> participantMailList) {
        this.place = place;
        this.subject = subject;
        this.date = date;
        this.hour = hour;
        this.participantMailList = participantMailList;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public List<String> getParticipantMailList() {
        return participantMailList;
    }

    public void setParticipantMailList(List<String> participantMailList) {
        this.participantMailList = participantMailList;
    }
}

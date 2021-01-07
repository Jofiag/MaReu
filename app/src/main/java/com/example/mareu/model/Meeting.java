package com.example.mareu.model;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Serializable {
    private String place;
    private String subject;
    private String hour;
    private List<String> participantMailList;

    public Meeting() {
    }

    public Meeting(String place, String subject, String hour, List<String> participantMailList) {
        this.place = place;
        this.subject = subject;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<String> getParticipantMailList() {
        return participantMailList;
    }

    public void setParticipantMailList(List<String> participantMailList) {
        this.participantMailList = participantMailList;
    }
}

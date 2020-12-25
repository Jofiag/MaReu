package com.example.mareu.model;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Serializable {
    private String time;
    private String place;
    private String subject;
    private List<String> participantMailList;

    public Meeting() {
    }

    public Meeting(String time, String place, String subject, List<String> participantMailList) {
        this.time = time;
        this.place = place;
        this.subject = subject;
        this.participantMailList = participantMailList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public List<String> getParticipantMailList() {
        return participantMailList;
    }

    public void setParticipantMailList(List<String> participantMailList) {
        this.participantMailList = participantMailList;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", subject='" + subject + '\'' +
                ", participantMailList=" + participantMailList +
                '}';
    }
}

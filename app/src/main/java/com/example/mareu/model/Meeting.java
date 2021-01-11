package com.example.mareu.model;

import java.io.Serializable;
import java.util.List;

public class Meeting implements Serializable {
    private String subject, time;
    private List<String> participantMailList;
    private int day, month, year, hour, minutes;
    private Room room;

    public Meeting() {
    }

    public Meeting(String subject, String time, List<String> participantMailList) {
        this.subject = subject;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getParticipantMailList() {
        return participantMailList;
    }

    public void setParticipantMailList(List<String> participantMailList) {
        this.participantMailList = participantMailList;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}

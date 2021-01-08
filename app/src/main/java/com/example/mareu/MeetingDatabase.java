package com.example.mareu;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingDatabase {
    private static MeetingDatabase instance;
    private List<Meeting> meetingList = new ArrayList<>();

    public static MeetingDatabase getInstance() {
        if (instance == null)
            instance = new MeetingDatabase();

        return instance;
    }

    public List<Meeting> initiateMeetingList(){
        Meeting meeting1 = new Meeting("Room A", "Finance", "10h30", Arrays.asList("jo@gmail.com", "fi@gmail.com", "ag@gmail.com"));
        Meeting meeting2 = new Meeting("Room B", "Economy", "10h30", Arrays.asList("fi@gmail.com", "gn@gmail.com", "on@gmail.com"));
        Meeting meeting3 = new Meeting("Room C", "Politic", "10h30", Arrays.asList("ah@gmail.com", "ab@gmail.com"));

        meeting1.setDay(8);
        meeting1.setMonth(1);
        meeting1.setYear(2021);

        meeting2.setDay(9);
        meeting2.setMonth(1);
        meeting2.setYear(2021);

        meetingList.add(meeting1);
        meetingList.add(meeting2);
        meetingList.add(meeting3);

        return meetingList;
    }

    public List<Meeting> getMeetingList() {
        return meetingList;
    }

    public void setMeetingList(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }
}

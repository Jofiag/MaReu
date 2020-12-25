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
        Meeting meeting1 = new Meeting("08h30", "Room A", "Finance", Arrays.asList("jo@gmail.com", "fi@gmail.com", "ag@gmail.com"));
        Meeting meeting2 = new Meeting("09h30", "Room B", "Economy", Arrays.asList("fi@gmail.com", "gn@gmail.com", "on@gmail.com"));
        Meeting meeting3 = new Meeting("10h30", "Room C", "Politic", Arrays.asList("ah@gmail.com", "ab@gmail.com"));
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

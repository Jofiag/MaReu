package com.example.mareu;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.fragment.MeetingListFragment.ROOM_A;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_B;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_C;

public class MeetingDatabase {
    private static MeetingDatabase instance;
    private List<Meeting> meetingList = new ArrayList<>();

    public static MeetingDatabase getInstance() {
        if (instance == null)
            instance = new MeetingDatabase();

        return instance;
    }

    public List<Meeting> initiateMeetingList(){
        Meeting meeting1 = new Meeting("Finance", "10h30", Arrays.asList("jo@gmail.com", "fi@gmail.com", "ag@gmail.com"));
        Meeting meeting2 = new Meeting("Economy", "10h30", Arrays.asList("fi@gmail.com", "gn@gmail.com", "on@gmail.com"));
        Meeting meeting3 = new Meeting("Politic", "10h30", Arrays.asList("ah@gmail.com", "ab@gmail.com"));

        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int tomorrow = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1;

        meeting1.setRoom(new Room(ROOM_A));
        meeting2.setRoom(new Room(ROOM_B));
        meeting3.setRoom(new Room(ROOM_C));

        meeting1.setDay(currentDay);
        meeting1.setMonth(1);
        meeting1.setYear(2021);

        meeting2.setDay(tomorrow);
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

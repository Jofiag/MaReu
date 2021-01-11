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
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        Meeting meeting1 = new Meeting(new Room(ROOM_A),"Finance", calendar, Arrays.asList("jo@gmail.com", "fi@gmail.com", "ag@gmail.com"));

        calendar.set(Calendar.DAY_OF_MONTH, currentDay + 1);
        Meeting meeting2 = new Meeting(new Room(ROOM_B), "Economy", calendar, Arrays.asList("fi@gmail.com", "gn@gmail.com", "on@gmail.com"));

        calendar.set(Calendar.DAY_OF_MONTH, currentDay + 2);
        Meeting meeting3 = new Meeting(new Room(ROOM_B), "Politic", calendar, Arrays.asList("fi@gmail.com", "gn@gmail.com", "on@gmail.com"));

        meeting1.setRoom(new Room(ROOM_A));
        meeting2.setRoom(new Room(ROOM_B));
        meeting3.setRoom(new Room(ROOM_C));

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

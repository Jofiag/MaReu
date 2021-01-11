package com.example.mareu.api;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyMethodsApi{
    private static final MeetingDatabase db = MeetingDatabase.getInstance();

    public static void addMeeting(Meeting meeting){
        db.getMeetingList().add(meeting);
    }

    public static void deleteMeeting(Meeting meeting){
        db.getMeetingList().remove(meeting);
    }

    public static List<Meeting> selectMeetingByRoom(String room){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : db.getMeetingList()){
            if (meeting.getRoom().getRoomText().equals(room))
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectMeetingByDate(int day){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : db.getMeetingList()){
            boolean meetingIsToday = meeting.getCalendar().get(Calendar.DAY_OF_MONTH) == day;
            if (meetingIsToday)
                filteredList.add(meeting);
        }

        return filteredList;
    }


}

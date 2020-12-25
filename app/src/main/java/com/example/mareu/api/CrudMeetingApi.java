package com.example.mareu.api;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class CrudMeetingApi {
    private static final MeetingDatabase db = MeetingDatabase.getInstance();
    private static final List<Meeting> meetingList = db.getMeetingList();

    public static void addMeeting(Meeting meeting){
        meetingList.add(meeting);
        db.setMeetingList(meetingList);
    }

    public static void deleteMeeting(Meeting meeting){
        meetingList.remove(meeting);
        db.setMeetingList(meetingList);
    }
}

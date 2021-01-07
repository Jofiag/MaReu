package com.example.mareu.api;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyMethodsApi {
    private static final MeetingDatabase db = MeetingDatabase.getInstance();

    public static void addMeeting(List<Meeting> meetingList, Meeting meeting){
        meetingList.add(meeting);
        db.setMeetingList(meetingList);
    }

    public static void deleteMeeting(List<Meeting> meetingList, Meeting meeting){
        meetingList.remove(meeting);
        db.setMeetingList(meetingList);
    }

    public static void sortMeetingListByPlace(List<Meeting> meetingList){
        Collections.sort(meetingList, (meeting1, meeting2) -> meeting1.getPlace().compareTo(meeting2.getPlace()));
        db.setMeetingList(meetingList);
    }
}

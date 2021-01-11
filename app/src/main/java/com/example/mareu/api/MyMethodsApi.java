package com.example.mareu.api;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.fragment.MeetingListFragment.ROOM_A;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_B;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_C;

public class MyMethodsApi{
    private static final MeetingDatabase db = MeetingDatabase.getInstance();

    public static void addMeeting(List<Meeting> meetingList, Meeting meeting){
        meetingList.add(meeting);
        db.setMeetingList(meetingList);
    }

    public static void deleteMeeting(List<Meeting> meetingList, Meeting meeting){
        meetingList.remove(meeting);
        db.setMeetingList(meetingList);
    }

    public static List<Meeting> selectMeetingInRoomA(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : meetingList){
            if (meeting.getRoom().getRoomText().equals(ROOM_A))
                filteredList.add(meeting);
        }

        /* OR
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            meetingList.stream()
                    .filter(meeting -> meeting.getPlace().equals("Room A"))
                    .forEach(filteredList::add);
        }*/

        return filteredList;
    }

    public static List<Meeting> selectMeetingInRoomB(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : meetingList){
            if (meeting.getRoom().getRoomText().equals(ROOM_B))
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectMeetingInRoomC(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : meetingList){
            if (meeting.getRoom().getRoomText().equals(ROOM_C))
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectTodayMeeting(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        for (Meeting meeting : meetingList){
            boolean meetingIsToday = meeting.getDay() == currentDay;
            if (meetingIsToday)
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectTomorrowMeeting(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);


        for (Meeting meeting : meetingList){
            boolean meetingIsTomorrow = meeting.getDay() == currentDay+1;
            if (meetingIsTomorrow)
                filteredList.add(meeting);
        }

        return filteredList;
    }


}

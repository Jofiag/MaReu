package com.example.mareu.api;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Calendar;
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

    public static List<Meeting> selectMeetingInRoomA(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : meetingList){
            if (meeting.getPlace().equals("Room A"))
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
            if (meeting.getPlace().equals("Room B"))
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectMeetingInRoomC(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        for (Meeting meeting : meetingList){
            if (meeting.getPlace().equals("Room C"))
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectAllMeeting(List<Meeting> meetingList){
        return meetingList;
    }

    public static List<Meeting> selectTodayMeeting(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int actualDay = calendar.get(Calendar.DAY_OF_MONTH);
        int actualMonth = calendar.get(Calendar.MONTH);
        int actualYear = calendar.get(Calendar.YEAR);


        for (Meeting meeting : meetingList){
            boolean today = meeting.getDay() == actualDay && meeting.getMonth() == actualMonth && meeting.getYear() == actualYear;
            if (today)
                filteredList.add(meeting);
        }

        return filteredList;
    }

    public static List<Meeting> selectTomorrowMeeting(List<Meeting> meetingList){
        List<Meeting> filteredList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int actualDay = calendar.get(Calendar.DAY_OF_MONTH);
        int actualMonth = calendar.get(Calendar.MONTH);
        int actualYear = calendar.get(Calendar.YEAR);


        for (Meeting meeting : meetingList){
            boolean tomorrow = meeting.getDay() == actualDay+1 && meeting.getMonth() == actualMonth && meeting.getYear() == actualYear;
            if (tomorrow)
                filteredList.add(meeting);
        }

        return filteredList;
    }

    /*public static List<Meeting> meetingSelection(List<Meeting> meetingList, String status){
        List<Meeting> filteredList;

        switch (status){
            case "Room A":
                filteredList = MyMethodsApi.selectMeetingInRoomA(meetingList);
                break;
            case "Room B":
                filteredList = MyMethodsApi.selectMeetingInRoomB(meetingList);
                break;
            case "Room C":
                filteredList = MyMethodsApi.selectMeetingInRoomC(meetingList);
                break;
            default:
                filteredList = MyMethodsApi.selectAllMeeting(meetingList);
                break;
        }

        return filteredList;
    }*/
}

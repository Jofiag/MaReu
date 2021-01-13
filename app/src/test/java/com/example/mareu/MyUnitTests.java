package com.example.mareu;

import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.fragment.MeetingListFragment.ROOM_A;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_B;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_C;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyUnitTests {
    private final MeetingDatabase db = MeetingDatabase.getInstance();

    @Test
    public void addMeetingIsSuccessful(){
        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Calendar calendar = Calendar.getInstance();

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_B));
        meeting.setCalendar(calendar);
        meeting.setSubject("Android developer");
        meeting.setParticipantMailList(emailList);

        MyMethodsApi.addMeeting(meeting);

        assertTrue(db.getMeetingList().contains(meeting));
    }

    @Test
    public void deleteMeetingIsSuccessful(){
        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Calendar calendar = Calendar.getInstance();

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_B));
        meeting.setCalendar(calendar);
        meeting.setSubject("Android developer");
        meeting.setParticipantMailList(emailList);

        int firstSize = db.getMeetingList().size();

        MyMethodsApi.addMeeting(meeting);
        assertEquals(firstSize + 1, db.getMeetingList().size());

        MyMethodsApi.deleteMeeting(meeting);
        assertEquals(firstSize, db.getMeetingList().size());
    }

    @Test
    public void filterMeetingListIsSuccessful(){

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int tomorrow = currentDay + 1;

        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Meeting meetingA = new Meeting();
        meetingA.setRoom(new Room(ROOM_A));
        meetingA.setCalendar(calendar);
        meetingA.setSubject("Android developer");
        meetingA.setParticipantMailList(emailList);

        Calendar calendarB = Calendar.getInstance();
        calendarB.set(Calendar.DAY_OF_MONTH, tomorrow);
        Meeting meetingB = new Meeting();
        meetingB.setRoom(new Room(ROOM_B));
        meetingB.setCalendar(calendarB);
        meetingB.setSubject("Android developer");
        meetingB.setParticipantMailList(emailList);

        Meeting meetingC = new Meeting();
        meetingC.setRoom(new Room(ROOM_C));
        meetingC.setCalendar(calendarB);
        meetingC.setSubject("Android developer");
        meetingC.setParticipantMailList(emailList);

        MyMethodsApi.deleteAllMeeting();

        MyMethodsApi.addMeeting(meetingA);
        MyMethodsApi.addMeeting(meetingB);
        MyMethodsApi.addMeeting(meetingC);

        List<Meeting> meetingListFiltered;

        //Checking filter with Room
        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_A);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_B);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_C);
        assertTrue(meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 1);

        //Checking filter with Date
        assertEquals(meetingA.getCalendar().get(Calendar.DAY_OF_MONTH), currentDay);

        meetingListFiltered = MyMethodsApi.selectMeetingByDate(currentDay);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByDate(tomorrow);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 2);
    }
}

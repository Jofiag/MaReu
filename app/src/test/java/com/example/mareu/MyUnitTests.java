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
import static org.junit.Assert.*;

public class MyUnitTests {

    @Test
    public void addMeetingIsSuccessful(){
        List<Meeting> meetingList = new ArrayList<>();

        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_B));
        meeting.setTime("11h00");
        meeting.setSubject("Android developer");
        meeting.setParticipantMailList(emailList);

        MyMethodsApi.addMeeting(meetingList, meeting);

        assertTrue(meetingList.contains(meeting));
    }

    @Test
    public void deleteMeetingIsSuccessful(){
        List<Meeting> meetingList = new ArrayList<>();

        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_A));
        meeting.setTime("11h00");
        meeting.setSubject("Android developer");
        meeting.setParticipantMailList(emailList);

        int firstSize = meetingList.size();

        MyMethodsApi.addMeeting(meetingList, meeting);
        assertEquals(1, meetingList.size());

        MyMethodsApi.deleteMeeting(meetingList, meeting);
        assertEquals(firstSize, meetingList.size());
    }

    @Test
    public void filterMeetingListIsSuccessful(){
        List<Meeting> meetingList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int tomorrow = currentDay + 1;

        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Meeting meetingA = new Meeting();
        meetingA.setRoom(new Room(ROOM_A));
        meetingA.setTime("11h00");
        meetingA.setSubject("Android developer");
        meetingA.setParticipantMailList(emailList);
        meetingA.setDay(currentDay);
        meetingA.setMonth(1);
        meetingA.setYear(2021);

        Meeting meetingB = new Meeting();
        meetingB.setRoom(new Room(ROOM_B));
        meetingB.setTime("11h00");
        meetingB.setSubject("Android developer");
        meetingB.setParticipantMailList(emailList);
        meetingB.setDay(tomorrow);
        meetingB.setMonth(1);
        meetingB.setYear(2021);

        Meeting meetingC = new Meeting();
        meetingC.setRoom(new Room(ROOM_C));
        meetingC.setTime("11h00");
        meetingC.setSubject("Android developer");
        meetingC.setParticipantMailList(emailList);
        meetingC.setDay(tomorrow);
        meetingC.setMonth(1);
        meetingC.setYear(2021);

        meetingList.add(meetingB);
        meetingList.add(meetingC);
        meetingList.add(meetingA);
        List<Meeting> meetingListFiltered;

        //Checking filter with Room
        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_A);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_B);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_C);
        assertTrue(meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 1);

        //Checking filter with Date
        meetingListFiltered = MyMethodsApi.selectTodayMeeting(meetingList);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectTomorrowMeeting(meetingList);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 2);
    }
}

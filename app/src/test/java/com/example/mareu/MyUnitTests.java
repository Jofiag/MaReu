package com.example.mareu;

import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.fragment.MeetingListFragment.CURRENT_DAY;
import static com.example.mareu.fragment.MeetingListFragment.NEXT_DAY;
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

        Calendar calendar = Calendar.getInstance();

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_B));
        meeting.setCalendar(calendar);
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

        Calendar calendar = Calendar.getInstance();

        Meeting meeting = new Meeting();
        meeting.setRoom(new Room(ROOM_B));
        meeting.setCalendar(calendar);
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

        calendar.set(Calendar.DAY_OF_MONTH, tomorrow);
        Meeting meetingB = new Meeting();
        meetingB.setRoom(new Room(ROOM_B));
        meetingB.setCalendar(calendar);
        meetingB.setSubject("Android developer");
        meetingB.setParticipantMailList(emailList);

        calendar.set(Calendar.DAY_OF_MONTH, tomorrow + 1);
        Meeting meetingC = new Meeting();
        meetingC.setRoom(new Room(ROOM_C));
        meetingC.setSubject("Android developer");
        meetingC.setParticipantMailList(emailList);

        MyMethodsApi.addMeeting(MeetingDatabase.getInstance().getMeetingList(), meetingA);
        MyMethodsApi.addMeeting(MeetingDatabase.getInstance().getMeetingList(), meetingB);
        MyMethodsApi.addMeeting(MeetingDatabase.getInstance().getMeetingList(), meetingC);

        List<Meeting> meetingListFiltered;

        //Checking filter with Room
        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_A);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_B);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByRoom(ROOM_C);
        assertTrue(meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 1);

        //Checking filter with Date
        meetingListFiltered = MyMethodsApi.selectMeetingByDate(CURRENT_DAY);
        assertTrue(meetingListFiltered.contains(meetingA) && meetingListFiltered.size() == 1);

        meetingListFiltered = MyMethodsApi.selectMeetingByDate(NEXT_DAY);
        assertTrue(meetingListFiltered.contains(meetingB) && meetingListFiltered.contains(meetingC) && meetingListFiltered.size() == 2);
    }
}

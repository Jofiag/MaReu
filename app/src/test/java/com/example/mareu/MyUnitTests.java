package com.example.mareu;

import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.model.Meeting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        meeting.setPlace("Room 1");
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
        meeting.setPlace("Room 1");
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
    public void sortMeetingListIsSuccessful(){
        List<Meeting> meetingList = new ArrayList<>();

        List<String> emailList = new ArrayList<>();
        emailList.add("kevin@gmail.com");
        emailList.add("jeremy@yahoo.fr");
        emailList.add("mark@sfr.fr");

        Meeting meetingA = new Meeting();
        meetingA.setPlace("Room A");
        meetingA.setTime("11h00");
        meetingA.setSubject("Android developer");
        meetingA.setParticipantMailList(emailList);

        Meeting meetingB = new Meeting();
        meetingB.setPlace("Room B");
        meetingB.setTime("11h00");
        meetingB.setSubject("Android developer");
        meetingB.setParticipantMailList(emailList);

        Meeting meetingC = new Meeting();
        meetingC.setPlace("Room C");
        meetingC.setTime("11h00");
        meetingC.setSubject("Android developer");
        meetingC.setParticipantMailList(emailList);

        meetingList.add(meetingB);
        meetingList.add(meetingC);
        meetingList.add(meetingA);

        assertTrue(meetingList.get(0) == meetingB && meetingList.get(1) == meetingC && meetingList.get(2) == meetingA);

        MyMethodsApi.sortMeetingListByPlace(meetingList);
        assertTrue(meetingList.get(0) == meetingA && meetingList.get(1) == meetingB && meetingList.get(2) == meetingC);
    }
}

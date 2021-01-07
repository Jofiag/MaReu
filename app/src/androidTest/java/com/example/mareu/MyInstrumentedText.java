package com.example.mareu;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mareu.controler.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MyInstrumentedText {

    private String room;
    private String time;
    private String subject;
    private String email;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRules = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidString(){
        //Specifying some valid strings
        room = "Room A";
        time = "11h";
        subject = "AI";
        email = "jofiag@gmail.com";
    }

    @Test
    public void addMeetingIsSuccessful(){
        //Check if the recycler is empty

        onView(withId(R.id.floating_action_button)).perform(click());
        onView(withId(R.id.room_edit_text)).perform(typeText(room), closeSoftKeyboard());
        onView(withId(R.id.time_edit_text)).perform(typeText(time), closeSoftKeyboard());
        onView(withId(R.id.subject_edit_text)).perform(typeText(subject), closeSoftKeyboard());

        onView(withId(R.id.add_participant_email_button)).perform(click());
        onView(withId(R.id.participant_emails_edit_text)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.save_participant_button)).perform(click());
        onView(withId(R.id.save_meeting_button)).perform(click());

        onView(withId(R.id.show_meeting_list_button)).perform(click());

        //Check if the recyclerView contains at least on element
    }

    @Test
    public void deleteMeetingIsSuccessful(){

    }

    @Test
    public void sortMeetingListIsSuccessful(){

    }

}

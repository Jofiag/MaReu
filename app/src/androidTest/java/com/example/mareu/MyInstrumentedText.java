package com.example.mareu;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mareu.controler.MainActivity;
import com.example.mareu.utils.AssertRecyclerItemCount;
import com.example.mareu.utils.MyViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MyInstrumentedText {
    private String subject1, subject2, email1, email2;
    private int currentDay, tomorrow, currentMonth, currentYear;
    private int hour, minute;
    private int roomASwitchId, roomBSwitchId, roomCSwitchId;
    private int defaultRecyclerViewSize;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRules = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidString(){
        Calendar calendar = Calendar.getInstance();

        //Specifying some valid variable
        subject1 = "Android";
        subject2 = "iOS";

        email1 = "jofiag@gmail.com";
        email2 = "pierre@yahoo.fr";

        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        tomorrow = currentDay + 1;
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        hour = 10;
        minute = 30;

        roomASwitchId = R.id.room_a_switch;
        roomBSwitchId = R.id.room_b_switch;
        roomCSwitchId = R.id.room_c_switch;

        defaultRecyclerViewSize = 3;
    }

    @Test
    public void addMeetingIsSuccessful(){
        //Check if the recyclerView contains 3 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        addMeetingProcess(subject1, email1, currentDay, roomASwitchId);

        //Check if the recyclerView contains at least 4 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize + 1);
    }

    @Test
    public void deleteMeetingIsSuccessful(){
        //Check if the recyclerView contains 3 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        //delete a meeting process
        deleteMeetingProcess();

        //Check if the recyclerView contains 2 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize - 1);

    }

    @Test
    public void filterMeetingListIsSuccessful(){
        addMeetingProcess(subject1, email1, currentDay, roomCSwitchId);
        addMeetingProcess(subject2, email2, tomorrow, roomBSwitchId);

        //Check if the recyclerView contains 3 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        //filter with one room process (select meeting in room A)

        //Check if the recyclerView contains 1 element
        checkRecyclerViewItemCount(1);

        //filter with allRoom/allDate process (select all meeting)

        //Check if the recyclerView contains 3 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        //filter with one date process (select tomorrow meeting)

        //Check if the recyclerView contains 2 element
        checkRecyclerViewItemCount(2);
    }

    private void checkRecyclerViewItemCount(int countExpected){
        onView(withId(R.id.my_recyclerview)).check(new AssertRecyclerItemCount(countExpected));
    }

    private void addMeetingProcess(String subject, String email, int day, int roomSwitchId){
        //Start AddMeetingActivity
        onView(withId(R.id.floating_action_button)).perform(click());

        //Write "Android" in the subject edit text
        onView(withId(R.id.subject_edit_text)).perform(replaceText(subject), closeSoftKeyboard());

        //Click on add participant email button
        onView(withId(R.id.add_participant_email_button)).perform(click());

        //Enter "jofiag@gmail.com" in the email edit text
        onView(withId(R.id.participant_emails_edit_text)).perform(replaceText(email), closeSoftKeyboard());

        //Save the email entered above by clicking on the save email button
        onView(withId(R.id.save_participant_button)).perform(click());

        //Click on the set date button
        onView(withId(R.id.set_date_button)).perform(click());

        //set the date with the displayed DatePicker and then click on the "ok" button to confirm
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(currentYear, currentMonth, day));
        onView(withId(android.R.id.button1)).perform(click());

        //Click on the set hour button
        onView(withId(R.id.set_hour_button)).perform(click());

        //set the time with the displayed TimePicker and then click on the "ok" button to confirm
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minute));
        onView(withId(android.R.id.button1)).perform(click());

        //Set the meeting roomSwitchId
        onView(withId(roomSwitchId)).perform(click());

        //Click on the save meeting button to save the meeting and show the list
        onView(withId(R.id.save_meeting_button)).perform(click());
    }

    private void deleteMeetingProcess(){
        //Click on the delete button of the third item (delete the third meeting)
        onView(withId(R.id.my_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(2, MyViewAction.clickChildViewWithId(R.id.delete_image_button)));
    }

}

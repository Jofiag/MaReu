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
import static androidx.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MyInstrumentedText {
    private String subject1, email1;
    private String roomFilterText, dateFilterText, customizeText, roomAText, allRoomText, tomorrowText;
    private int currentDay, currentMonth, currentYear;
    private int hour, minute;
    private int roomASwitchId, roomBSwitchId;
    private int defaultRecyclerViewSize;

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRules = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initValidString(){
        Calendar calendar = Calendar.getInstance();

        //Specifying some valid variable
        subject1 = "Android";

        email1 = "jofiag@gmail.com";

        roomFilterText = "Room filter";
        dateFilterText = "Date filter";
        customizeText = "Customize";
        roomAText = "Room A";
        allRoomText = "All Room";
        tomorrowText = "Tomorrow";

        currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        currentMonth = calendar.get(Calendar.MONTH);
        currentYear = calendar.get(Calendar.YEAR);

        hour = 10;
        minute = 30;

        roomASwitchId = R.id.room_a_switch;
        roomBSwitchId = R.id.room_b_switch;

        defaultRecyclerViewSize = 0;
    }

    @Test
    public void addMeetingIsSuccessful(){
        //Check if the recyclerView contains 0 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        addMeetingProcess(subject1, email1, currentDay, roomASwitchId);

        //Check if the recyclerView contains at least 1 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize + 1);
    }

    @Test
    public void deleteMeetingIsSuccessful(){
        //Check if the recyclerView contains 0 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        addMeetingProcess(subject1, email1, currentDay, roomASwitchId);

        //Check if the recyclerView contains at least 1 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize + 1);

        deleteMeetingProcess();

        //Check if the recyclerView contains 0 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

    }

    @Test
    public void filterMeetingListIsSuccessful(){
        //Check if the recyclerView contains 0 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize);

        addMeetingProcess(subject1, email1, currentDay, roomASwitchId);
        addMeetingProcess(subject1, email1, currentDay + 1, roomBSwitchId);

        //Check if the recyclerView contains 1 element
        checkRecyclerViewItemCount(defaultRecyclerViewSize + 2);

        //filter with one room process (select meeting in room A)
        filterProcess(roomFilterText, roomAText);

        //Check if the recyclerView contains 1 element
        checkRecyclerViewItemCount(1);


        //filter with allRoom/allDate process (select all meeting)
        filterProcess(roomFilterText, allRoomText);

        //Check if the recyclerView contains 2 elements added above
        checkRecyclerViewItemCount(defaultRecyclerViewSize + 2);


        //filter with one date process (select tomorrow meeting)
        filterProcess(dateFilterText, tomorrowText);

        //Check if the recyclerView contains 1 element
        checkRecyclerViewItemCount(1);

        filterProcess(dateFilterText, customizeText);

        //Check if the recyclerView contains 1 element
        checkRecyclerViewItemCount(1);
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
        onView(withId(R.id.my_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.delete_image_button)));
    }

    private void filterProcess(String typeOfStatus, String status){
        openContextualActionModeOverflowMenu();         //Open the menu
        onView(withText(typeOfStatus)).perform(click());
        onView(withText(status)).perform(click());

        if (status.equals(customizeText)){
            //set the date with the displayed DatePicker and then click on the "ok" button to confirm
            onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(currentYear, currentMonth, currentDay));
            onView(withId(android.R.id.button1)).perform(click());
        }


    }

}

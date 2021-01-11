package com.example.mareu.controler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mareu.R;
import com.example.mareu.adapter.MeetingListRecyclerViewAdapter;
import com.example.mareu.fragment.MeetingDetailsFragment;
import com.example.mareu.fragment.MeetingListFragment;
import com.example.mareu.model.Meeting;

public class MainActivity extends AppCompatActivity implements MeetingListRecyclerViewAdapter.OnMeetingClickListener {
    public static final String MEETING_SELECTED_CODE = "meeting selected";
    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMeetingList();
        attachMeetingListFragment();
        setDualPane();
    }

    private void getMeetingList() {
    }

    private void attachNewFragment(Fragment newFragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }

    private void attachMeetingListFragment(){
        Fragment fragment = new MeetingListFragment();

        attachNewFragment(fragment, R.id.meeting_list_fragment_container);
    }

    private void attachMeetingDetailsFragment(){
        Fragment fragment = new MeetingDetailsFragment();

        attachNewFragment(fragment, R.id.meeting_details_fragment_container);
    }

    private void setDualPane(){
        isDualPane = findViewById(R.id.meeting_details_fragment_container) != null;
    }

    @Override
    public void onMeetingSelected(Meeting meeting) {
        if (isDualPane)
            attachMeetingDetailsFragment();
    }
}
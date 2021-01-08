package com.example.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.fragment.AddMeetingFragment;
import com.example.mareu.model.Meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.fragment.AddMeetingFragment.MEETING_LIST_CODE;

public class AddMeetingActivity extends AppCompatActivity {
    private List<Meeting> meetingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        meetingList = MeetingDatabase.getInstance().getMeetingList();
        attachNewFragment(new AddMeetingFragment(), R.id.add_meeting_fragment_container);
    }

    public void attachNewFragment(Fragment newFragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }
}
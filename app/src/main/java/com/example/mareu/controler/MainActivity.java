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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.fragment.AddMeetingFragment.MEETING_LIST_CODE;

public class MainActivity extends AppCompatActivity implements MeetingListRecyclerViewAdapter.OnMeetingClickListener {
    public static final String MEETING_SELECTED_CODE = "meeting selected";
    private boolean isDualPane;
    private List<Meeting> meetingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMeetingListFromAddMeetingActivity();
        attachMeetingListFragment();
        setDualPane();
    }

    private void getMeetingListFromAddMeetingActivity() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            meetingList = (List<Meeting>) bundle.getSerializable(MEETING_LIST_CODE);
    }

    private void attachNewFragment(Fragment newFragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }

    private void attachMeetingListFragment(){
        Fragment fragment = new MeetingListFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MEETING_LIST_CODE, (Serializable) meetingList);
        fragment.setArguments(bundle);

        attachNewFragment(fragment, R.id.meeting_list_fragment_container);
    }

    private void attachMeetingDetailsFragment(Meeting meetingSelected){
        Fragment fragment = new MeetingDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MEETING_SELECTED_CODE, meetingSelected);
        fragment.setArguments(bundle);

        attachNewFragment(fragment, R.id.meeting_details_fragment_container);
    }

    private void setDualPane(){
        isDualPane = findViewById(R.id.meeting_details_fragment_container) != null;
    }

    @Override
    public void onMeetingSelected(Meeting meeting) {
        if (isDualPane)
            attachMeetingDetailsFragment(meeting);
    }
}
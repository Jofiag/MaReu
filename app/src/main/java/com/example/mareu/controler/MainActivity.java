package com.example.mareu.controler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mareu.R;
import com.example.mareu.adapter.MeetingListRecyclerViewAdapter;
import com.example.mareu.fragment.MeetingDetailsFragment;
import com.example.mareu.fragment.MeetingListFragment;
import com.example.mareu.model.Meeting;
import com.example.mareu.utils.Utils;

public class MainActivity extends AppCompatActivity implements MeetingListRecyclerViewAdapter.OnMeetingClickListener {
    public static final String MEETING_SELECTED_CODE = "meeting selected";
    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utils.attachNewFragment(new MeetingListFragment(), R.id.meeting_list_fragment_container);
        setDualPane();

    }

    private void attachMeetingDetailsFragment(Meeting meetingSelected){
        Fragment fragment = new MeetingDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MEETING_SELECTED_CODE, meetingSelected);
        fragment.setArguments(bundle);

        Utils.attachNewFragment(fragment, R.id.meeting_details_fragment_container);
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
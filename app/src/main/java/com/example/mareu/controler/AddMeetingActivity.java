package com.example.mareu.controler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mareu.R;
import com.example.mareu.fragment.AddMeetingFragment;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        attachNewFragment(new AddMeetingFragment(), R.id.add_meeting_fragment_container);
    }

    public void attachNewFragment(Fragment newFragment, int container) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }
}
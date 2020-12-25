package com.example.mareu.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mareu.R;
import com.example.mareu.fragment.MeetingDetailsFragment;
import com.example.mareu.fragment.MeetingListFragment;

public class MainActivity extends AppCompatActivity {
    private boolean isDualPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachNewFragment(new MeetingListFragment(), R.id.meeting_list_fragment_container);
        setDualPane();

        if (isDualPane)
            attachNewFragment(new MeetingDetailsFragment(), R.id.meeting_details_fragment_container);

    }

    private void attachNewFragment(Fragment newFragment, int container){
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(container, newFragment);
        fragmentTransaction.commit();
    }

    private void setDualPane(){
        if (findViewById(R.id.meeting_details_fragment_container) != null)
            isDualPane = true;
        else
            isDualPane = false;
    }
}
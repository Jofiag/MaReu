package com.example.mareu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.adapter.MeetingListRecyclerViewAdapter;
import com.example.mareu.controler.AddMeetingActivity;
import com.example.mareu.model.Meeting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeetingListFragment extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    public MeetingListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);

        setReference(view);
        setRecyclerView(view);
        startAddMeetingActivity(view);

        return view;
    }

    private void setReference(View view){
        recyclerView = view.findViewById(R.id.my_recyclerview);
        fab = view.findViewById(R.id.floating_action_button);
    }

    private void setRecyclerView(View view){
        Context context = view.getContext();
        List<Meeting> meetingList = MeetingDatabase.getInstance().initiateMeetingList();
        MeetingListRecyclerViewAdapter adapter = new MeetingListRecyclerViewAdapter(context, meetingList);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void startAddMeetingActivity(View view){
        fab.setOnClickListener(v -> startActivity(new Intent(view.getContext(), AddMeetingActivity.class)));
    }
}
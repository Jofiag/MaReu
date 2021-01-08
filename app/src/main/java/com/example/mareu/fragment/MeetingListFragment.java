package com.example.mareu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.adapter.MeetingListRecyclerViewAdapter;
import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.controler.AddMeetingActivity;
import com.example.mareu.model.Meeting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.fragment.AddMeetingFragment.MEETING_LIST_CODE;

public class MeetingListFragment extends Fragment {
    public static final String TOMORROW = "Tomorrow";
    public static final String TODAY = "Today";
    public static final String ALL_ROOM = "All Room";
    public static final String ROOM_C = "Room C";
    public static final String ROOM_B = "Room B";
    public static final String ROOM_A = "Room A";
    public static final String ALL_DATE = "All Date";

    private List<Meeting> meetingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MeetingListRecyclerViewAdapter adapter;
    private FloatingActionButton fab;

    public MeetingListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_meeting, container, false);

        meetingList = MeetingDatabase.getInstance().getMeetingList();
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
        adapter = new MeetingListRecyclerViewAdapter(context, meetingList);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    private void startAddMeetingActivity(View view){
        Intent intent = new Intent(view.getContext(), AddMeetingActivity.class);

        fab.setOnClickListener(v -> {
            startActivity(intent);
            getActivity().finish();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.meeting_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*@Override
    public void onOptionsMenuClosed(@NonNull Menu menu) {
        super.onOptionsMenuClosed(menu);

        MenuItem roomA = menu.findItem(R.id.room_a_select_item);
        MenuItem roomB = menu.findItem(R.id.room_b_select_item);
        MenuItem roomC = menu.findItem(R.id.room_c_select_item);
        MenuItem allRoom = menu.findItem(R.id.all_room_select_item);

        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(roomA);
        itemList.add(roomB);
        itemList.add(roomC);
        itemList.add(allRoom);

        List<MenuItem> itemCheckedList = new ArrayList<>();

        for (MenuItem item : itemList){
            if (item.isChecked())
                itemCheckedList.add(item);
        }

        List<Meeting> finalFilteredList = new ArrayList<>();
        for (MenuItem item : itemCheckedList) {
            List<Meeting> filteredList = MyMethodsApi.meetingSelection(meetingList, item.getTitle().toString());

            finalFilteredList.addAll(filteredList);
        }

        adapter = new MeetingListRecyclerViewAdapter(getActivity(), finalFilteredList);
        recyclerView.setAdapter(adapter);

    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.room_a_select_item)
            filterMeetingList(ROOM_A);
        else if (item.getItemId() == R.id.room_b_select_item)
            filterMeetingList(ROOM_B);
        else if (item.getItemId() == R.id.room_c_select_item)
            filterMeetingList(ROOM_C);
        else if (item.getItemId() == R.id.all_room_select_item)
            filterMeetingList(ALL_ROOM);
        else if (item.getItemId() == R.id.today_select_item)
            filterMeetingList(TODAY);
        else if (item.getItemId() == R.id.tomorrow_select_item)
            filterMeetingList(TOMORROW);
        else if (item.getItemId() == R.id.all_date_select_item)
            filterMeetingList(ALL_DATE);

        return super.onOptionsItemSelected(item);
    }

    private void filterMeetingList(String status){
            List<Meeting> filteredList;

        switch (status) {
            case ROOM_A:
                filteredList = MyMethodsApi.selectMeetingInRoomA(meetingList);
                break;
            case ROOM_B:
                filteredList = MyMethodsApi.selectMeetingInRoomB(meetingList);
                break;
            case ROOM_C:
                filteredList = MyMethodsApi.selectMeetingInRoomC(meetingList);
                break;
            case TODAY:
                filteredList = MyMethodsApi.selectTodayMeeting(meetingList);
                break;
            case TOMORROW:
                filteredList = MyMethodsApi.selectTomorrowMeeting(meetingList);
                break;
            default:
                filteredList = meetingList;
                break;
        }

            adapter = new MeetingListRecyclerViewAdapter(getActivity(), filteredList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getActivity(), "List filtered !", Toast.LENGTH_SHORT).show();
    }

    /*private void setItemChecked(MenuItem item){
        item.setChecked(!item.isChecked());
    }*/
}
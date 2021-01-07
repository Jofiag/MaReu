package com.example.mareu.fragment;

import android.annotation.SuppressLint;
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

        getMeetingList();
        setReference(view);
        setRecyclerView(view);
        startAddMeetingActivity(view);

        return view;
    }

    private void getMeetingList() {
        Bundle bundle = getArguments();

        if (bundle != null)
            meetingList = (List<Meeting>) bundle.getSerializable(MEETING_LIST_CODE);
        else
            meetingList = MeetingDatabase.getInstance().getMeetingList();
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
        intent.putExtra(MEETING_LIST_CODE, (Serializable) meetingList);

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.room_a_select_item:
                filterMeetingList("Room A");
                return true;
            case R.id.room_b_select_item:
                filterMeetingList("Room B");
                return true;
            case R.id.room_c_select_item:
                filterMeetingList("Room C");
                return true;
            case R.id.all_room_select_item:
                filterMeetingList("All Room");
                return true;
            case R.id.today_select_item:
                filterMeetingList("Today");
                return true;
            case R.id.tomorrow_select_item:
                filterMeetingList("Tomorrow");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void filterMeetingList(String status){
            List<Meeting> filteredList;

            switch (status){
                case "Room A":
                    filteredList = MyMethodsApi.selectMeetingInRoomA(meetingList);
                    break;
                case "Room B":
                    filteredList = MyMethodsApi.selectMeetingInRoomB(meetingList);
                    break;
                case "Room C":
                    filteredList = MyMethodsApi.selectMeetingInRoomC(meetingList);
                    break;
                case "All Room":
                    filteredList = MyMethodsApi.selectAllMeeting(meetingList);
                    break;
                case "Today":
                    filteredList = MyMethodsApi.selectTodayMeeting(meetingList);
                    break;
                default:
                    filteredList = MyMethodsApi.selectTomorrowMeeting(meetingList);
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
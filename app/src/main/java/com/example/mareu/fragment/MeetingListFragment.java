package com.example.mareu.fragment;

import android.app.DatePickerDialog;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class MeetingListFragment extends Fragment {
    public static final String TOMORROW = "Tomorrow";
    public static final String TODAY = "Today";
    public static final String ALL_ROOM = "All Room";
    public static final String ROOM_C = "Room C";
    public static final String ROOM_B = "Room B";
    public static final String ROOM_A = "Room A";
    public static final String ALL_DATE = "All Date";
    public static final int CURRENT_DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    public static final int NEXT_DAY = CURRENT_DAY + 1;

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
            Objects.requireNonNull(getActivity()).finish();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.meeting_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

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
        else if (item.getItemId() == R.id.customize_select_item) {
            setCustomizeDateAndFilterList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void filterMeetingList(String status){
            List<Meeting> filteredList;

        switch (status) {
            case ROOM_A:
                filteredList = MyMethodsApi.selectMeetingByRoom(ROOM_A);
                break;
            case ROOM_B:
                filteredList = MyMethodsApi.selectMeetingByRoom(ROOM_B);
                break;
            case ROOM_C:
                filteredList = MyMethodsApi.selectMeetingByRoom(ROOM_C);
                break;
            case TODAY:
                filteredList = MyMethodsApi.selectMeetingByDate(CURRENT_DAY);
                break;
            case TOMORROW:
                filteredList = MyMethodsApi.selectMeetingByDate(NEXT_DAY);
                break;
            default:
                filteredList = meetingList;
                break;
        }

            adapter = new MeetingListRecyclerViewAdapter(getActivity(), filteredList);
            recyclerView.setAdapter(adapter);
            Toast.makeText(getActivity(), "List filtered !", Toast.LENGTH_SHORT).show();
    }

    private void setCustomizeDateAndFilterList(){
        Calendar calendar = Calendar.getInstance();

        //Current date
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        //List<Meeting> filteredList = new ArrayList<>();

        //DatePicker
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    (view, year, monthOfYear, dayOfMonth) -> {
                        List<Meeting> filteredList = MyMethodsApi.selectMeetingByDate(dayOfMonth);
                        adapter = new MeetingListRecyclerViewAdapter(MeetingListFragment.this.getActivity(), filteredList);
                        recyclerView.setAdapter(adapter);
                        Toast.makeText(MeetingListFragment.this.getActivity(), "List filtered !", Toast.LENGTH_SHORT).show();
                    }, currentYear, currentMonth, currentDay);

            datePickerDialog.show();
        }

    }
}
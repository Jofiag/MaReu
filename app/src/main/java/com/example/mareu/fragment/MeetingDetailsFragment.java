package com.example.mareu.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

import static com.example.mareu.controler.MainActivity.MEETING_SELECTED_CODE;


public class MeetingDetailsFragment extends Fragment {
    private Meeting meeting = new Meeting();

    public MeetingDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null)
            this.meeting = (Meeting) bundle.getSerializable(MEETING_SELECTED_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_details, container, false);

        showMeetingDetails(view, meeting);

        return view;
    }

    private void showMeetingDetails(View view, Meeting meeting){
        TextView dateText = view.findViewById(R.id.date_detail_text_view);
        TextView timeText = view.findViewById(R.id.time_detail_text);
        TextView roomText = view.findViewById(R.id.room_detail_text);
        TextView subjectText = view.findViewById(R.id.subject_detail_text);
        TextView participantsEmailsText = view.findViewById(R.id.emails_detail_text);

        StringBuilder emailsText = new StringBuilder();
        List<String> emailList = meeting.getParticipantMailList();
        for (int i = 0; i < emailList.size(); i++) {
            if (i == 0)
                emailsText = new StringBuilder(emailList.get(i));
            else
                emailsText.append("\n").append(emailList.get(i));
        }

        int meetingDay = meeting.getCalendar().get(Calendar.DAY_OF_MONTH);
        int meetingMonth = meeting.getCalendar().get(Calendar.MONTH);
        int meetingYear = meeting.getCalendar().get(Calendar.YEAR);
        int meetingHour = meeting.getCalendar().get(Calendar.HOUR);
        int meetingMinute = meeting.getCalendar().get(Calendar.MINUTE);

        dateText.setText(MessageFormat.format("{0}/{1}/{2}", meetingDay, meetingMonth, meetingYear));
        timeText.setText(MessageFormat.format("{0}h{1}", meetingHour, meetingMinute));
        roomText.setText(meeting.getRoom().getRoomText());
        subjectText.setText(meeting.getSubject());
        participantsEmailsText.setText(emailsText);
    }
}
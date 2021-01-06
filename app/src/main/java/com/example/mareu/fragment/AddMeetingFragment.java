package com.example.mareu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingFragment extends Fragment {
    private TextView participantsEmailsText;
    private EditText roomEdit;
    private EditText timeEdit;
    private EditText subjectEdit;
    private EditText emailEdit;
    private Button addParticipantEmailButton;
    private Button saveEmailButton;

    private final StringBuilder emailText = new StringBuilder();

    private final Meeting meeting = new Meeting();
    private final List<Meeting> meetingList = MeetingDatabase.getInstance().getMeetingList();
    private final List<String> emailsList = new ArrayList<>();

    public AddMeetingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        Context context = view.getContext();
        saveMeeting(context, view);

        return view;
    }

    private void saveMeeting(Context context, View view){
        participantsEmailsText = view.findViewById(R.id.participants_emails_text_add_fragment);
        roomEdit = view.findViewById(R.id.room_edit_text);
        timeEdit = view.findViewById(R.id.time_edit_text);
        subjectEdit = view.findViewById(R.id.subject_edit_text);
        emailEdit = view.findViewById(R.id.participant_emails_edit_text);
        addParticipantEmailButton = view.findViewById(R.id.add_participant_email_button);
        saveEmailButton = view.findViewById(R.id.save_participant_button);
        Button saveMeetingButton = view.findViewById(R.id.save_meeting_button);

        addParticipantEmailButton.setOnClickListener(v -> showParticipantEditText());

        saveMeetingButton.setOnClickListener(v -> {
            String roomEntered = roomEdit.getText().toString().trim();
            String timeEntered = timeEdit.getText().toString().trim();
            String subjectEntered = subjectEdit.getText().toString().trim();
            boolean fieldsEnteredNotEmpty = !TextUtils.isEmpty(roomEntered) && !TextUtils.isEmpty(timeEntered) && !TextUtils.isEmpty(subjectEntered);

            if (fieldsEnteredNotEmpty && !emailsList.isEmpty()){
                meeting.setPlace(roomEntered);
                meeting.setTime(timeEntered);
                meeting.setSubject(subjectEntered);
                meeting.setParticipantMailList(emailsList);

                meetingList.add(meeting);
            }
            else if (!fieldsEnteredNotEmpty)
                Toast.makeText(context, "Empty field are not allowed !", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "You must enter at least one participant !", Toast.LENGTH_SHORT).show();
        });
    }

    private void showParticipantEditText(){
        addParticipantEmailButton.setVisibility(View.GONE);
        emailEdit.setVisibility(View.VISIBLE);
        saveEmailButton.setVisibility(View.VISIBLE);

        saveEmailButton.setOnClickListener(v -> {
            String emailEntered = emailEdit.getText().toString().trim();
            if (!TextUtils.isEmpty(emailEntered)){
                emailsList.add(emailEntered);
                emailText.append(emailText).append("\n\n").append(emailEntered);
                participantsEmailsText.setText(emailText);

                addParticipantEmailButton.setVisibility(View.VISIBLE);
                emailEdit.setVisibility(View.GONE);
                saveEmailButton.setVisibility(View.GONE);
            }
            else
                Toast.makeText(getContext(), "Empty field not allowed !", Toast.LENGTH_SHORT).show();
        });
    }
}
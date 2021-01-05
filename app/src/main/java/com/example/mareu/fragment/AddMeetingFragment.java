package com.example.mareu.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class AddMeetingFragment extends Fragment {
    private final List<Meeting> meetingList = MeetingDatabase.getInstance().getMeetingList();

    public AddMeetingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        View popupView = inflater.inflate(R.layout.add_participant_email_popup, container, false);
        Context context = view.getContext();

        saveMeeting(context, view, popupView);

        return view;
    }

    private void saveMeeting(Context context, View view, View popupView){
        EditText roomEdit = view.findViewById(R.id.room_edit_text);
        EditText timeEdit = view.findViewById(R.id.time_edit_text);
        EditText subjectEdit = view.findViewById(R.id.subject_edit_text);
        TextView participantsEmailsText = view.findViewById(R.id.participants_emails_text_add_fragment);
        Button addParticipantEmailButton = view.findViewById(R.id.add_participant_email_button);
        Button saveMeetingButton = view.findViewById(R.id.save_meeting_button);

        Meeting meeting = new Meeting();
        List<String> emailsList = new ArrayList<>();

        addParticipantEmailButton.setOnClickListener(v -> addAParticipant(context, popupView, emailsList, participantsEmailsText));

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

    private void addAParticipant(Context context, View popupView, List<String> emailsList, TextView participantsEmailsText){
        EditText participantEmailEdit = popupView.findViewById(R.id.participant_emails_edit_text_popup);
        Button saveEmailButton = popupView.findViewById(R.id.save_participant_button);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog;

        builder.setView(popupView);
        dialog = builder.create();
        dialog.setTitle("Add a participant");
        dialog.setMessage("Enter participant email");
        dialog.show();

        String emailEntered = participantEmailEdit.getText().toString().trim();
        StringBuilder emailsText = new StringBuilder();
        if (!TextUtils.isEmpty(emailEntered)) {
            saveEmailButton.setOnClickListener(v -> {
                if (!emailsList.contains(emailEntered)) {
                    emailsList.add(emailEntered);
                    emailsText.append(emailsText).append("\n").append(emailEntered);
                    participantsEmailsText.setText(emailsText);
                    dialog.dismiss();
                } else
                    Toast.makeText(context, "This participant already exist in this meeting", Toast.LENGTH_SHORT).show();
            });
        }
        else
            Toast.makeText(context, "Empty field is not allowed !", Toast.LENGTH_SHORT).show();
    }
}
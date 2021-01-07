package com.example.mareu.fragment;

import android.content.Context;
import android.content.Intent;
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

import com.example.mareu.R;
import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.controler.MainActivity;
import com.example.mareu.model.Meeting;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddMeetingFragment extends Fragment {
    public static final String MEETING_LIST_CODE = "meeting list";
    private TextView participantsEmailsText;
    private EditText roomEdit;
    private EditText subjectEdit;
    private EditText emailEdit;
    private Button addParticipantEmailButton;
    private Button saveEmailButton;
    private Button saveMeetingButton;
    private Button showAllMeetingButton;

    private final StringBuilder emailText = new StringBuilder();

    private final Meeting meeting = new Meeting();
    private List<Meeting> meetingList = new ArrayList<>();
    private final List<String> emailsList = new ArrayList<>();

    public AddMeetingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_meeting, container, false);
        Context context = view.getContext();

        getMeetingList();
        setReferences(view);
        saveMeeting(context);
        showAllMeeting();

        return view;
    }

    private void getMeetingList() {
        Bundle bundle = getArguments();
        if (bundle != null)
            meetingList = (List<Meeting>) bundle.getSerializable(MEETING_LIST_CODE);
    }

    private void setReferences(View view){
        roomEdit = view.findViewById(R.id.room_edit_text);
        subjectEdit = view.findViewById(R.id.subject_edit_text);
        emailEdit = view.findViewById(R.id.participant_emails_edit_text);
        addParticipantEmailButton = view.findViewById(R.id.add_participant_email_button);
        participantsEmailsText = view.findViewById(R.id.participants_emails_text_add_fragment);
        saveEmailButton = view.findViewById(R.id.save_participant_button);
        saveMeetingButton = view.findViewById(R.id.save_meeting_button);
        showAllMeetingButton = view.findViewById(R.id.show_meeting_list_button);
    }

    private void saveMeeting(Context context){
        addParticipantEmailButton.setOnClickListener(v -> showParticipantEditText());

        saveMeetingButton.setOnClickListener(v -> {
            String roomEntered = roomEdit.getText().toString().trim();
            String subjectEntered = subjectEdit.getText().toString().trim();
            boolean fieldsEnteredNotEmpty = !TextUtils.isEmpty(roomEntered) && !TextUtils.isEmpty(subjectEntered);

            if (fieldsEnteredNotEmpty && !emailsList.isEmpty()){
                meeting.setPlace(roomEntered);
                meeting.setSubject(subjectEntered);
                meeting.setParticipantMailList(emailsList);

                MyMethodsApi.addMeeting(meetingList, meeting);
                if (meetingList.contains(meeting))
                    Toast.makeText(context, "Meeting saved !", Toast.LENGTH_SHORT).show();
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
            if (!TextUtils.isEmpty(emailEntered) && !emailsList.contains(emailEntered)){
                emailsList.add(emailEntered);
                emailText.append(emailEntered).append("\n\n");
                participantsEmailsText.setText(emailText);

                addParticipantEmailButton.setVisibility(View.VISIBLE);
                emailEdit.setVisibility(View.GONE);
                saveEmailButton.setVisibility(View.GONE);
            }
            else if (emailsList.contains(emailEntered))
                Toast.makeText(getContext(), "Already added to this meeting !", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "Empty field not allowed !", Toast.LENGTH_SHORT).show();
        });
    }

    private void showAllMeeting(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MEETING_LIST_CODE, (Serializable) meetingList);

        showAllMeetingButton.setOnClickListener(v -> startActivity(intent));
    }
}
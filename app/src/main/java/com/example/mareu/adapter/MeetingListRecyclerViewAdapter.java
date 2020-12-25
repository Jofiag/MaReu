package com.example.mareu.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.MeetingDatabase;
import com.example.mareu.R;
import com.example.mareu.api.CrudMeetingApi;
import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.MyViewHolder> {

    private List<Meeting> meetingList = new ArrayList<>();

    public MeetingListRecyclerViewAdapter(List<Meeting> meetingList) {
        this.meetingList = meetingList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meeting meeting = meetingList.get(position);

        int[] colorTable = {Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLUE, Color.BLACK, Color.WHITE, Color.MAGENTA};
        String details = meeting.getSubject() + " - " + meeting.getTime() + " - " + meeting.getPlace();
        StringBuilder emailsText = new StringBuilder();
        List<String> emailList = meeting.getParticipantMailList();
        for (int i = 0; i < emailList.size(); i++) {
            if (i == 0)
                emailsText = new StringBuilder(emailList.get(i));
            else
                emailsText.append(", ").append(emailList.get(i));
        }

        holder.meetingImageView.setColorFilter(colorTable[randomInt(6, 0)]);
        holder.someMeetingDetailsText.setText(details);
        holder.participantsEmailsText.setText(emailsText.toString());
        holder.deleteImageButton.setOnClickListener(v -> {
            if (MeetingDatabase.getInstance().getMeetingList().contains(meeting))
                CrudMeetingApi.deleteMeeting(meeting);

            meetingList.remove(meeting);

            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView meetingImageView;
        private final TextView someMeetingDetailsText;
        private final TextView participantsEmailsText;
        private final ImageButton deleteImageButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            meetingImageView = itemView.findViewById(R.id.meeting_image_view);
            someMeetingDetailsText = itemView.findViewById(R.id.some_meeting_details_text);
            participantsEmailsText = itemView.findViewById(R.id.participants_emails_text);
            deleteImageButton = itemView.findViewById(R.id.delete_image_button);
        }
    }

    public static int randomInt(int max, int min){
        return new Random().nextInt(max - min) + min;
    }
}

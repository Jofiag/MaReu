package com.example.mareu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.api.MyMethodsApi;
import com.example.mareu.model.Meeting;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MeetingListRecyclerViewAdapter extends RecyclerView.Adapter<MeetingListRecyclerViewAdapter.MyViewHolder> {
    public interface OnMeetingClickListener {
        void onMeetingSelected(Meeting meeting);
    }

    private final List<Meeting> meetingList;
    private final OnMeetingClickListener mCallback;

    public MeetingListRecyclerViewAdapter(Context context, List<Meeting> meetingList) {
        this.meetingList = meetingList;
        this.mCallback = (OnMeetingClickListener) context;
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

        String details = meeting.getSubject() + " - " + meeting.getTime() + " - " + meeting.getRoom().getRoomText();
        StringBuilder emailsText = new StringBuilder();
        List<String> emailList = meeting.getParticipantMailList();
        for (int i = 0; i < emailList.size(); i++) {
            if (i == 0)
                emailsText = new StringBuilder(emailList.get(i));
            else
                emailsText.append(", ").append(emailList.get(i));
        }

        holder.meetingImageView.setColorFilter(meeting.getRoom().getColor());
        holder.someMeetingDetailsText.setText(details);
        holder.participantsEmailsText.setText(emailsText.toString());
        holder.deleteImageButton.setOnClickListener(v -> {
            if (meetingList.contains(meeting))
                MyMethodsApi.deleteMeeting(meetingList, meeting);
            notifyDataSetChanged();
        });
        holder.itemView.setOnClickListener(v -> mCallback.onMeetingSelected(meeting));
    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView meetingImageView;
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

}

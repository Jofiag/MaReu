package com.example.mareu.model;

import android.graphics.Color;

import static com.example.mareu.fragment.MeetingListFragment.ROOM_A;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_B;
import static com.example.mareu.fragment.MeetingListFragment.ROOM_C;

public class Room {
    private final String room;
    private int color;

    public Room(String room) {
        this.room = room;
        setColor();
    }

    public String getRoomText() {
        return room;
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        switch (room){
            case ROOM_A:
                this.color = Color.GREEN;
                break;
            case ROOM_B:
                this.color = Color.YELLOW;
                break;
            case ROOM_C:
                this.color = Color.RED;
                break;
        }
    }
}

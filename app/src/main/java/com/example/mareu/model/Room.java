package com.example.mareu.model;

import android.graphics.Color;

public class Room {
    private String room;
    private int color;

    public Room() {
    }

    public Room(String room, int color) {
        this.room = room;
        this.color = color;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getColor() {
        return color;
    }

    public void setColor() {
        switch (room){
            case "Room A":
                this.color = Color.GREEN;
                break;
            case "Room B":
                this.color = Color.YELLOW;
                break;
            case "Room C":
                this.color = Color.RED;
                break;
        }
    }
}

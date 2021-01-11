package com.example.mareu.model;

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

    public void setColor(int color) {
        this.color = color;
    }
}

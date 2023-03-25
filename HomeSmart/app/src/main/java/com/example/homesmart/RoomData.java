package com.example.homesmart;

public class RoomData {

    private String room_name;
    private int room_id, rtype_id;

    RoomData(String room_name, int room_id, int rtype_id){
        this.room_name = room_name;
        this.room_id = room_id;
        this.rtype_id = rtype_id;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getRtype_id() {
        return rtype_id;
    }

    public void setRtype_id(int rtype_id) {
        this.rtype_id = rtype_id;
    }
}

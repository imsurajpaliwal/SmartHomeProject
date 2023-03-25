package com.example.scannerhome;

import java.security.PublicKey;

public class RoomModel {
    public int Id;
    public int RoomTypeId;
    public String Name;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRoomTypeId() {
        return RoomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        RoomTypeId = roomTypeId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

package com.example.homesmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView courseRV = findViewById(R.id.idRoomRV);

        // Here, we have created new array list and added data to it
        ArrayList<RoomData> roomDataArrayList = new ArrayList<RoomData>();
        roomDataArrayList.add(new RoomData("Suraj Bedroom", 1, R.drawable.home));
        roomDataArrayList.add(new RoomData("Kitchen", 2, R.drawable.home));
        roomDataArrayList.add(new RoomData("Bharat Bedroom", 3, R.drawable.home));
        roomDataArrayList.add(new RoomData("Common Living Room", 4, R.drawable.home));
        roomDataArrayList.add(new RoomData("Common Drawing Hall", 5, R.drawable.home));
        roomDataArrayList.add(new RoomData("Balcony", 6, R.drawable.home));
        // we are initializing our adapter class and passing our arraylist to it.
        RoomAdapter roomAdapter = new RoomAdapter(this, roomDataArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(roomAdapter);
    }
}
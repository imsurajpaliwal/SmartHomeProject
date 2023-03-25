package com.example.homesmart;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{

    private final Context context;
    private final ArrayList<RoomData> roomDataArrayList;

    // Constructor
    public RoomAdapter(Context context, ArrayList<RoomData> roomDataArrayList) {
        this.context = context;
        this.roomDataArrayList = roomDataArrayList;
    }
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        RoomData model = roomDataArrayList.get(position);
        holder.roomNameTV.setText(model.getRoom_name());
        //holder.courseRatingTV.setText("" + model.getCourse_rating());
        //holder.courseIV.setImageResource(model.getCourse_image());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return roomDataArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView roomImage;
        private final TextView roomNameTV;
        //private final TextView ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //roomImage = itemView.findViewById(R.id.idRoomImage);
            roomNameTV = itemView.findViewById(R.id.idRoomName);
            //courseRatingTV = itemView.findViewById(R.id.);
        }
    }
}
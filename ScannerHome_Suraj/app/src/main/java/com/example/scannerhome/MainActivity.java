package com.example.scannerhome;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //qr code scanner object
    private IntentIntegrator qrScan;
    JSONObject obj;
    String home_app_flag;
    JSONArray rValue, nValue, aValue, room, node, appliance;
    String [] r ;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qrScan = new IntentIntegrator(this);
        qrScan.initiateScan();
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        try{
            obj = new JSONObject(result.getContents());
            home_app_flag = obj.getString("Home_App_Flag");
        }catch (JSONException e){
            Toast.makeText(this, "Please scan a valid QR", Toast.LENGTH_LONG).show();
            qrScan.initiateScan();
        }
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else if (home_app_flag == "true"){
                //if qr contains data
                try {
                    //converting the data to json
                    //Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                    room = obj.optJSONArray("Room");
                    List<RoomModel> roomList = new ArrayList<RoomModel>();
                    for (int y = 0; y < room.length(); y++) {
                        JSONObject rColumns = room.getJSONObject(y);
                        RoomModel roomModel = new RoomModel();
                        roomModel.setId((Integer) rColumns.get("Id"));
                        roomModel.setName((String) rColumns.get("Name"));
                        roomModel.setRoomTypeId((Integer) rColumns.get("RoomTypeId"));
                        roomList.add(roomModel);
                    }

                    node = obj.optJSONArray("Node");
                    List<NodeModel> nodeList = new ArrayList<NodeModel>();
                    for (int i = 0; i < node.length(); i++) {
                        JSONObject nColumns = node.getJSONObject(i);
                        NodeModel nodeModel = new NodeModel();
                        nodeModel.setId((Integer) nColumns.get("Id"));
                        nodeModel.setNodeSerialNumber((Integer) nColumns.get("NodeSerialId"));
                        nodeModel.setNodeType((String) nColumns.get("NodeType"));
                        nodeList.add(nodeModel);
                    }
                    appliance = obj.optJSONArray("Appliance");
                    List<ApplianceModel> applianceList = new ArrayList<ApplianceModel>();
                    for (int j = 0; j < appliance.length(); j++) {
                        JSONObject aColumns = appliance.getJSONObject(j);
                        ApplianceModel applianceModel = new ApplianceModel();
                        applianceModel.setId((Integer) aColumns.get("Id"));
                        applianceModel.setApplianceName((String) aColumns.get("ApplianceName"));
                        applianceModel.setApplianceTypeId((Integer) aColumns.get("AppTypeId"));
                        applianceModel.setNodeId((Integer) aColumns.get("NodeId"));
                        applianceModel.setNodeIndex((Integer) aColumns.get("NodeIndex"));
                        applianceModel.setRoomId((Integer) aColumns.get("RoomId"));
                        applianceList.add(applianceModel);
                    }

                    dbHandler = new DBHandler(MainActivity.this);
                    dbHandler.addDataBase(roomList,nodeList,applianceList,room.length(),node.length(),appliance.length());
                    Toast.makeText(MainActivity.this, "Data has been added.", Toast.LENGTH_SHORT).show();

                    //Read data from room

                    Cursor res = dbHandler.viewDataBase();
                    String s = "s";
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        s = res.getString(1);
                        buffer.append("Id: "+ res.getString(0)+"\n");
                        buffer.append("Room Name: "+ res.getString(1)+"\n");
                        buffer.append("Room Type: "+ res.getString(2)+"\n");
                    }
                    System.out.println(buffer);
                    TextView textView = findViewById(R.id.textView);
                    textView.setText(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, "wrong Data", Toast.LENGTH_LONG).show();
                    qrScan.initiateScan();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

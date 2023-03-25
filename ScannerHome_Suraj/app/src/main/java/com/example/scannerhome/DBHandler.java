package com.example.scannerhome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "eHome";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String ROOM_TABLE = "rooms";
    private static final String NODE_TABLE = "nodes";
    private static final String APPLIANCE_TABLE = "appliances";

    // below variable is for our id column.
    private static final String ROOM_ID_COL = "r_id";
    private static final String NODE_ID_COL = "n_id";
    private static final String APPLIANCE_ID_COL = "a_id";

    // below variable is for our  name column
    private static final String ROOM_NAME_COL = "r_name";
    private static final String NODE_NAME_COL = "n_name";
    private static final String APPLIANCE_NAME_COL = "a_name";

    // below variable id for our Node type column.
    private static final String NODE_TYPE_COL = "n_type";

    // below variable id for our appliance type column.
    private static final String APPLIANCE_TYPE_COL = "a_type";

    // below variable id for our room type column.
    private static final String ROOM_TYPE_COL = "r_type";

    // below variable id for our Node type column.
    private static final String NODE_INDEX_COL = "n_index";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String room_query = "CREATE TABLE " + ROOM_TABLE + " ("
                + ROOM_ID_COL + " INTEGER,"
                + ROOM_NAME_COL + " TEXT,"
                + ROOM_TYPE_COL + " INTEGER)";

        String node_query = "CREATE TABLE " + NODE_TABLE + " ("
                + NODE_ID_COL + " INTEGER, "
                + NODE_NAME_COL + " INTEGER,"
                + NODE_TYPE_COL + " TEXT)";

        String appliance_query = "CREATE TABLE " + APPLIANCE_TABLE + " ("
                + APPLIANCE_ID_COL + " INTEGER, "
                + APPLIANCE_NAME_COL + " TEXT,"
                + APPLIANCE_TYPE_COL + " INTEGER,"
                + NODE_ID_COL + " INTEGER,"
                + NODE_INDEX_COL + " INTEGER,"
                + ROOM_ID_COL + " INTEGER)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(room_query);
        db.execSQL(node_query);
        db.execSQL(appliance_query);
    }

    // this method is use to add new course to our sqlite database.
    //int roomID, String roomName, int roomTypeID, int nodeID, String nodeName, String nodeType, int applianceID, String applianceName, int applianceType, int node_Index
    public void addDataBase(List<RoomModel> roomList,List<NodeModel> nodeList,List<ApplianceModel> applianceList,int roomCount,int nodeCount,int applianceCount){

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();
        // on below line we are passing all values
        // along with its key and value pair.

        //Room Content
        for (RoomModel rModel : roomList){
            ContentValues values = new ContentValues();
            values.put(ROOM_ID_COL,rModel.getId());
            values.put(ROOM_NAME_COL, rModel.getName());
            values.put(ROOM_TYPE_COL, rModel.getRoomTypeId());
            db.insert(ROOM_TABLE, null, values);
        }

        //Node Content
        for (NodeModel nModel : nodeList) {
            ContentValues values = new ContentValues();
            values.put(NODE_ID_COL, nModel.getId());
            values.put(NODE_NAME_COL, nModel.getNodeSerialNumber() );
            values.put(NODE_TYPE_COL, nModel.getNodeType());
            db.insert(NODE_TABLE, null, values);
        }

        //Appliance Content
        for (ApplianceModel aModel : applianceList) {
            ContentValues values = new ContentValues();
            values.put(APPLIANCE_ID_COL, aModel.getId());
            values.put(APPLIANCE_NAME_COL, aModel.getApplianceName());
            values.put(APPLIANCE_TYPE_COL, aModel.getApplianceTypeId());
            values.put(NODE_ID_COL, aModel.getNodeId());
            values.put(NODE_INDEX_COL, aModel.getNodeIndex());
            values.put(ROOM_ID_COL, aModel.getRoomId());
            db.insert(APPLIANCE_TABLE, null, values);
        }
        db.close();
    }

    public Cursor viewDataBase()  {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ROOM_TABLE,null);
        return res;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + ROOM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NODE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + APPLIANCE_TABLE);
        onCreate(db);
    }
}

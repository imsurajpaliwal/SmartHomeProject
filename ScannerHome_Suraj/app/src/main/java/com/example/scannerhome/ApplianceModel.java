package com.example.scannerhome;

public class ApplianceModel {
    String applianceName;
    int Id;
    int nodeId;
    int applianceTypeId;
    int roomId;
    int nodeIndex;

    public String getApplianceName() {
        return applianceName;
    }

    public void setApplianceName(String applianceName) {
        this.applianceName = applianceName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getApplianceTypeId() {
        return applianceTypeId;
    }

    public void setApplianceTypeId(int applianceTypeId) {
        this.applianceTypeId = applianceTypeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getNodeIndex() {
        return nodeIndex;
    }

    public void setNodeIndex(int nodeIndex) {
        this.nodeIndex = nodeIndex;
    }
}

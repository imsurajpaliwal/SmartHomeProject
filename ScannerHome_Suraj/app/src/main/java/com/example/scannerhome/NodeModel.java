package com.example.scannerhome;

public class NodeModel {
    int Id;
    int NodeSerialNumber;
    String NodeType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNodeSerialNumber() {
        return NodeSerialNumber;
    }

    public void setNodeSerialNumber(int nodeSerialNumber) {
        NodeSerialNumber = nodeSerialNumber;
    }

    public String getNodeType() {
        return NodeType;
    }

    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }
}

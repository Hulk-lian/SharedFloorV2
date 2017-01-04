package com.jtcode.sharedfloor.model;

public class TypeItem {

    private String id;
    private String nameType;

    public String getId(){
        return id;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public TypeItem(String name){
        nameType= name;
    }

    @Override
    public String toString() {
        return nameType;
    }
}

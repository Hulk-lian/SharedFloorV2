package com.jtcode.sharedfloor.model;


import android.os.Parcel;
import android.os.Parcelable;


public class PurchaseItem implements Parcelable{
    private String id;
    private String name;
    private TypeItem type;

    public PurchaseItem(String name,TypeItem type){
        this.name=name;
        this.type=type;
    }

    protected PurchaseItem(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<PurchaseItem> CREATOR = new Creator<PurchaseItem>() {
        @Override
        public PurchaseItem createFromParcel(Parcel in) {
            return new PurchaseItem(in);
        }

        @Override
        public PurchaseItem[] newArray(int size) {
            return new PurchaseItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeItem getType() {
        return type;
    }

    public String getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }
}
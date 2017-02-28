package com.jtcode.sharedfloor.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


public class PurchaseItem implements Parcelable, Comparable<PurchaseItem>{
    private String id;
    private String name;
    private boolean strike;

    public PurchaseItem(String name){
        this.name=name;
        this.strike=false;
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

    public String getId() {
        return id;
    }

    public boolean getStrike(){
        return this.strike;
    }
    public void setStrike(boolean strike){
        this.strike=strike;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res=false;
        PurchaseItem p;
        if(obj!= null){
            if(obj instanceof PurchaseItem){
                p=(PurchaseItem)obj;
                if(this.name.equalsIgnoreCase(p.name)){
                    res=true;
                }
            }
        }
        return res;
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

    @Override
    public int compareTo(@NonNull PurchaseItem p) {
        return this.name.compareToIgnoreCase(p.getName());
    }
}
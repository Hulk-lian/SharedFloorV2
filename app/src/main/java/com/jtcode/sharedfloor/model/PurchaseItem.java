package com.jtcode.sharedfloor.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


public class PurchaseItem implements Parcelable, Comparable<PurchaseItem>{
    private int id;
    private String name;
    private boolean strike;

    public PurchaseItem(String name){
        this.name=name;
        this.strike=false;
    }

    public boolean isStrike() {
        return strike;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected PurchaseItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        strike = in.readByte() != 0;
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

    @Override
    public int compareTo(@NonNull PurchaseItem p) {
        return this.name.compareToIgnoreCase(p.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeByte((byte) (strike ? 1 : 0));
    }
}
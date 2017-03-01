package com.jtcode.sharedfloor.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.jtcode.sharedfloor.interfaces.CustomConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;


public class Expense implements Parcelable{

    private int id;
    private String name;
    private double amount;
    private double amountPerUser;
    private String paid;//the user who paid the expense

    public Expense(){

    }


    protected Expense(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readDouble();
        amountPerUser = in.readDouble();
        paid = in.readString();
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    //region getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAmountPerUser(int nUser){
        if(nUser!=0)
        this.amountPerUser=Math.round(this.amount/nUser);
    }

    public double getAmountPerUser() {
        return amountPerUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

//endregion

    public static final Comparator<Expense> AMOUNT_COMPARATOR= new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            return Double.compare(expense1.getAmount(),expense2.getAmount());
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(amount);
        dest.writeDouble(amountPerUser);
        dest.writeString(paid);
    }
}

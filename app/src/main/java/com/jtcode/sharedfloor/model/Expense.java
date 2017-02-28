package com.jtcode.sharedfloor.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.jtcode.sharedfloor.interfaces.CustomConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;


public class Expense implements Parcelable, Comparable<Expense>{

    private String id;
    private String name;
    private double amount;
    private double amountPerUser;
    private String dateExpense;
    private String paid;//the user who paid the expense

    public Expense(){

    }

    protected Expense(Parcel in) {
        id = in.readString();
        name = in.readString();
        amount = in.readDouble();
        amountPerUser = in.readDouble();
        dateExpense = in.readString();
        paid = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(amount);
        dest.writeDouble(amountPerUser);
        dest.writeString(dateExpense);
        dest.writeString(paid);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public double getAmountPerUser() {
        return amountPerUser;
    }
    public void setAmountPerUser(int nUser){
        this.amountPerUser=Math.round(this.amount/nUser);
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDateExpense() {
        return dateExpense;
    }

    public void setDateExpense(String dateExpense) {
        this.dateExpense = dateExpense;
    }
//endregion

    //constructor
    public Expense(String name,Double amount,String paid,int userscount,String dateExpense){
        this.name=name;
        this.amount=amount;
        this.amountPerUser=amount/userscount;
        this.paid=paid;
        this.dateExpense=dateExpense;

    }


    public static final Comparator<Expense> AMOUNT_COMPARATOR= new Comparator<Expense>() {
        @Override
        public int compare(Expense expense1, Expense expense2) {
            return Double.compare(expense1.getAmount(),expense2.getAmount());
        }
    };

    public static final Comparator<Expense> DATE_SORT=new Comparator<Expense>() {
        @Override
        public int compare(Expense lhs, Expense rhs) {
            DateFormat format= new SimpleDateFormat(CustomConstants.DATEFORMAT);
            try {
                return format.parse(rhs.getDateExpense()).compareTo(format.parse(lhs.getDateExpense()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    };

    @Override
    public int compareTo(Expense another) {
        DateFormat format= new SimpleDateFormat(CustomConstants.DATEFORMAT);
        try {
            return format.parse(this.getDateExpense()).compareTo(format.parse(another.getDateExpense()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

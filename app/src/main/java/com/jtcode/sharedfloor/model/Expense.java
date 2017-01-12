package com.jtcode.sharedfloor.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;
import java.util.Date;


public class Expense implements Parcelable{

    private Home home;//the home of the expense

    private String id;
    private String name;
    private double amount;
    private double amountPerUser;
    private String dateExpense;
    private User paid;//the user who paid the expense

    protected Expense(Parcel in) {
        id = in.readString();
        name = in.readString();
        amount = in.readDouble();
        amountPerUser = in.readDouble();
        dateExpense = in.readString();
        paid = in.readParcelable(User.class.getClassLoader());
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

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
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
        this.amountPerUser=this.amount/nUser;
    }

    public User getPaid() {
        return paid;
    }

    public void setPaid(User paid) {
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
    public Expense(String name,Double amount,User paid,Home homeE,String dateExpense){
        this.name=name;
        this.amount=amount;
        this.amountPerUser=amount/homeE.getNumberUsers();
        this.paid=paid;
        this.home=homeE;
        this.dateExpense=dateExpense;

    }


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
        dest.writeString(id);
        dest.writeString(name);
        dest.writeDouble(amount);
        dest.writeDouble(amountPerUser);
        dest.writeString(dateExpense);
        dest.writeParcelable(paid, flags);
    }
}

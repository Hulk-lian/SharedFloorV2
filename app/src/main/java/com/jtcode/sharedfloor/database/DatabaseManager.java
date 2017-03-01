package com.jtcode.sharedfloor.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jtcode.sharedfloor.model.Expense;
import com.jtcode.sharedfloor.model.User;
import java.util.ArrayList;

/**
 * Created by Hulk-lián
 */

public class DatabaseManager {

    //users
    public int addUser(User user){
        ContentValues params= new ContentValues();
        params.put(DatabaseContract.UserEntry.COLUMN_NAME,user.getName());
        params.put(DatabaseContract.UserEntry.COLUMN_PASS,user.getPassword());

        SQLiteDatabase database=DatabaseHelper.getInstance().getDatabase();
        return (int)database.insert(DatabaseContract.UserEntry.TABLE_NAME,null,params);
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> usuarios= new ArrayList<>();
        SQLiteDatabase db=DatabaseHelper.getInstance().getDatabase();
        Cursor cursor=db.query(DatabaseContract.UserEntry.TABLE_NAME,DatabaseContract.UserEntry.ALL_COLS,
                null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                User u= new User();
                u.setId(cursor.getInt(0));
                u.setName(cursor.getString(1));
                u.setPassword(cursor.getString(2));
                usuarios.add(u);
            }while (cursor.moveToNext());
        }
        return usuarios;
    }

    public void deleteUser(User user){
        String[] where={user.getName()};
        SQLiteDatabase database= DatabaseHelper.getInstance().getDatabase();
        database.delete(DatabaseContract.UserEntry.TABLE_NAME,DatabaseContract.UserEntry.COLUMN_NAME+" = ?",where);
    }

    //Expenses
    public int addExpense(Expense expense){

        ContentValues params= new ContentValues();
        int idUser=getIDUserbyName(expense.getPaid());

        params.put(DatabaseContract.ExpenseEntry.COLUMN_NAME,expense.getName());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_AMOUNT,expense.getAmount());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_PAID,idUser);

        SQLiteDatabase database=DatabaseHelper.getInstance().getDatabase();
        return (int)database.insert(DatabaseContract.ExpenseEntry.TABLE_NAME,null,params);
    }

    public ArrayList<Expense> getAllExpenses(){
        ArrayList<Expense> exptmp= new ArrayList<>();
        SQLiteDatabase db=DatabaseHelper.getInstance().getDatabase();
        Cursor cursor=db.query(DatabaseContract.ExpenseEntry.TABLE_NAME,DatabaseContract.ExpenseEntry.ALL_COLS,
                null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Expense e = new Expense();
                e.setId(cursor.getInt(0));
                e.setName(cursor.getString(1));
                e.setAmount(Double.parseDouble(cursor.getString(2)));
                e.setAmountPerUser(getAllUsers().size());
                e.setPaid(getNameUserByID(cursor.getInt(3)));
                exptmp.add(e);
            }while (cursor.moveToNext());
        }
        return exptmp;
    }

    public void UpdateExpense(Expense e){
        ContentValues params= new ContentValues();
        params.put(DatabaseContract.ExpenseEntry.COLUMN_NAME,e.getName());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_AMOUNT,e.getAmount());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_PAID,getIDUserbyName(e.getPaid()));

        String[] whereArgs={String.valueOf(e.getId())};

        SQLiteDatabase db=DatabaseHelper.getInstance().getDatabase();
        db.update(DatabaseContract.ExpenseEntry.TABLE_NAME,params," "+DatabaseContract.ExpenseEntry._ID+" = ?",whereArgs);
    }

    public void deleteExpense(Expense e){
        String[] where={String.valueOf(e.getId())};
        SQLiteDatabase database= DatabaseHelper.getInstance().getDatabase();
        database.delete(DatabaseContract.ExpenseEntry.TABLE_NAME," "+DatabaseContract.ExpenseEntry._ID+" = ?",where);
    }




    //utils

    private String getNameUserByID(int idUser){
        ArrayList<User> tmp=this.getAllUsers();
        for (User u: tmp){
            if(u.getId()==idUser){
                return u.getName();
            }
        }
        return "";
    }

    private int getIDUserbyName(String userName){
        ArrayList<User> tmp=this.getAllUsers();
        for(User u: tmp){
            if(u.getName()==userName){
                return u.getId();
            }
        }
        return -1;
    }

    //PurchaseList


}

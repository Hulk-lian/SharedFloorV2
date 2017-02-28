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
                User u= new User(
                        cursor.getString(1),
                        cursor.getString(2)
                );
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

        params.put(DatabaseContract.ExpenseEntry.COLUMN_NAME,expense.getName());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_AMOUNT,expense.getAmount());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_PAID,expense.get)

        SQLiteDatabase database=DatabaseHelper.getInstance().getDatabase();
        return (int)database.insert(DatabaseContract.UserEntry.TABLE_NAME,null,params);
    }



    //PurchaseList


}

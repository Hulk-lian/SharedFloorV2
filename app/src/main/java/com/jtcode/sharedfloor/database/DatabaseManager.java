package com.jtcode.sharedfloor.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jtcode.sharedfloor.model.Expense;
import com.jtcode.sharedfloor.model.PurchaseItem;
import com.jtcode.sharedfloor.model.User;
import java.util.ArrayList;

/**
 * Created by Hulk-lián
 */

public class DatabaseManager {
    private static DatabaseManager instance= new DatabaseManager();
    private static DatabaseHelper databaseHelper;

    public static DatabaseManager getInstance(){
        if(databaseHelper==null){
            databaseHelper=new DatabaseHelper();
        }
        return instance;
    }

    public SQLiteDatabase getDB(){
        return databaseHelper.getWritableDatabase();
    }


    //users
    public int addUser(User user){
        ContentValues params= new ContentValues();
        params.put(DatabaseContract.UserEntry.COLUMN_NAME,user.getName());
        params.put(DatabaseContract.UserEntry.COLUMN_PASS,user.getPassword());

        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        return (int)database.insert(DatabaseContract.UserEntry.TABLE_NAME,null,params);
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> usuarios= new ArrayList<>();
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
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
        SQLiteDatabase database= databaseHelper.getWritableDatabase();
        database.delete(DatabaseContract.UserEntry.TABLE_NAME,DatabaseContract.UserEntry.COLUMN_NAME+" = ?",where);
    }

    //Expenses
    public int addExpense(Expense expense){

        ContentValues params= new ContentValues();
        int idUser=getIDUserbyName(expense.getPaid());

        params.put(DatabaseContract.ExpenseEntry.COLUMN_NAME,expense.getName());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_AMOUNT,expense.getAmount());
        params.put(DatabaseContract.ExpenseEntry.COLUMN_PAID,idUser);

        SQLiteDatabase database=databaseHelper.getWritableDatabase();
        return (int)database.insert(DatabaseContract.ExpenseEntry.TABLE_NAME,null,params);
    }

    public ArrayList<Expense> getAllExpenses(){
        ArrayList<Expense> exptmp= new ArrayList<>();
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
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

        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        db.update(DatabaseContract.ExpenseEntry.TABLE_NAME,params," "+DatabaseContract.ExpenseEntry._ID+" = ?",whereArgs);
    }

    public void deleteExpense(Expense e){
        String[] where={String.valueOf(e.getId())};
        SQLiteDatabase database= databaseHelper.getWritableDatabase();
        database.delete(DatabaseContract.ExpenseEntry.TABLE_NAME," "+DatabaseContract.ExpenseEntry._ID+" = ?",where);
    }


    //purchase list

    public int addPurchase(PurchaseItem p){
        ContentValues params=new ContentValues();
        params.put(DatabaseContract.PurchaseEntry.TABLE_NAME,p.getName());
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        return (int)db.insert(DatabaseContract.PurchaseEntry.TABLE_NAME,null,params);
    }

    public ArrayList<PurchaseItem> getAllPurchase(){
        ArrayList<PurchaseItem> ptem= new ArrayList<>();

        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        Cursor c= db.query(DatabaseContract.PurchaseEntry.TABLE_NAME,DatabaseContract.PurchaseEntry.ALL_COLS,
                null,null,null,null,null);

        if(c.moveToFirst()){
            do {
                PurchaseItem p=new PurchaseItem(c.getInt(0),c.getString(1),c.getInt(2));
                ptem.add(p);
            }while (c.moveToNext());
        }
        return ptem;
    }

    public void updateItem(PurchaseItem item){
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseContract.PurchaseEntry.COLUMN_NAME,item.getName());
        contentValues.put(DatabaseContract.PurchaseEntry.COLUMN_STRIKE,item.isStrike());
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        String[] whereargs={String.valueOf(item.getId())};
        db.update(DatabaseContract.PurchaseEntry.TABLE_NAME,contentValues," "+DatabaseContract.PurchaseEntry._ID +" = ? ",whereargs);
    }

    public void removeItem(PurchaseItem item){
        String[] whereargs={item.getName()};
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        db.delete(DatabaseContract.PurchaseEntry.TABLE_NAME," "+DatabaseContract.PurchaseEntry._ID+" = ?",whereargs);
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


}

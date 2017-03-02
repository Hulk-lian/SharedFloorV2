package com.jtcode.sharedfloor.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.jtcode.sharedfloor.SharedFloorApplication;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Hulk-lián
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="Home.db";


    public DatabaseHelper() {
        super(SharedFloorApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(DatabaseContract.UserEntry.SQL_CREATE);
        db.execSQL(DatabaseContract.ExpenseEntry.SQL_CREATE);
        db.execSQL(DatabaseContract.PurchaseEntry.SQL_CREATE);
        db.setTransactionSuccessful();
        insertExamples(db);
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        db.execSQL(DatabaseContract.UserEntry.SQL_DELETE);
        db.execSQL(DatabaseContract.ExpenseEntry.SQL_DELETE);
        db.execSQL(DatabaseContract.PurchaseEntry.SQL_DELETE);
        this.onCreate(db);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
            db.setForeignKeyConstraintsEnabled(true);
        }else
            db.execSQL("PRAGMA foreign_keys = ON");
    }

    //examples
    private void insertExamples(SQLiteDatabase db){
        db.execSQL("insert into user (username,password ) values (\"yo mismo\",\"upcuo\"),(\"usuario369\",\"misuperpass\")");
        db.execSQL("insert into expense (exp_name,exp_amount,exp_paid) values(\"luz\",50,1),(\"agua\",60,1),(\"internet\",100,1),(\"otros gastos\",50,1),(\"modelo 130\",10,1)");
        db.execSQL("insert into purchase (pur_name,pur_strike) values(\"agua mineral\",0),(\"amor\",1),(\"chocolate\",0),(\"vino\",1),(\"un dragon de komodo\",1)");

    }
}

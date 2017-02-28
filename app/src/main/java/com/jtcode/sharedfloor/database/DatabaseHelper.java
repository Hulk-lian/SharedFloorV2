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
    private static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;
    private Context context;


    private DatabaseHelper() {
        super(SharedFloorApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(){
        if(databaseHelper == null)
            databaseHelper = new DatabaseHelper(); //Se usa el de la aplicación por si la activity fuese null

        return databaseHelper;
    }
    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }

    public synchronized SQLiteDatabase openDB(){
        if(mOpenCounter.incrementAndGet()==1){
            mDatabase=getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDB(){
        if(mOpenCounter.decrementAndGet()==0)
            mDatabase.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        db.execSQL(DatabaseContract.UserEntry.SQL_CREATE);
        db.execSQL(DatabaseContract.ExpenseEntry.SQL_CREATE);
        db.execSQL(DatabaseContract.PurchaseEntry.SQL_CREATE);
        db.setTransactionSuccessful();
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
}

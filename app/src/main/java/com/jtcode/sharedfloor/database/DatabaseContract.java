package com.jtcode.sharedfloor.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract(){}

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME="user";
        public static final String COLUMN_NAME="username";
        public static final String COLUMN_PASS="password";
        public static final String SQL_CREATE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT NOT NULL , "+COLUMN_PASS+" TEXT NOT NULL )";
        public static final String SQL_DELETE="DROP TABLE IF EXISTS "+TABLE_NAME;
        public static final String[] ALL_COLS={BaseColumns._ID,COLUMN_NAME,COLUMN_PASS};
        public static final String DEFAULT_SORT=COLUMN_NAME;
    }

    public static class ExpenseEntry implements BaseColumns{
        public static final String TABLE_NAME="expense";
        public static final String COLUMN_NAME="exp_name";
        public static final String COLUMN_AMOUNT="exp_amount";
        public static final String COLUMN_PAID="exp_paid";
        public static final String SQL_CREATE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT NOT NULL, "+COLUMN_AMOUNT+" TEXT NOT NULL, "+COLUMN_PAID +" INTEGER NOT NULL REFERENCES "+
                UserEntry.TABLE_NAME+"( "+UserEntry._ID+" ) ON UPDATE CASCADE ON DELETE CASCADE )";
        public static final String SQL_DELETE="DROP TABLE IF EXISTS "+TABLE_NAME;
        public static final String[] ALL_COLS={BaseColumns._ID,COLUMN_NAME,COLUMN_AMOUNT,COLUMN_PAID};
        public static final String DEFAULT_SORT=COLUMN_NAME;
        //join
        public static final String EXPENSE_JOIN_USER="INNER JOIN "+UserEntry.TABLE_NAME+" ON "+COLUMN_PAID+" = "+UserEntry._ID;
    }

    public static class PurchaseEntry implements BaseColumns{
        public static final String TABLE_NAME="purchase";
        public static final String COLUMN_NAME="pur_name";
        public static final String COLUMN_STRIKE="pur_strike";
        public static final String SQL_CREATE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( "+BaseColumns._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME+" TEXT UNIQUE NOT NULL, " +COLUMN_STRIKE+ " INTEGER NOT NULL )";
        public static final String SQL_DELETE="DROP TABLE IF EXISTS "+TABLE_NAME;
        public static final String[] ALL_COLS={BaseColumns._ID,COLUMN_NAME,COLUMN_STRIKE};
        public static final String DEFAULT_SORT=COLUMN_NAME;

    }

}

package com.example.raeven.guidance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viana on 9/25/2016.
 */
public class UserDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "UserDetails1.db";
    public static final int DATABASE_VERSION = 1;

    public UserDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS " +
                    UserContract.UserDetails.TABLE_NAME     + "(" +
                    UserContract.UserDetails._ID            + " INTEGER PRIMARY KEY, " +
                    UserContract.UserDetails.NAME_COLUMN    + " VARCHAR, " +
                    UserContract.UserDetails.STUDNUMBER_COLUMN + " INTEGER," +
                    UserContract.UserDetails.PASSWORD_COLUMN + " VARCHAR," +
                    UserContract.UserDetails.CONTACTNUMBER_COLUMN + " VARCHAR," +
                    UserContract.UserDetails.USERTYPE_COLUMN + " INTEGER," +
                    UserContract.UserDetails.COURSE_COLUMN + " VARCHAR);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + UserContract.UserDetails.TABLE_NAME);
        onCreate(db);
    }
}

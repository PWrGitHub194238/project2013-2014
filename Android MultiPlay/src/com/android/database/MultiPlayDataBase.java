package com.android.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MultiPlayDataBase extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MultiplayDataBase.db";
	private static final String DEBUG_TAG = "DB";

    public MultiPlayDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
    	Log.d(DEBUG_TAG, "Database creating...");
    	for (DBHelper.SQL_CREATE_DATABASE value : DBHelper.SQL_CREATE_DATABASE.values()) {
	    	Log.d(DEBUG_TAG,value.query);
	        db.execSQL(value.query);
    	}
        Log.d(DEBUG_TAG, "All data is lost.");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	 Log.d(DEBUG_TAG, "Database updating...");
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DBHelper.SQL_DROP_DATABASE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
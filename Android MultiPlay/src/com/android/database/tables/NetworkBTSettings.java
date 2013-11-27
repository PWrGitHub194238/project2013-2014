package com.android.database.tables;

import android.provider.BaseColumns;

import com.android.database.DBHelper;

public class NetworkBTSettings {

	public static abstract class DBSchema implements BaseColumns {
		public static final String TABLE_NAME = "WirelessNetwork";
        public static final String COLUMN_IP = "Copmuter IP";
	}

	public static final String SQL_CREATE_TABLE =
	    "CREATE TABLE " + DBSchema.TABLE_NAME + " ("
	    		+ DBSchema._ID + " INTEGER PRIMARY KEY,"
	    		+ DBSchema.COLUMN_IP + DBHelper.TYPE_TEXT
	    	+ " )" + DBHelper.SEMICOLON_SEP;

	public static final String SQL_DROP_TABLE =
	    "DROP TABLE IF EXISTS " + DBSchema.TABLE_NAME + DBHelper.SEMICOLON_SEP;
}

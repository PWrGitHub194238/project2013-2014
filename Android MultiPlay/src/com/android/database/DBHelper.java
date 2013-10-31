package com.android.database;

import com.android.database.tables.WirelessHistory;
import com.android.database.tables.WirelessNetwork;

public final class DBHelper {

	//SQL types
	public static final String TEXT_TYPE = " TEXT";
	
	//SQL special characters
	public static final String COMMA_SEP = ", ";
	public static final String SEMICOLON_SEP = "; ";
	
	//SQL Create table
	public static final String SQL_CREATE_DATABASE = 
			WirelessHistory.SQL_CREATE_TABLE
			+ WirelessNetwork.SQL_CREATE_TABLE;

	public static final String SQL_DROP_DATABASE = 
			WirelessHistory.SQL_DROP_TABLE
			+ WirelessNetwork.SQL_DROP_TABLE;
	
}

package com.android.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.database.tables.ASCIIButton;
import com.android.database.tables.ConnectionHistory;
import com.android.database.tables.Controllers_detail;
import com.android.database.tables.General;
import com.android.database.tables.MovementButton;
import com.android.database.tables.NetworkBTSettings;
import com.android.database.tables.NetworkWiFiSettings;
import com.android.database.tables.SteeringWheel;

public class DBHelper {
	
	private Context context = null;
	private static SQLiteDatabase db = null;
	
	//SQL types 
	public static final String TYPE_TEXT = " TEXT";
	public static final String TYPE_PK_INT = " INTEGER PRIMARY KEY";
	
	//SQL special characters
	public static final String COMMA_SEP = ",";
	public static final String SEMICOLON_SEP = ";";
	public static final String STAR = "*";
	
	//SQL tables
	private static final String[] TABLE_NAMES = {
		ASCIIButton.DBSchema.TABLE_NAME,
		ConnectionHistory.DBSchema.TABLE_NAME,
		Controllers_detail.DBSchema.TABLE_NAME,
		Controllers_detail.DBSchema.TABLE_NAME,
		General.DBSchema.TABLE_NAME,
		MovementButton.DBSchema.TABLE_NAME,
		NetworkBTSettings.DBSchema.TABLE_NAME,
		NetworkWiFiSettings.DBSchema.TABLE_NAME,
		SteeringWheel.DBSchema.TABLE_NAME,
	};
	
	//SQL Create table
	public static final String SQL_CREATE_DATABASE = 
			ConnectionHistory.SQL_CREATE_TABLE
			+ NetworkBTSettings.SQL_CREATE_TABLE;

	public static final String SQL_DROP_DATABASE = 
			ConnectionHistory.SQL_DROP_TABLE
			+ NetworkBTSettings.SQL_DROP_TABLE;
	
	public DBHelper(Context context) {
		this.context = context;
	}
	
	public void openConnection() {
		MultiPlayDataBase dbHelper = new MultiPlayDataBase(context);
	    try {
	        db = dbHelper.getWritableDatabase();
	    } catch (SQLException e) {
	        db = dbHelper.getReadableDatabase();
	    }
	}
	
	
	public static int sql_generate_minID (String tableName) {
		int n;
		int searchedID = 1;
		int columnIndex;
		int tablesCount = TABLE_NAMES.length;
		String tableKeyName = null;
		Cursor cur = null;
		for ( n = 0; n < tablesCount; n += 1 ) {
			if (tableName .equals(TABLE_NAMES[n])) {
				Log.i("DB","OK" );
				tableKeyName = tableName.concat("ID");
				cur = db.rawQuery("SELECT ? FROM ? ORDERD BY ? ASC", new String[]{tableKeyName,tableName,tableKeyName});
				if ( cur.moveToFirst() == true ) {
					columnIndex = cur.getColumnIndex(tableKeyName);
					while (searchedID == cur.getInt(columnIndex)) {
						searchedID = searchedID + 1;
						if ( cur.moveToNext() == false ) {
							break;
						}
					}
				}
				break;
			}
		}
		return searchedID;
	}
}

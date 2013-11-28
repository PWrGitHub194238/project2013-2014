package com.android.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.database.tables.ASCIIButton;
import com.android.database.tables.ConnectionHistory;
import com.android.database.tables.Controllers;
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
	public static final String TYPE_NULL = "NULL";
	public static final String TYPE_TEXT = "TEXT";
	public static final String TYPE_INT = "INTEGER";
	public static final String TYPE_REAL = "REAL";
	public static final String TYPE_BLOB = "BLOB";
	
	/** Date format. The number of seconds since 1970-01-01 00:00:00 UTC.
	 * 
	 */
	public static final String TYPE_DATE_AS_INT = "INTEGER";
	public static final String TYPE_PK_INT = "INTEGER PRIMARY KEY";
	public static final String TYPE_SK_INT = "INTEGER UNIQUE";
	
	/** Generates a foreign key declaration for creating tables.
	 * 
	 * It makes the values ​​of one column pointing a value of the column from another table, for example:
	 * 
	 * {@code
	 * DBHelper.TYPE_FK(
	 * 	Controllers_detail.DBSchema.COLUMN_1,Controllers.DBSchema.TABLE_NAME,
	 * 	Controllers.DBSchema.KEY_UNIQUE_1)
	 * }
	 * 
	 * will generate a part of query that tells SQLite 
	 * to create a foreign key on {@link Controllers_detail.DBSchema#COLUMN_1}
	 * that will be referring to {@link Controllers.DBSchema#KEY_UNIQUE_1} in another table, 
	 * pointed by its name - {@link Controllers.DBSchema#TABLE_NAME} and it will look like follows:
	 * 
	 * {@code FOREIGN KEY(ControllerID) REFERENCES Controllers(ControllersID)}
	 * 
	 * @param fk_field Column's name that should be a foreign key in table which calls this method.
	 * @param reference_table Table that will b pointed by a <i>fk_field</i>
	 * @param reference_field Column of the <i>reference_table</i> that will be pointed by a <i>fk_field</i>
	 * @return Partial queries for SQLite responsible for creating foreign keys.
	 */
	public static String TYPE_FK (String fk_field, String reference_table, String reference_field) {
		return "FOREIGN KEY("+fk_field+") REFERENCES "+reference_table+"("+reference_field+")";
	}
	
	
	//SQL special characters
	public static final String COMMA_SEP = ",";
	public static final String SEMICOLON_SEP = ";";
	public static final String STAR = "*";
	
	
	//SQL special field values
	public static final String BUTTON_TYPE_ASCII = "ASCIIButton";
	public static final String BUTTON_TYPE_MOVEMENT = "MovementButton";
	public static final String BUTTON_TYPE_STEERING = "SteeringWheel";
	
	public static final String CONTROLLER_TYPE_MOUSE = "";
	public static final String CONTROLLER_TYPE_KEYBOARD = "";
	public static final String CONTROLLER_TYPE_PAD = "";
	public static final String CONTROLLER_TYPE_STEERING = "";
	public static final String CONTROLLER_TYPE_FLIGHT = "";
	
	public static final String TRUE = "1";
	public static final String FALSE = "0";
	
	

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

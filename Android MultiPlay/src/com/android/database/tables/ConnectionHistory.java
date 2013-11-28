package com.android.database.tables;

import java.util.HashMap;

import android.provider.BaseColumns;

import com.android.database.DBHelper;

/** Class that is representing table with the same name in application's database.
 * 
 * This is a helper class that generates queries relevant to the table 
 * that this class represents. The example below shows how to deal with
 * any {@link HashMap} type arguments such as "newValues" in {@link #SQL_UPDATE_ROW(int, HashMap)}:
 * 
 * <a name="example"></a>
 * {@code
 * HashMap<String,String> newValues = new HashMap<String,String>();
 * 	newValues.put(ConnectionHistory.DBSchema.COLUMN_1, "Name");
 * 	newValues.put(ConnectionHistory.DBSchema.COLUMN_2, 12000);
 * ConnectionHistory.SQL_UPDATE_ROW(1, newValues);
 * }
 *
 * 
 * where the keys are the names of the columns in the corresponding table,
 *  which this class represents.
 *
 */
public class ConnectionHistory {

	/** Inner class for {@link ConnectionHistory} that implements {@link BaseColumns}.
	 * 
	 * Additionally, it stores the names of the columns, along with all the other values
	 * that are important to the table, as static final {@link String}s to help you build queries.
	 * All column names are listed like follows:
	 * 
	 * <ul>
	 * 	<li> COLUMN_1 </li>
	 * 	<li> COLUMN_2 </li>
	 * 	<li> ... </li>
	 * 	<li> KEY_UNIQUE_1 </li>
	 * 	<li> KEY_UNIQUE_2 </li>
	 * 	<li> ... </li>
	 * </ul>
	 * 
	 * and so another table attributes that might be used. Be aware that although values 
	 * from KEY_UNIQUE_2 to KEY_UNIQUE_n are optional, values KEY_UNIQUE_1 and TABLE_NAME 
	 * are required to provide correct functionality and they should not be changed.
	 *
	 */
	public static abstract class DBSchema implements BaseColumns {
		public static final String TABLE_NAME = "ConnectionHistory";
        public static final String COLUMN_1 = "Name";
        public static final String COLUMN_2 = "Last_Date";
        public static final String KEY_UNIQUE_1 = "ConnectionHistoryID";
	}
	
	/** Generates String that contains query to database for creating ConnectionHistory table.
	 * 
	 */
	public static final String SQL_CREATE_TABLE =
	    "CREATE TABLE IF NOT EXISTS " + DBSchema.TABLE_NAME + " ("
	    		+ DBSchema._ID + DBHelper.TYPE_PK_INT + ","
	    		+ DBSchema.COLUMN_1 + DBHelper.TYPE_TEXT + ","
	    		+ DBSchema.COLUMN_2 + DBHelper.TYPE_DATE_AS_INT + ","
	    		+ DBSchema.KEY_UNIQUE_1 + DBHelper.TYPE_SK_INT
	    	+ " )";

	/** Generates String that contains query to database for deleting ConnectionHistory table.
	 * 
	 */
	public static final String SQL_DROP_TABLE =
	    "DROP TABLE IF EXISTS " + DBSchema.TABLE_NAME;

}
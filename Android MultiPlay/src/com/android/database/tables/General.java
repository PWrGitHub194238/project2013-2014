package com.android.database.tables;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.provider.BaseColumns;
import android.util.Log;

import com.android.database.DBHelper;

/** Class that is representing table with the same name in application's database.
 * 
 * This is a helper class that generates queries relevant to the table 
 * that this class represents. The example below shows how to deal with
 * any {@link Map} type arguments such as "newValues" in {@link DBHelper#sql_insert_row(Class, Map, boolean)}:
 * 
 * <a name="example"></a>
 * {@code
 * Map<String,String> newValues = new HashMap<String,String>();
 * 	newValues.put(General.DBSchema.COLUMN_1, "Device_Name");
 * 	newValues.put(General.DBSchema.COLUMN_2, DBHelper.FALSE);
 * General.SQL_UPDATE_ROW(1, newValues);
 * }
 *
 * 
 * where the keys are the names of the columns in the corresponding table,
 *  which this class represents.
 *
 */
public final class General implements DBHelper.TableIF {

	/** Inner class for {@link General} that implements {@link BaseColumns}.
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
	public static final class DBSchema implements BaseColumns {
		public static final String TABLE_NAME = "General";
        public static final String COLUMN_1 = "Device_Name";
        public static final String COLUMN_2 = "WiFi_or_BT";
        public static final String COLUMN_3 = "Default_WirelessID";
        public static final String COLUMN_4 = "Default_BletoothID";
        public static final String COLUMN_5 = "BLUETOOTH";
        public static final String COLUMN_6 = "BLE";
        public static final String COLUMN_7 = "WIFI";
        public static final String COLUMN_8 = "WIFI_P2P";
        public static final String COLUMN_9 = "SENSOR_ACCEL";
        public static final String COLUMN_10 = "SENSOR_GYROSCOPE";
        public static final String COLUMN_11 = "SENSOR_GRAVITY";
        public static final String COLUMN_12 = "SENSOR_ROT_VECTOR";
        public static final String COLUMN_13 = "SENSOR_LIN_ACCEL";
        public static final String COLUMN_14 = "MICROPHONE";
        public static final String COLUMN_15 = "MULTITOUCH";
        public static final String COLUMN_16 = "SHOW_HELP";

        public static final String KEY_UNIQUE_1 = "GeneralID";
	}
	
	/** Implementation of {@link DBHelper.TableIF#getTableName()} method.
	 * 
	 * As mentioned here ({@link DBSchema}), {@link DBSchema#TABLE_NAME} value is necessary 
	 * for full functionality and they should not be changed.
	 */
	@Override
	public String getTableName() {
		return DBSchema.TABLE_NAME;
	}

	/** Implementation of {@link DBHelper.TableIF#getKeyUnique1()} method.
	 * 
	 * As mentioned here ({@link DBSchema}), {@link DBSchema#KEY_UNIQUE_1} value is necessary 
	 * for full functionality and they should not be changed.
	 */
	@Override
	public String getKeyUnique1() {
		return DBSchema.KEY_UNIQUE_1;
	}

	/** Generates String that contains query to database for creating General table.
	 * 
	 */
	public static final String SQL_CREATE_TABLE =
		    "CREATE TABLE IF NOT EXISTS " + DBSchema.TABLE_NAME + " ("
		    		+ DBSchema._ID + " " + DBHelper.TYPE_PK_INT + ", "
		    		+ DBSchema.COLUMN_1 + " " + DBHelper.TYPE_TEXT + " " + DBHelper.DEFAULT_NULL + ", "
		    		+ DBSchema.COLUMN_2 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_NULL + ", "
		    		+ DBSchema.COLUMN_3 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_NULL + ", "
		    		+ DBSchema.COLUMN_4 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_NULL + ", "
		    		+ DBSchema.COLUMN_5 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_6 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_7 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_8 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_9 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_10 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_11 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_12 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_13 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_14 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_15 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_FALSE + ", "
		    		+ DBSchema.COLUMN_16 + " " + DBHelper.TYPE_INT + " " + DBHelper.DEFAULT_TRUE + ", "
		    		+ DBSchema.KEY_UNIQUE_1 + " " + DBHelper.TYPE_SK_INT + ", "
		    		+ DBHelper.TYPE_FK(
		    				DBSchema.COLUMN_3,
		    				NetworkWiFiSettings.DBSchema.TABLE_NAME,
		    				NetworkWiFiSettings.DBSchema.KEY_UNIQUE_1) + ", "
		    		+ DBHelper.TYPE_FK(
		    				DBSchema.COLUMN_4,
		    				NetworkBTSettings.DBSchema.TABLE_NAME,
		    				NetworkBTSettings.DBSchema.KEY_UNIQUE_1)
		    	+ " )";

	/** Generates String that contains query to database for deleting General table.
	 * 
	 */
	public static final String SQL_DROP_TABLE =
	    "DROP TABLE IF EXISTS " + DBSchema.TABLE_NAME;


	/** String that contains query to database for selecting rows from table.
	 * 
	 * @param id {@link DBSchema#KEY_UNIQUE_1} value that uniquely represents one row in General table.
	 * @return String that holds a new query for database that selects single row from General table.
	 */
	public static String SQL_SELECT_BY_ID (int id) {
		String query = new String("SELECT " + DBHelper.STAR + " FROM "
				+ DBSchema.TABLE_NAME + " WHERE " + DBSchema.KEY_UNIQUE_1
				+ " = " + id
				);
		return query;
		
	}
	
	/** Generates String that contains query to database for updating rows in General table.
	 * 
	 * @param id {@link DBSchema#KEY_UNIQUE_1} value that uniquely represents one row in General table.
	 * @param newValues Set of pairs in form: ({@literal >}COLUMN_NAME{@literal >},{@literal <}NEW_VALUE{@literal >}) (<a href="#example">Usage example</a>).
	 * @return String that holds a new query for database that updates single row in General table.
	 */
	public static String SQL_UPDATE_ROW (int id, HashMap<String, String> newValues) {
		Iterator<Entry<String, String>> entry_IT = newValues.entrySet().iterator();
		Entry<String, String> curEntry = null;
		StringBuilder query = new StringBuilder("UPDATE " + DBSchema.TABLE_NAME + " SET ");
		while ( entry_IT.hasNext() ) {
			curEntry = entry_IT.next();
			query.append(curEntry.getKey() + " = " + curEntry.getValue());
			if ( entry_IT.hasNext() ) {
				query.append(", ");
			}
		}
		query.append(" WHERE " + DBSchema.KEY_UNIQUE_1 + " = " + id);
		Log.i("DB",query.toString());
		return query.toString();
	}

	/** Generates String that contains query to database for inserting rows in ASCIIButton table.
	 * 
	 * In this method the ID parameter that is used in {@link #SQL_INSERT_ROW(int, HashMap)} 
	 * is auto generated by {@link DBHelper#sql_generate_not_exitsting_minID(Class)} helper class.
	 * 
	 * 
	 * @param newValues Set of pairs in form: ({@literal >}COLUMN_NAME{@literal >},{@literal <}NEW_VALUE{@literal >}) (<a href="#example">Usage example</a>).
	 * @return String that holds a new query for database that inserts single row in ASCIIButton table.
	 */
	public static String SQL_INSERT_ROW (HashMap<String, String> newValues) {
		Iterator<String> keys_IT = newValues.keySet().iterator();
		Iterator<String> values_IT = newValues.values().iterator();
		StringBuilder query = new StringBuilder("INSERT INTO " + DBSchema.TABLE_NAME + " ( ");
		while ( keys_IT.hasNext() ) {
			query.append(keys_IT.next());
			if ( keys_IT.hasNext() ) {
				query.append(", ");
			}
		}
		query.append(" ) VALUES ( ");
		while ( values_IT.hasNext() ) {
			query.append(values_IT.next());
			if ( values_IT.hasNext() ) {
				query.append(", ");
			}
		}
		query.append(" ) ");
		Log.i("DB",query.toString());
		return query.toString();
	}
	
	/** String that contains query to database for inserting rows in ASCIIButton table.
	 * 
	 * @param id {@link DBSchema#KEY_UNIQUE_1} value that will uniquely represents new row in ASCIIButton table.
	 * @param newValues Set of pairs in form: ({@literal >}COLUMN_NAME{@literal >},{@literal <}NEW_VALUE{@literal >}) (<a href="#example">Usage example</a>).
	 * @return String that holds a new query for database that inserts single row in ASCIIButton table.
	 */
	public static String SQL_INSERT_ROW (int id, HashMap<String, String> newValues) {
		Iterator<String> keys_IT = newValues.keySet().iterator();
		Iterator<String> values_IT = newValues.values().iterator();
		StringBuilder query = new StringBuilder("INSERT INTO " + DBSchema.TABLE_NAME + " ( ");
		while ( keys_IT.hasNext() ) {
			query.append(keys_IT.next());
			if ( keys_IT.hasNext() ) {
				query.append(", ");
			}
		}
		query.append(" ) VALUES ( ");
		while ( values_IT.hasNext() ) {
			query.append(values_IT.next());
			if ( values_IT.hasNext() ) {
				query.append(", ");
			}
		}
		query.append(" ) ");
		Log.i("DB",query.toString());
		return query.toString();
	}
	
	public static String SQL_DELETE_ROW (int id) {
		StringBuilder query = new StringBuilder("DELETE FROM " + DBSchema.TABLE_NAME 
				+ " WHERE " + DBSchema.KEY_UNIQUE_1 + " = " + id);
		Log.i("DB",query.toString());
		return query.toString();	
	}
}

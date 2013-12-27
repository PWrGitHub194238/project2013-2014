package com.android.database.tables;

import java.util.Map;

import android.provider.BaseColumns;

import com.android.database.DBHelper;

/** Class that is representing table with the same name in application's database.
 * 
 * This is a helper class that generates queries relevant to the table 
 * that this class represents. The example below shows how to deal with
 * any {@link Map} type arguments such as "newValues" in {@link DBHelper#sql_insert_row(Class, Map, boolean)}:
 * {@code
 * Map<String,String> newValues = new HashMap<String,String>();
 * 	newValues.put(MovementButton.DBSchema.COLUMN_1, 0.56);
 * 	newValues.put(MovementButton.DBSchema.COLUMN_2, DBHelper.FALSE);
 * 	newValues.put(MovementButton.DBSchema.COLUMN_3, "[Crtl]+[A]");
 * MovementButton.SQL_UPDATE_ROW(1, newValues);
 * }
 *
 * 
 * where the keys are the names of the columns in the corresponding table,
 *  which this class represents.
 *
 */
public class MovementButton implements DBHelper.TableIF {

	/** Inner class for {@link MovementButton} that implements {@link BaseColumns}.
	 * 
	 * Additionally, it stores the names of the columns, along with all the other values ​​
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
		public static final String TABLE_NAME = "MovementButton";
        public static final String COLUMN_1 = "Sensitive";
        public static final String COLUMN_2 = "Is_Inverted";
        public static final String COLUMN_3 = "ShortTap_Key_Combination";
        public static final String KEY_UNIQUE_1 = "MovementButtonID";
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

	/** Generates String that contains query to database for creating MovementButton table.
	 * 
	 */
	public static final String SQL_CREATE_TABLE =
		    "CREATE TABLE IF NOT EXISTS " + DBSchema.TABLE_NAME + " ("
		    		+ DBSchema._ID + " " + DBHelper.TYPE_PK_INT + ", "
		    		+ DBSchema.COLUMN_1 + " " + DBHelper.TYPE_REAL + ", "
		    		+ DBSchema.COLUMN_2 + " " + DBHelper.TYPE_INT + ", "
		    		+ DBSchema.COLUMN_3 + " " + DBHelper.TYPE_TEXT + ", "
		    		+ DBSchema.KEY_UNIQUE_1 + " " + DBHelper.TYPE_SK_INT
		    	+ " )";

	/** Generates String that contains query to database for deleting MovementButton table.
	 * 
	 */
	public static final String SQL_DROP_TABLE =
	    "DROP TABLE IF EXISTS " + DBSchema.TABLE_NAME;

}
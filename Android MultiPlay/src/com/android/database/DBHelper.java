package com.android.database;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.application.MultiPlayApplication;
import com.android.application.WirelessConfigurationClass;
import com.android.database.tables.ASCIIButton;
import com.android.database.tables.ConnectionHistory;
import com.android.database.tables.Controllers;
import com.android.database.tables.Controllers_detail;
import com.android.database.tables.General;
import com.android.database.tables.MovementButton;
import com.android.database.tables.NetworkBTSettings;
import com.android.database.tables.NetworkWiFiSettings;
import com.android.database.tables.SteeringWheel;
import com.android.services.ConnectionHelper;


public class DBHelper {
	
	public interface TableIF {
		public String getTableName();
		public String getKeyUnique1();
	}
	
	private static class QueryBuilder {
		public static String iterateOverNames(Iterator<? extends Object> iterator) {
			StringBuilder query = new StringBuilder();
			while (iterator.hasNext()) {
				query.append(iterator.next().toString());
				if ( iterator.hasNext() ) {
					query.append(", ");
				}
			}
			return query.toString();
		}
		
		public static String iterateOverValues(Iterator<? extends Object> iterator) {
			StringBuilder query = new StringBuilder();
			while (iterator.hasNext()) {
				query.append(
						"'" + iterator.next().toString() + "'"
						);
				if ( iterator.hasNext() ) {
					query.append(", ");
				}
			}
			return query.toString();
		}
		public static String STATEMENT_IN(Iterator<? extends Object> iterator) {
			StringBuilder query = new StringBuilder("IN (");
			query.append(
					iterateOverValues(iterator));
			query.append(" )");
			return query.toString();
		}
	};
	
	private Context context = null;
	MultiPlayDataBase dbHelper = null;
	private static SQLiteDatabase db = null;
	
	//SQL types 
	public static final String TYPE_NULL = "NULL";
	public static final String TYPE_TEXT = "TEXT";
	public static final String TYPE_INT = "INTEGER";
	public static final String TYPE_REAL = "REAL";
	public static final String TYPE_BLOB = "BLOB";
	
	public static final String DEFAULT_NULL = "DEFAULT NULL";
	
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
	public static enum SQL_TABLE_NAME {
		TABLE_ASCIIButton(ASCIIButton.DBSchema.TABLE_NAME),
		TABLE_ConnectionHistory(ConnectionHistory.DBSchema.TABLE_NAME),
		TABLE_Controllers_detail(Controllers_detail.DBSchema.TABLE_NAME),
		TABLE_Controllers(Controllers.DBSchema.TABLE_NAME),
		TABLE_General(General.DBSchema.TABLE_NAME),
		TABLE_MovementButton(MovementButton.DBSchema.TABLE_NAME),
		TABLE_NetworkBTSettings(NetworkBTSettings.DBSchema.TABLE_NAME),
		TABLE_NetworkWiFiSettings(NetworkWiFiSettings.DBSchema.TABLE_NAME),
		TABLE_SteeringWheel(SteeringWheel.DBSchema.TABLE_NAME);
		
		String tableName = null;
		
		private SQL_TABLE_NAME(String tableName) {
			this.tableName = tableName;
		}
	};
	
	//SQL Create table
	public static enum SQL_CREATE_DATABASE {
		TABLE_ASCIIButton(ASCIIButton.SQL_CREATE_TABLE),
		TABLE_ConnectionHistory(ConnectionHistory.SQL_CREATE_TABLE),
		TABLE_Controllers_detail(Controllers_detail.SQL_CREATE_TABLE),
		TABLE_Controllers(Controllers.SQL_CREATE_TABLE),
		TABLE_General(General.SQL_CREATE_TABLE),
		TABLE_MovementButton(MovementButton.SQL_CREATE_TABLE),
		TABLE_NetworkBTSettings(NetworkBTSettings.SQL_CREATE_TABLE),
		TABLE_NetworkWiFiSettings(NetworkWiFiSettings.SQL_CREATE_TABLE),
		TABLE_SteeringWheel(SteeringWheel.SQL_CREATE_TABLE);
		
		String query = null;
		
		private SQL_CREATE_DATABASE(String query) {
			this.query = query;
		}
	};

	public static final String SQL_DROP_DATABASE = 
			ASCIIButton.SQL_DROP_TABLE + "; "
			+ ConnectionHistory.SQL_DROP_TABLE + "; "
			+ Controllers_detail.SQL_DROP_TABLE + "; "
			+ Controllers.SQL_DROP_TABLE + "; "
			+ General.SQL_DROP_TABLE + "; "
			+ MovementButton.SQL_DROP_TABLE + "; "
			+ NetworkBTSettings.SQL_DROP_TABLE + "; "
			+ NetworkWiFiSettings.SQL_DROP_TABLE + "; "
			+ SteeringWheel.SQL_DROP_TABLE + "; ";
	
	
	public static final boolean CLEAR_YES = true;
	public static final boolean CLEAR_NO = false;
	
	public static final boolean REOPEN_YES = true;
	public static final boolean REOPEN_NO = false;

	
	public DBHelper(Context context) {
		this.context = context;
	}
	
	public void openConnection() {
		if ( dbHelper == null ) {
			dbHelper = new MultiPlayDataBase(context);
		}
		
		if ( db == null ) {
		    try {
		        db = dbHelper.getWritableDatabase();
		    } catch (SQLException e) {
		        db = dbHelper.getReadableDatabase();
		    }
		}
	}
	
	public void closeConnection() {
		Log.d("DB","CLOSING");
		if (db != null ) {
		    db.close();
		    db = null;
		}
	}

	/** Finds minimum ID that not exist in chosen table.
	 * 
	 * The method performs a query that selects IDs from table <i>tableName</i> 
	 * and returns the minimum, not existing, value.
	 * 
	 * @param tableClass One of table names from {@link SQL_TABLE_NAME}.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return Minimal ID for the selected <i>tableName</i> that not exists yet (eg for <i>INSERT</i> queries)
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public static int sql_generate_not_exitsting_minID (Class<? extends DBHelper.TableIF> tableClass, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		int searchedID = 1;
		Cursor cur = null;
		DBHelper.TableIF table = tableClass.newInstance();
		
		String KEY_UNIQUE_1 = table.getKeyUnique1();
		
		StringBuilder query = new StringBuilder(
				"SELECT " + KEY_UNIQUE_1 + " FROM " + table.getTableName() + " ORDER BY " + KEY_UNIQUE_1
		);
		Log.d("DB",(db.isOpen())?"true":"false");
		cur = db.rawQuery(query.toString(), null);
		if ( cur.moveToFirst() == true ) {
			int columnIndex = cur.getColumnIndex(KEY_UNIQUE_1);
			while (searchedID == cur.getInt(columnIndex)) {
				searchedID = searchedID + 1;
				if ( cur.moveToNext() == false ) {
					break;
				}
			}
		}
		Log.d("DB", String.valueOf(searchedID));
		if (reopenConnection == true ) {
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
		}
		return searchedID;
	}
	
	/** Select query to database that returns every row.
	 * 
	 * Usage example:
	 * 
	 * {@code
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		dbHelper.openConnection();
		Cursor cur;
		try {
			cur = dbHelper.sql_select_by_id(General.class);
			Log.d("DB", DatabaseUtils.dumpCursorToString(cur));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 * }
	 * 
	 * In order to view returned data you can use {@link DatabaseUtils#dumpCursorToString(Cursor)} method.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return {@link Cursor} to returned set of data by execution of previously generated select query in this method.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public Cursor sql_select_by_id(Class<? extends DBHelper.TableIF> tableClass, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Cursor cursor = null;
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();
		
		StringBuilder query = new StringBuilder(
				"SELECT " + DBHelper.STAR + " FROM " + TABLE_NAME
				);
		
		Log.d("DB", query.toString());
		cursor = db.rawQuery(query.toString(), null);
		cursor.moveToFirst();
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
		return cursor;
	}
	
	/** Select query to database that returns one row which matches to KEY_UNIQUE.
	 * 
	 * Usage example:
	 * 
	 * {@code
		DBHelper dbHelper = new DBHelper(getApplicationContext());
		dbHelper.openConnection();
		Cursor cur;
		try {
			cur = dbHelper.sql_select_by_id(General.class,1);
			Log.d("DB", DatabaseUtils.dumpCursorToString(cur));
		} catch (Exception e) {
			e.printStackTrace();
		}
	 * }
	 * 
	 * In order to view returned data you can use {@link DatabaseUtils#dumpCursorToString(Cursor)} method.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param KEY_UNIQUE ID value that uniquely represents one row in <i>tableClass</i> table.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return {@link Cursor} to returned set of data by execution of previously generated select query in this method.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public Cursor sql_select_by_id(Class<? extends DBHelper.TableIF> tableClass, int KEY_UNIQUE, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Cursor cursor = null;
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();
		String KEY_UNIQUE_1 = table.getKeyUnique1();
		
		StringBuilder query = new StringBuilder(
				"SELECT " + DBHelper.STAR + " FROM "
				+ TABLE_NAME + " WHERE " + KEY_UNIQUE_1
				+ " = " + KEY_UNIQUE
				);
		
		Log.d("DB", query.toString());
		cursor = db.rawQuery(query.toString(), null);
		cursor.moveToFirst();
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
		return cursor;
	}
	
	/** Select query to database that returns selected columns in one row which matches to KEY_UNIQUE.
	 * 
	 * Usage example:
	 * 
	 * {@code
	 * DBHelper dbHelper = new DBHelper(getApplicationContext());
	 * dbHelper.openConnection();
	 * try {
	 * 	Set<String> columns = new HashSet<String>();
	 * 	columns.add(General.DBSchema.COLUMN_1);
	 * 	Cursor cur = dbHelper.sql_select_by_id(General.class,columns,1);
	 * }
	 * }
	 * 
	 * In order to view returned data you can use {@link DatabaseUtils#dumpCursorToString(Cursor)} method.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param columnNames Column names that should be selected in database query.
	 * @param KEY_UNIQUE ID value that uniquely represents one row in <i>tableClass</i> table.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return {@link Cursor} to returned set of data by execution of previously generated select query in this method.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public Cursor sql_select_by_id(Class<? extends DBHelper.TableIF> tableClass, Set<String> columnNames, int KEY_UNIQUE, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Cursor cursor = null;
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		if (columnNames!=null && !columnNames.isEmpty() ) {
			
			DBHelper.TableIF table = tableClass.newInstance();
			String TABLE_NAME = table.getTableName();
			String KEY_UNIQUE_1 = table.getKeyUnique1();
			
			StringBuilder query = new StringBuilder("SELECT ");
			
			query.append(
					QueryBuilder.iterateOverNames(
							columnNames.iterator()));
			
			query.append(
					" FROM " + TABLE_NAME + " WHERE " + KEY_UNIQUE_1 + " = " + KEY_UNIQUE
					);
			
			Log.d("DB", query.toString());
			cursor = db.rawQuery(query.toString(), null);
			cursor.moveToFirst();
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
			return cursor;
			
		} else {
			return sql_select_by_id(tableClass,KEY_UNIQUE,REOPEN_NO);
		}	
	}
	
	/** Select query to database that returns rows which matches to one of KEY_UNIQUEs.
	 * 
	 * Usage example:
	 * 
	 * {@code
	 * DBHelper dbHelper = new DBHelper(getApplicationContext());
	 * dbHelper.openConnection();
	 * try {
	 * 	Set<Integer> keys = new HashSet<Integer>();
	 * 	keys.add(1);
	 * 	Cursor cur = dbHelper.sql_select_by_id(General.class,keys);
	 * }
	 * }
	 * 
	 * In order to view returned data you can use {@link DatabaseUtils#dumpCursorToString(Cursor)} method.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param KEY_UNIQUEs ID values that uniquely represents rows in <i>tableClass</i> table.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return {@link Cursor} to returned set of data by execution of previously generated select query in this method.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public Cursor sql_select_by_id(Class<? extends DBHelper.TableIF> tableClass, Set<Integer> KEY_UNIQUEs, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Cursor cursor = null;
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();
		String KEY_UNIQUE_1 = table.getKeyUnique1();
		
		StringBuilder query = new StringBuilder(
				"SELECT " + DBHelper.STAR + " FROM "
				+ TABLE_NAME + " WHERE " + KEY_UNIQUE_1
				+ " "
				);
		
		query.append(
				QueryBuilder.STATEMENT_IN(
						KEY_UNIQUEs.iterator()));
		
		Log.d("DB", query.toString());
		cursor = db.rawQuery(query.toString(), null);
		cursor.moveToFirst();
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
		return cursor;
	}
	
	/** Select query to database that returns selected columns in rows which matches to one of KEY_UNIQUEs.
	 * 
	 * Usage example:
	 * 
	 * {@code
	 * DBHelper dbHelper = new DBHelper(getApplicationContext());
	 * dbHelper.openConnection();
	 * try {
	 * 	Set<String> columns = new HashSet<String>();
	 * 	columns.add(General.DBSchema.COLUMN_1);
	 * 	Set<Integer> keys = new HashSet<Integer>();
	 * 	keys.add(1);
	 * 	Cursor cur = dbHelper.sql_select_by_id(General.class,columns,keys);
	 * }
	 * }
	 * 
	 * In order to view returned data you can use {@link DatabaseUtils#dumpCursorToString(Cursor)} method.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param columnNames Column names that should be selected in database query.
	 * @param KEY_UNIQUEs ID values that uniquely represents rows in <i>tableClass</i> table.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @return {@link Cursor} to returned set of data by execution of previously generated select query in this method.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public Cursor sql_select_by_id(Class<? extends DBHelper.TableIF> tableClass, Set<String> columnNames, Set<Integer> KEY_UNIQUEs, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Cursor cursor = null;
		Log.d("DB", "Database select...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();
		String KEY_UNIQUE_1 = table.getKeyUnique1();
		
		StringBuilder query = new StringBuilder("SELECT ");
		
		query.append(
				QueryBuilder.iterateOverNames(
						columnNames.iterator()));
		
		query.append(
				" FROM "
				+ TABLE_NAME + " WHERE " + KEY_UNIQUE_1
				+ " "
				);
		
		query.append(
				QueryBuilder.STATEMENT_IN(
						KEY_UNIQUEs.iterator()));
		
		Log.d("DB", query.toString());
		cursor = db.rawQuery(query.toString(), null);
		cursor.moveToFirst();
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
		return cursor;
	}
	
	/** Insert query to database.
	 * 
	 * In this method the ID parameter that is used in {@link #sql_insert_row(Class, Map, boolean)} 
	 * is auto generated by {@link #sql_generate_not_exitsting_minID(Class)} helper class.
	 * 
	 * Usage example:
	 * 
	 * {@code
	 * DBHelper dbHelper = new DBHelper(getApplicationContext());
	 * dbHelper.openConnection();
	 * try {
	 * 	Set<String> columns = new HashSet<String>();
	 * 	columns.add(General.DBSchema.COLUMN_1);
	 * 	Set<Integer> keys = new HashSet<Integer>();
	 * 	keys.add(1);
	 * 	Cursor cur = dbHelper.sql_select_by_id(General.class,columns,keys);
	 * }
	 * }
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param newValues Set of pairs in form: ({@literal <}COLUMN_NAME{@literal >},{@literal <}NEW_VALUE{@literal >}) (<a href="#example">Usage example</a>).
	 * @param isToClear Flag that can only take the values of {@link #CLEAR_YES} or {@link #CLEAR_NO}. It determinates 
	 * whenever to call {@link Map#clear()} method in order 
	 * to avoid creating new {@link Map} object for another execution of that method.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public void sql_insert_row (Class<? extends DBHelper.TableIF> tableClass, Map<String, String> newValues, boolean isToClear, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		if (newValues == null || !newValues.isEmpty()) {
			Log.d("DB", "Database insert...");
			Log.d("TYPE", tableClass.getName());
			
			DBHelper.TableIF table = tableClass.newInstance();
			String TABLE_NAME = table.getTableName();
			String KEY_UNIQUE_1 = table.getKeyUnique1();
			
			StringBuilder query = new StringBuilder("INSERT INTO " + TABLE_NAME + " ( " + KEY_UNIQUE_1 + ", ");
			
			query.append(
					QueryBuilder.iterateOverValues(
							newValues.keySet().iterator()));
			
			query.append(" ) VALUES ( " + sql_generate_not_exitsting_minID(tableClass,REOPEN_NO) + ", ");
			
			query.append(
					QueryBuilder.iterateOverValues(
							newValues.values().iterator()));
			
			query.append(" ) ");
			Log.i("DB",query.toString());
			db.execSQL(query.toString());

			Log.i("DB","OK");
			if (isToClear == true) {
				newValues.clear();
			}
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
			return;
		} else {
			Log.d("DB", "Database empty insert!");
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
			return;
		}
	}
	
	/** Insert query to database.
	 * 
	 * @param tableClass The table in the database that is represented by a class that implements {@link DBHelper.TableIF}.
	 * @param KEY_UNIQUE ID value that uniquely represents one row in <i>tableClass</i> table.
	 * @param newValues Set of pairs in form: ({@literal <}COLUMN_NAME{@literal >},{@literal <}NEW_VALUE{@literal >}) (<a href="#example">Usage example</a>).
	 * @param isToClear Flag that can only take the values of {@link #CLEAR_YES} or {@link #CLEAR_NO}. It determinates 
	 * whenever to call {@link Map#clear()} method in order 
	 * to avoid creating new {@link Map} object for another execution of that method.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public void sql_insert_row (Class<? extends DBHelper.TableIF> tableClass, int KEY_UNIQUE, Map<String, String> newValues, boolean isToClear, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		if (newValues == null || !newValues.isEmpty()) {
			Log.d("DB", "Database insert...");
			Log.d("TYPE", tableClass.getName());
			
			DBHelper.TableIF table = tableClass.newInstance();
			String TABLE_NAME = table.getTableName();
			String KEY_UNIQUE_1 = table.getKeyUnique1();
			
			StringBuilder query = new StringBuilder("INSERT INTO " + TABLE_NAME + " ( " + KEY_UNIQUE_1 + ", ");
			
			query.append(
					QueryBuilder.iterateOverValues(
							newValues.keySet().iterator()));
			
			query.append(" ) VALUES ( " + KEY_UNIQUE + ", ");
			
			query.append(
					QueryBuilder.iterateOverValues(
							newValues.values().iterator()));
			
			query.append(" ) ");
			Log.i("DB",query.toString());
			db.execSQL(query.toString());

			Log.i("DB","OK");
			if (isToClear == true) {
				newValues.clear();
			}
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
			return;
		} else {
			Log.d("DB", "Database empty insert!");
			
			if (reopenConnection == true ) {
				MultiPlayApplication.getDbHelper().closeConnection();
			}
			return;
		}
	}
	
	/** Delete query to database.
	 * 
	 * @param tableClass One of table names from {@link SQL_TABLE_NAME}.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public void sql_delete_row (Class<? extends DBHelper.TableIF> tableClass, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Log.d("DB", "Database delete...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();

		StringBuilder query = new StringBuilder(
				"DELETE FROM " + TABLE_NAME
				);
		Log.i("DB",query.toString());
		db.execSQL(query.toString());
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
	}
	
	/** Delete query to database.
	 * 
	 * @param tableClass One of table names from {@link SQL_TABLE_NAME}.
	 * @param KEY_UNIQUE ID values that uniquely represents rows in <i>tableClass</i> table.
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public void sql_delete_row (Class<? extends DBHelper.TableIF> tableClass, int KEY_UNIQUE, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Log.d("DB", "Database delete...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();
		String KEY_UNIQUE1 = table.getKeyUnique1();

		StringBuilder query = new StringBuilder(
				"DELETE FROM " + TABLE_NAME + " WHERE " + KEY_UNIQUE1 + " = " + KEY_UNIQUE
				);
		Log.i("DB",query.toString());
		db.execSQL(query.toString());
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
	}
	
	/**
	 * 
	 * @param tableClass todo
	 * @param KEY_UNIQUEs todo
	 * @param reopenConnection determinates if new connection to database should be opened. 
	 * Keep in mind that by setting value as <i>true</i>, after executing a query, connection will be closed 
	 * and it needs to be reopen before executing another query (by passing the same argument as <i>true</i>).
	 * Otherwise no additional actions for opening/closing database connection will be taken.
	 * 
	 * @throws IllegalAccessException Thrown when a program attempts to access a tableClass 
	 * which is not accessible from the location where the reference is made.
	 * @throws InstantiationException Thrown when a program attempts to access a tableClass' constructor
	 * which is not accessible from the location where the reference is made.
	 */
	public void sql_delete_row (Class<? extends DBHelper.TableIF> tableClass, Set<Integer> KEY_UNIQUEs, boolean reopenConnection) throws InstantiationException, IllegalAccessException {
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().openConnection();
		}
		
		Log.d("DB", "Database delete...");
		Log.d("TYPE", tableClass.getName());
		
		DBHelper.TableIF table = tableClass.newInstance();
		String TABLE_NAME = table.getTableName();

		StringBuilder query = new StringBuilder(
				"DELETE FROM " + TABLE_NAME
				);
		Log.i("DB",query.toString());
		db.execSQL(query.toString());
		
		if (reopenConnection == true ) {
			MultiPlayApplication.getDbHelper().closeConnection();
		}
	}
	
	
	
	public static Collection<? extends WirelessConfigurationClass> parseNetworkWiFiSettings( Cursor cursor ) {
		Set<WirelessConfigurationClass> parsedConfigurations = new HashSet<WirelessConfigurationClass>();
		WirelessConfigurationClass readedConfiguration = null;
		if (cursor.moveToFirst() == true) {
			Log.d("DB", "OK1");
			while (cursor.isAfterLast() == false ) {
				Log.d("DB", "OK2");
				readedConfiguration = new WirelessConfigurationClass(
						cursor.getString(cursor.getColumnIndex(NetworkWiFiSettings.DBSchema.COLUMN_2)), 
						cursor.getInt(cursor.getColumnIndex(NetworkWiFiSettings.DBSchema.COLUMN_3)));
				
				readedConfiguration.setName(
						cursor.getString(cursor.getColumnIndex(NetworkWiFiSettings.DBSchema.COLUMN_1)));
				
				readedConfiguration.setConnectionStatus(ConnectionHelper.STATUS_WARNING);
				readedConfiguration.setStored(true);
				readedConfiguration.setStoredIndex(cursor.getPosition());
	
				Log.d("DB", DatabaseUtils.dumpCurrentRowToString(cursor));
				
				Log.d("DB", "conStatus > "+ String.valueOf(readedConfiguration.getConnectionStatus()));
				Log.d("DB", "Index > "+ String.valueOf(readedConfiguration.getStoredIndex()));
				Log.d("DB", "IP > "+ String.valueOf(readedConfiguration.getIP()));
				Log.d("DB", "MAC > "+ String.valueOf(readedConfiguration.getMACAdress()));
				Log.d("DB", "Name > "+ String.valueOf(readedConfiguration.getName()));
				Log.d("DB", "Port > "+ String.valueOf(readedConfiguration.getPort()));
				Log.d("DB", "System > "+ String.valueOf(readedConfiguration.getSystem()));
	
				parsedConfigurations.add(readedConfiguration);
				cursor.moveToNext();
			}
		}
		return parsedConfigurations;
	}
}

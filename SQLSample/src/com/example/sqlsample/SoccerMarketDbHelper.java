package com.example.sqlsample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SoccerMarketDbHelper extends SQLiteOpenHelper {

	private static final String TEXT_TYPE = " TEXT";
	private static final String INT_TYPE = " INT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + SoccerMarketDbContract.PlayerEntry.TABLE_NAME + " (" +
	    		SoccerMarketDbContract.PlayerEntry._ID + " INTEGER PRIMARY KEY," +
	    		SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    		SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_AGE + INT_TYPE + COMMA_SEP +
	    		SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_POSITION + TEXT_TYPE + COMMA_SEP +
	    		SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_CLUB + TEXT_TYPE +
	    " )";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + SoccerMarketDbContract.PlayerEntry.TABLE_NAME;
	
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SoccerMarket.db";

    public SoccerMarketDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
 
    public void deletePlayer(String name)
    {
    	// Gets the data repository in write mode
    	SQLiteDatabase db = getWritableDatabase();

    	String selection = SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_NAME + "=?";
    	String[] selelectionArgs = { name };
    	db.delete(SoccerMarketDbContract.PlayerEntry.TABLE_NAME, selection, selelectionArgs);
    }
    
	public void insertPlayer(String name, int age, String position, String club)
	{
		deletePlayer(name);
		
		SQLiteDatabase db = getWritableDatabase();
		
		// Create a new map of values, where column names are the keys
		ContentValues values = new ContentValues();
		values.put(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_NAME, name);
		values.put(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_AGE, age);
		values.put(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_POSITION, position);
		values.put(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_CLUB, club);
		
		// Insert the new row, returning the primary key value of the new row
		db.insert(
				SoccerMarketDbContract.PlayerEntry.TABLE_NAME,
				null,
		        values);
	}
	
	public Cursor queryPlayer(String selection, String[] selelectionArgs)
	{
		SQLiteDatabase db = getReadableDatabase();
		
		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
			    SoccerMarketDbContract.PlayerEntry._ID,
			    SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_NAME,
			    SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_POSITION,
			    SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_AGE,
		    };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_AGE + " DESC";
		
		Cursor cursor = db.query(
				SoccerMarketDbContract.PlayerEntry.TABLE_NAME,  // The table to query
				projection,                               // The columns to return
				selection,                                // The columns for the WHERE clause
				selelectionArgs,                            // The values for the WHERE clause
				null,                                     // don't group the rows
				null,                                     // don't filter by row groups
				sortOrder                                 // The sort order
		    );
		
		return cursor;
	}
}

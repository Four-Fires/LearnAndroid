package com.example.sqlsample;

import android.provider.BaseColumns;

public class SoccerMarketDbContract {
	
	private SoccerMarketDbContract() {}
	
	public static abstract class PlayerEntry implements BaseColumns {
	    public static final String TABLE_NAME = "playerinfo";
	    public static final String COLUMN_NAME_NAME = "name";	 
	    public static final String COLUMN_NAME_AGE = "age";
	    public static final String COLUMN_NAME_POSITION = "position";
	    public static final String COLUMN_NAME_CLUB = "club";
	}
}

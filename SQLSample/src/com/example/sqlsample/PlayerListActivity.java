package com.example.sqlsample;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PlayerListActivity extends ListActivity implements DialogInterface.OnClickListener {
	
	SoccerMarketDbHelper mDbHelper;
	List<String> mPlayers;
	int mClickedItemPosition;
		
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);

	    mDbHelper = new SoccerMarketDbHelper(this);	    
	}
	
	@Override
	protected void onStart() {
	    super.onStart();  // Always call the superclass method first
	    
	    loadPlayers();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.options_menu, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    //respond to menu item selection
		switch (item.getItemId())
		{
		case R.id.addplayer:
			startActivity(new Intent(this, AddPlayerActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void loadPlayers()
	{
		Cursor cursor = mDbHelper.queryPlayer(null, null);

		mPlayers = new ArrayList<String>();
		cursor.moveToFirst();
		for	(int i=0; i < cursor.getCount(); i++)
		{
			cursor.moveToPosition(i);
			mPlayers.add(cursor.getString(cursor.getColumnIndexOrThrow(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_NAME))
						+ ", "
						+ cursor.getString(cursor.getColumnIndexOrThrow(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_POSITION))
						+ ", "
						+ cursor.getString(cursor.getColumnIndexOrThrow(SoccerMarketDbContract.PlayerEntry.COLUMN_NAME_AGE)));
		}
		
		ArrayAdapter<String> playersAdapter = new ArrayAdapter<String>(this, 
	            android.R.layout.simple_list_item_1, 
	            android.R.id.text1, 
	            mPlayers);

	    this.setListAdapter(playersAdapter);
	}
	
	protected void onListItemClick (ListView l, View v, int position, long id)
	{
		Locale locale = new Locale("en", "CANADA");
		String logmsg = String.format(locale, "position %d clicked", position);
		Log.d("PlayerListActivity.onListItemClick", logmsg);
		
		mClickedItemPosition = position;
		
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete...");
        // Setting Dialog Message
        String alertmsg = "Are you sure you want delete " + mPlayers.get(position) + "?";
        alertDialog.setMessage(alertmsg);
 
        // Setting Icon to Dialog
//        alertDialog.setIcon(R.drawable.delete);
        
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("Confirm", this);
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Cancel", this);
 
        // Showing Alert Message
        alertDialog.show();
	}
	
	public void onClick(DialogInterface dialog,int which) {
    	// Write your code here to invoke YES event
		if (which == DialogInterface.BUTTON_POSITIVE)
		{
			Toast.makeText(getApplicationContext(), "You clicked on Confirm", Toast.LENGTH_SHORT).show();
			int endindex = mPlayers.get(mClickedItemPosition).indexOf(",");
			mDbHelper.deletePlayer(mPlayers.get(mClickedItemPosition).substring(0, endindex));
			mClickedItemPosition = -1;
			loadPlayers();
		}
		else
		{
			// Write your code here to invoke NO event
			Toast.makeText(getApplicationContext(), "You clicked on Cancel", Toast.LENGTH_SHORT).show();
			dialog.cancel();
			mClickedItemPosition = -1;
		}
    }
}
package com.example.sqlsample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddPlayerActivity extends Activity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
    }
	
	   public void onSubmit(View view) {
	        // Do something in response to button
		   SoccerMarketDbHelper dbHelper = new SoccerMarketDbHelper(this);
		   dbHelper.insertPlayer(((EditText) findViewById(R.id.editTextName)).getText().toString(),
				   				Integer.parseInt(((EditText) findViewById(R.id.editTextAge)).getText().toString()),
				   				((EditText) findViewById(R.id.editTextPosition)).getText().toString(),
				   				((EditText) findViewById(R.id.editTextClub)).getText().toString());
		   finish();
	    }
}

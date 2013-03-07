package com.example.fragmentsample;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements PlayerListFragment.OnPlayerSelectionListener/*PlayerListFragmentDelegate*/
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
     // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null)
        {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null)
            {
                return;
            }

            // Create an instance of ExampleFragment
            PlayerListFragment firstFragment = new PlayerListFragment();
//            FragmentProperties firstFragment = new FragmentProperties();
            
            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());
            
            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
	
    /** Called when the user clicks a Player button */
    public void onPlayerButton(View view)
    {    	
    	selectedPlayer(((Button)view).getText().toString());    	
    }
    
    public void selectedPlayer(String player)
    {
    	if (findViewById(R.id.fragment_container) != null)
    	{    		
    		// Create fragment and give it an argument specifying the article it should show
    		PlayerDetailFragment newFragment = new PlayerDetailFragment();
    		Bundle args = new Bundle();
    		args.putString(PlayerDetailFragment.CLUB_NAME, PlayerClubDictionary.clubOfPlayer(this, player));
    		newFragment.setArguments(args);

    		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

    		// Replace whatever is in the fragment_container view with this fragment,
    		// and add the transaction to the back stack so the user can navigate back
    		transaction.replace(R.id.fragment_container, newFragment);
    		transaction.addToBackStack(null);

    		// Commit the transaction
    		transaction.commit();
    	}
    	else
    	{
    		PlayerDetailFragment propertyFragment = (PlayerDetailFragment) getSupportFragmentManager().findFragmentById(R.id.playerdetail_fragment);
    		propertyFragment.updatePlayerDetail(PlayerClubDictionary.clubOfPlayer(this, player));
    	}
    }
}

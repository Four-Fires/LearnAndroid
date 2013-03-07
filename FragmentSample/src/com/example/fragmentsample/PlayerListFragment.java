package com.example.fragmentsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PlayerListFragment extends Fragment implements OnClickListener {
	
	OnPlayerSelectionListener mCallback;
	
	public interface OnPlayerSelectionListener {
        public void selectedPlayer(String player);
    }

	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnPlayerSelectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPlayerSelectionListener");
        }
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.playerlist_fragment, container, false);
		Button b1 = (Button) v.findViewById(R.id.button1);
		Button b2 = (Button) v.findViewById(R.id.button2);
		Button b3 = (Button) v.findViewById(R.id.button3);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        return v;
    }
	
	@Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button1:
        case R.id.button2:
        case R.id.button3:
        	mCallback.selectedPlayer(((Button)v).getText().toString()); 
        	break;
        default:
            break;
        }
    }
}

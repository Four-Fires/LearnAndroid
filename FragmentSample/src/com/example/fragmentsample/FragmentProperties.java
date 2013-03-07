package com.example.fragmentsample;

//import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentProperties extends Fragment {
	
//	public static final String CLUB_NAME = Resources.getSystem().getString(R.string.Club_Name);
	public static final String CLUB_NAME = "Club_Name";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View ret = inflater.inflate(R.layout.property_fragment, container, false);
		
		if (savedInstanceState == null)
		{
			EditText clubEdit =  (EditText) ret.findViewById(R.id.editTextClub);
			String club = getArguments().getString(CLUB_NAME);
			clubEdit.setText(club);
		}
		
        return ret;
    }
}

package com.example.fragmentsample;

import android.content.Context;

public class PlayerClubDictionary {
	public static String clubOfPlayer(Context context, String player)
	{
		String ret = null;
		String drogba = context.getString(R.string.Didier_Drogba);
		String messi = context.getString(R.string.Lionel_Messi);
		String ronaldo = context.getString(R.string.Cristiano_Ronaldo);
		
		if (player.equals(drogba))
			ret = context.getString(R.string.Galatasaray);
		else if (player.equals(messi))
			ret = context.getString(R.string.Barcelona);
		else if (player.equals(ronaldo))
			ret = context.getString(R.string.Real_Madrid);
		
		return ret;
	}
}

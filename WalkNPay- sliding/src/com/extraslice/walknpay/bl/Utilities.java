package com.extraslice.walknpay.bl;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

public class Utilities {

	public static double total = 0.00;
	public static double rewards = 0.00;
	public static String card;
	public static String card_holder;
	public static String expiry_date;
	public static String store= "ABC STORE";
	public static boolean islogged=false;
	public static boolean confirm_store = false;
	public static boolean pass_wallet = true;
	public static boolean firstrun=false;
    public static String store_info = "";
	Typeface  tfTangerine;

	public static String getUsername(Context context) {
		String value = "No";
		SharedPreferences prefs = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		value = prefs.getString("Username", "");
		return value;
	}


	public static String getPassword(Context context) {
		String value = "No";
		SharedPreferences prefs = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		value = prefs.getString("Password", "");
		return value;
	}

	/** saving login Credentials in sharedpreferences */
	public static void setCredentials(String username, String password, Context context) {
		SharedPreferences prefs = context.getSharedPreferences("pref",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor prefsEditor = prefs.edit();
		prefsEditor.putString("Username", username);
		prefsEditor.putString("Password", password);
		prefsEditor.commit();
	}


	public static Typeface getStyleTangerine(Context context)
	{
		return Typeface.createFromAsset(context.getAssets(), "tangerinebold.ttf");
	}

	public static Typeface getStyleNyala(Context context)
	{
		return Typeface.createFromAsset(context.getAssets(), "nyala.ttf");
	}
}

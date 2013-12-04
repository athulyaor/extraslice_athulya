package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;
import java.util.Random;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;



public class ConfirmationScreen extends Activity {
	ActionBar mactionBar;
	TextView txtpaid, txtAmount, txtTranscation, actionbartitle;
	Utilities utilities;
	CartTab cart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		mactionBar = getActionBar();
		cart = new CartTab();
		mactionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_actionbar, null);
		mactionBar.setCustomView(customActionBarView,
				new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
						ActionBar.LayoutParams.WRAP_CONTENT));
		actionbartitle = (TextView) customActionBarView
				.findViewById(R.id.actionbartitle);
		actionbartitle.setTypeface(Utilities
				.getStyleTangerine(getApplicationContext()));
		mactionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		setContentView(R.layout.activity_paid);
		mactionBar.show();

		txtpaid = (TextView) findViewById(R.id.tvpaid);
		txtAmount = (TextView) findViewById(R.id.tvAmount);
		txtTranscation = (TextView) findViewById(R.id.tvTransaction);

		if (Utilities.total == 0.00) {
			txtpaid.setText("Your payment of $0.00 is completed using \'"
					+ Utilities.card + "\' card. Thank you for your purchase.");
			txtAmount.setText("$0.00");
		} else {

			txtpaid.setText("Your payment of $"
					+ new DecimalFormat("##.##").format(Utilities.total)
					+ " is completed using \'" + Utilities.card
					+ "\' card. Thank you for your purchase.");
			txtAmount.setText("$"
					+ String.valueOf(new DecimalFormat("##.##")
							.format(Utilities.total)));
		}
		txtTranscation.setText(String.valueOf(randomNumber()));
		Button back = (Button) findViewById(R.id.btnback);

		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.productslist1=CartTab.productslist;
				Utilities.total = 0.00;
				/*MANACYEDITED 21-11-2013
				 * 
				 * 
				 * Intent intent = new Intent(ConfirmationScreen.this,
						TabManager.class);
				intent.putExtra("TAB", "cart");
				startActivity(intent);*/
				//ConfirmationScreen.this.finish();
			}
		});
	}

	/** Generating randomNumber */
	public int randomNumber() {
		Random rand = new Random();
		int min = 10000000, max = 99999999;
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if(keyCode==KeyEvent.KEYCODE_BACK)
		{
			Utilities.total = 0.00;
			/*MANACY EDITED 21-11-2013
			 * 
			 * 
			 * Intent intent = new Intent(ConfirmationScreen.this,
					TabManager.class);
			intent.putExtra("TAB", "cart");
			startActivity(intent);*/
		}
		return true;
	}
}

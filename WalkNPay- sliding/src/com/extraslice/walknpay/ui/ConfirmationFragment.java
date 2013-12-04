package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

public class ConfirmationFragment extends Fragment {
	ActionBar mactionBar;
	TextView txtpaid, txtAmount, txtTranscation, actionbartitle;
	Utilities utilities;
	CartTab cart;
	public ConfirmationFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_paid, container, false);
        txtpaid = (TextView) rootView.findViewById(R.id.tvpaid);
		txtAmount = (TextView) rootView.findViewById(R.id.tvAmount);
		txtTranscation = (TextView) rootView.findViewById(R.id.tvTransaction);
		Button back = (Button) rootView.findViewById(R.id.btnback);

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

		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i("Confirmation fragment", "size"+CartFragment.productslist.size());
				MainActivity.productslist1=CartFragment.productslist;
				Log.i("Confirmation fragment", "size"+MainActivity.productslist1.size());
				Utilities.total = 0.00;
				
				
				Fragment fragment = null;
				fragment = new CartFragment();
				 if (fragment != null) {
						FragmentManager fragmentManager = getFragmentManager();
						fragmentManager.beginTransaction()
								.replace(R.id.frame_container, fragment).commit();

					}
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
	

	
	
	        return rootView;
    }
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
		
		Fragment fragment = null;
		fragment = new CartFragment();
		 if (fragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
	}
	}
	return true;
}
/** Generating randomNumber */
public int randomNumber() {
	Random rand = new Random();
	int min = 10000000, max = 99999999;
	int randomNum = rand.nextInt(max - min + 1) + min;
	return randomNum;
}
}
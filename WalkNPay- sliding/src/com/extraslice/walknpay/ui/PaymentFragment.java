package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class PaymentFragment extends Fragment implements OnClickListener{
	
	ActionBar mactionBar;
	Button pay, cancel, ok, dismiss;
	TextView tvAmount, actionbartitle;
	Intent intent;
	Dialog dialog;
	LayoutInflater inflater;
	View forgot_view;
	RadioGroup rdGroup;
	RadioButton rdbtn1, rdbtn2, rdbtn3;
	EditText pswrdcnfm;
	String Password;
	String text;
	Context context;

public PaymentFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_pay, container, false);
         
       
        dialog = new Dialog(getActivity(),
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		tvAmount = (TextView) rootView.findViewById(R.id.lbltvAmount);
		pay = (Button) rootView.findViewById(R.id.lblpay);
		cancel = (Button) rootView.findViewById(R.id.lblcancel);

		rdGroup = (RadioGroup) rootView.findViewById(R.id.radioGroup);
		rdbtn1 = (RadioButton) rootView.findViewById(R.id.radio0);
		rdbtn2 = (RadioButton) rootView.findViewById(R.id.radio1);
		rdbtn3 = (RadioButton) rootView.findViewById(R.id.radio2);
		context=this.getActivity();

		if (Utilities.total == 0.00) {
			tvAmount.setText("$0.00");
		} else {
			tvAmount.setText("$"
					+ String.valueOf(new DecimalFormat("##.##")
							.format(Utilities.total)));
		}
		pay.setOnClickListener(this);
		cancel.setOnClickListener(this);

		text = "My City Master (Card ending with 8890)";
		Utilities.card = text;

		rdGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio0:
					text = "My City Master (Card ending with 8890)";
					Utilities.card = text;
					break;
				case R.id.radio1:
					text = "Bofa Visa(Card ending with 347)";
					Utilities.card = text;
					break;
				case R.id.radio2:
					text = "Chase Debit (Card ending with 745)";
					Utilities.card = text;
					break;
				default:
					break;
				}
			}
		});
		 return rootView;
	}

	public void onBackPressed(){
	    FragmentManager fm = getFragmentManager();
	    if (fm.getBackStackEntryCount() > 0) {
	        Log.i("MainActivity", "popping backstack");
	        fm.popBackStack();
	    } else {
	        Log.i("MainActivity", "nothing on backstack, calling super");
	       
	    }
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lblpay:
			
			
			
			 final Dialog dialog = new Dialog(getActivity());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.password_dialog);
				
				TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
				//text.setText(R.string.loginalertmessage);
				pswrdcnfm=(EditText)dialog.findViewById(R.id.editText1);

				Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
				// if button is clicked, close the custom dialog
				dialogButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
						Password= pswrdcnfm.getText().toString();
						System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + Password);

						if(Password.equalsIgnoreCase(Utilities.getPassword(context)))
						{
							
							Fragment fragment = null;
							fragment = new ConfirmationFragment();
							 if (fragment != null) {
									FragmentManager fragmentManager = getFragmentManager();
									fragmentManager.beginTransaction()
											.replace(R.id.frame_container, fragment).commit();

								}

							
							dialog.dismiss();
						}
						else
						{
	
						        final Dialog dialog = new Dialog(getActivity());
								dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								dialog.setContentView(R.layout.alert);
								dialog.setTitle("Error");
								dialog.show();
							
					 
								// set the custom dialog components - text, image and button
								TextView text = (TextView) dialog.findViewById(R.id.content);
								text.setText("Enter Valid Password!");
								
								Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
								// if button is clicked, close the custom dialog
								dialogButton.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										
										dialog.dismiss();
									}
									});
								
								//image.setImageResource(R.drawable.ic_launcher);
					 
							

						}
						
					}
				});
				
				Button negativebutton = (Button) dialog.findViewById(R.id.alertnegativebutton);
				// if button is clicked, close the custom dialog
				negativebutton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						
						
						dialog.dismiss();
					}
				});

				dialog.show();
		        
			 
	 
			
			break;
		case R.id.lblcancel:
			      // finish();
			Fragment fragment = null;
			fragment = new CartFragment();
			 if (fragment != null) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.frame_container, fragment).commit();

				}
			break;
		default:
			break;
		}
	}
    

	

}

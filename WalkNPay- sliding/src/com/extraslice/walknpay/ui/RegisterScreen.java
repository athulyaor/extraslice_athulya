package com.extraslice.walknpay.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;


public class RegisterScreen extends Activity implements OnClickListener {
	Button submit;
	Intent intent;
	ActionBar mactionBar;
	TextView  actionbartitle;
	Dialog dialog;
	EditText email, password, paymentpassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		mactionBar = getActionBar();
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
		setContentView(R.layout.register);
		mactionBar.show();

		dialog = new Dialog(RegisterScreen.this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		submit = (Button) findViewById(R.id.submit);
		email = (EditText) findViewById(R.id.user_name);
		password = (EditText) findViewById(R.id.password);
		paymentpassword = (EditText) findViewById(R.id.payment_password);
		
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.submit:

			

			 final String UserName = email.getText().toString();
		     final String Password = password.getText().toString();
		     final String Paymentassword = paymentpassword.getText().toString();
		     
		     if (UserName.length() == 0 || Password.length() == 0 || Paymentassword.length() == 0){
						

				if (UserName.length() == 0 || 
						//Password.length() == 0
						 UserName.equalsIgnoreCase("")
						//|| Password.equalsIgnoreCase("")
						|| UserName.equalsIgnoreCase(null)
						//|| Password.equalsIgnoreCase(null)
						)
					{



					 dialog = new Dialog(this);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.alert);
						
			 
						// set the custom dialog components - text, image and button
						TextView text = (TextView) dialog.findViewById(R.id.content);
						text.setText("Enter Email!");
						
						
						//image.setImageResource(R.drawable.ic_launcher);
			 
						Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
						// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								
								dialog.dismiss();
							}
						});
					
			 
						dialog.show();
				
				}
				
				else if (//UserName.length() == 0 
						Password.length() == 0 ||
					//	|| UserName.equalsIgnoreCase("")
						 Password.equalsIgnoreCase("")
						//|| UserName.equalsIgnoreCase(null))
						|| Password.equalsIgnoreCase(null)
						)
					{



					 dialog = new Dialog(this);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.alert);
						
			 
						// set the custom dialog components - text, image and button
						TextView text = (TextView) dialog.findViewById(R.id.content);
						text.setText("Enter Password!");
						
						
						//image.setImageResource(R.drawable.ic_launcher);
			 
						Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
						// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								
								dialog.dismiss();
							}
						});
					
			 
						dialog.show();
				

					}
				
				else if (//UserName.length() == 0 
						Paymentassword.length() == 0 ||
					//	|| UserName.equalsIgnoreCase("")
						 Paymentassword.equalsIgnoreCase("")
						//|| UserName.equalsIgnoreCase(null))
						|| Paymentassword.equalsIgnoreCase(null)
						)
					{



					 dialog = new Dialog(this);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.alert);
						
			 
						// set the custom dialog components - text, image and button
						TextView text = (TextView) dialog.findViewById(R.id.content);
						text.setText("Enter Payment Password!");
						
						
						//image.setImageResource(R.drawable.ic_launcher);
			 
						Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
						// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								
								dialog.dismiss();
							}
						});
					
			 
						dialog.show();
				
					}
				
				
						
				}
		     
		     else {
		    	 

					Toast.makeText(getApplicationContext(),
							"This feature is not yet implemented", Toast.LENGTH_SHORT)
							.show();

					finish();
		     }
		     
				


			break;

		default:
			break;
		}
	}
}

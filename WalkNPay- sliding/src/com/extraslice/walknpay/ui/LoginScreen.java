package com.extraslice.walknpay.ui;

import java.io.IOException;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.adapter.DataBaseHelper;
import com.extraslice.walknpay.bl.Utilities;



public class LoginScreen extends Activity implements OnClickListener {

	Button login, register, ok_but;
	EditText username, password;
	String user1 = "user";
	Intent intent;
	Dialog dialog;
	LayoutInflater inflater;
	View forgot_view;
	ActionBar mactionBar;

	DataBaseHelper myDbHelper;
	TextView actionbartitle,associate,forgot_password;

	@SuppressWarnings("deprecation")
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
		setContentView(R.layout.activity_login);
		mactionBar.show();

		dialog = new Dialog(LoginScreen.this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);

		username = (EditText) findViewById(R.id.email_edit);
		password = (EditText) findViewById(R.id.pass_edit);
		login = (Button) findViewById(R.id.login_button);
		register = (Button) findViewById(R.id.register_button);
		forgot_password = (TextView) findViewById(R.id.forgot_button);
		//associate = (TextView) findViewById(R.id.textView2);
		
		forgot_password.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

		// username.setText("john");
		// password.setText("john");

		login.setOnClickListener(this);
		register.setOnClickListener(this);
		forgot_password.setOnClickListener(this);

		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.copyDataBase();
			myDbHelper.openDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myDbHelper.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.login_button:

		 final String UserName = username.getText().toString();
	     final String Password = password.getText().toString();
	     
	     if (UserName.length() == 0 || Password.length() == 0){
					

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
	     }
			
			
			  else {
				
				Utilities.islogged = true;
				Utilities.setCredentials(UserName, Password, LoginScreen.this);
				intent = new Intent(LoginScreen.this, MenuActivity.class);
				startActivity(intent);
				finish();
				}
			
			//username.setText("");
			//password.setText("");
			
			
			
		    	break;

		case R.id.register_button:

			intent = new Intent(LoginScreen.this, RegisterScreen.class);
			startActivity(intent);

			break;

		case R.id.forgot_button:


			intent = new Intent(LoginScreen.this, Forgot.class);
			startActivity(intent);
			
			break;
		default:
			break;
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {/*
			// android.os.Process.killProcess(android.os.Process.myPid());
			AlertDialog alert;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setTitle("Confirm");
			builder.setMessage("Do you want to exit?");

			builder.setPositiveButton("Yes",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// Do nothing but close the dialog
							System.exit(0);
						}

					});

			builder.setNegativeButton("No",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// Do nothing
							dialog.dismiss();
						}
					});

			alert = builder.create();
			alert.show();
			return true;
		*/
	 dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_alert);
			
 
			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
			text.setText(R.string.loginalertmessage);
			
			
			//image.setImageResource(R.drawable.ic_launcher);
 
			Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					System.exit(0);
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
			
		}
		return super.onKeyDown(keyCode, event);
	}

}

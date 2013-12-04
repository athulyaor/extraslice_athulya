package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Forgot extends Activity  implements OnClickListener {

	EditText email;
	Button submit;
	Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot);
		
		email=(EditText)findViewById(R.id.email_edit);
		submit= (Button)findViewById(R.id.button1);
		
		submit.setOnClickListener(this);
		
			
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.button1:

		 final String Email = email.getText().toString();
	    
	     
	   if (Email.length() == 0 || 
					//Password.length() == 0
					 Email.equalsIgnoreCase("")
					//|| Password.equalsIgnoreCase("")
					|| Email.equalsIgnoreCase(null)
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
			
		
	     
			
			
			  else {
				
				
					Toast.makeText(getApplicationContext(), "Mail Sent", Toast.LENGTH_LONG).show();
					
					finish();
				
				}
			
	
			
			
		    	break;

	
			
			
		default:
			break;
		}
	
		
	}
	}
	



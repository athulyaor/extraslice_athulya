package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AssociateFragment extends Fragment {
	
	EditText associateid, retailid, storeid;
	Button login;
	Dialog dialog;
	
	public AssociateFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.associate, container, false);
       
       login=(Button)rootView.findViewById(R.id.login);
       associateid=(EditText)rootView.findViewById(R.id.associate_id);
		retailid=(EditText)rootView.findViewById(R.id.retail_id);
	   storeid=(EditText)rootView.findViewById(R.id.store_id);
	      
        login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				

				 final String Associate = associateid.getText().toString();
			     final String Retail = retailid.getText().toString();
			     final String Store = storeid.getText().toString();
			     
			     if (Associate.length() == 0 || Retail.length() == 0 || Store.length() == 0){
							

					if (Associate.length() == 0 || 
							//Password.length() == 0
							Associate.equalsIgnoreCase("")
							//|| Password.equalsIgnoreCase("")
							|| Associate.equalsIgnoreCase(null)
							//|| Password.equalsIgnoreCase(null)
							)
						{



						 dialog = new Dialog(getActivity());
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.alert);
							
				 
							// set the custom dialog components - text, image and button
							TextView text = (TextView) dialog.findViewById(R.id.content);
							text.setText("Enter Associate Id!");
							
							
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
							Retail.length() == 0 ||
						//	|| UserName.equalsIgnoreCase("")
									Retail.equalsIgnoreCase("")
							//|| UserName.equalsIgnoreCase(null))
							|| Retail.equalsIgnoreCase(null)
							)
						{



						 dialog = new Dialog(getActivity());
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.alert);
							
				 
							// set the custom dialog components - text, image and button
							TextView text = (TextView) dialog.findViewById(R.id.content);
							text.setText("Enter Retail Id!");
							
							
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
							Store.length() == 0 ||
						//	|| UserName.equalsIgnoreCase("")
									Store.equalsIgnoreCase("")
							//|| UserName.equalsIgnoreCase(null))
							|| Store.equalsIgnoreCase(null)
							)
						{



						 dialog = new Dialog(getActivity());
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.alert);
							
				 
							// set the custom dialog components - text, image and button
							TextView text = (TextView) dialog.findViewById(R.id.content);
							text.setText("Enter Store Id!");
							
							
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
			    	 

						Toast.makeText(getActivity(),
								"This feature is not yet implemented", Toast.LENGTH_SHORT)
								.show();

						
			     }
			     
					
				
			}
		});
	  
         
        return rootView;
    }
}

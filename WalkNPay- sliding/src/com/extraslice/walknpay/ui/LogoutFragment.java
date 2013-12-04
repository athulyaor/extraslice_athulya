package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LogoutFragment extends Fragment {
	
	public LogoutFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.logout, container, false);
        
        final Dialog dialog = new Dialog(getActivity());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.custom_alert);
		
		TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
		text.setText(R.string.logoutalertmessage);
		
		Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				 Intent n1=new Intent(getActivity(), LoginScreen.class);
					startActivity(n1);
					Utilities.rewards=0.00;
					Utilities.firstrun=false;
					Utilities.confirm_store=false;
					getActivity().finish();
				
				
			}
		});
		Button negativebutton = (Button) dialog.findViewById(R.id.alertnegativebutton);
		// if button is clicked, close the custom dialog
		negativebutton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent n1=new Intent(getActivity(), MenuActivity.class);
				startActivity(n1);
				dialog.dismiss();
			}
		});

		dialog.show();
        
       
        return rootView;
    }
}

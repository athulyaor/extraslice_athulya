package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;


import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RewardsFragment extends Fragment {
	
    ActionBar mactionBar;
	TextView tvSavingAmount, tvlifetimeAmount;
	
	public RewardsFragment(){
		
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
		View rootView = inflater.inflate(R.layout.rewards_tab, null);
        
        tvlifetimeAmount = (TextView)rootView.findViewById(R.id.lbltvlifetimeamt);
		tvSavingAmount = (TextView) rootView.findViewById(R.id.lbltvsavingsamt);

		tvSavingAmount.setText(""+Utilities.rewards);
		tvlifetimeAmount.setText(""+Utilities.rewards+125.30);
		
		if (Utilities.total == 0.00) {
			
			
		       tvSavingAmount.setText("$"
						+ String.valueOf(new DecimalFormat("##.##")
								.format(Utilities.rewards)));
				
			
				tvlifetimeAmount.setText("$"
						+ String.valueOf(new DecimalFormat("##.##")
								.format(Utilities.rewards +125.30)));
			}
			else {
				
				tvSavingAmount.setText("$0.00");
				tvlifetimeAmount.setText("$125.30");
			
				
			}
		
        return rootView;
        
        
    }
	
	
	
}

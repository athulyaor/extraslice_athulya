package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReceiptFragment extends Fragment {
	
	TextView store;
	
	public ReceiptFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.myreceipts, container, false);
        
    	store=(TextView)rootView.findViewById(R.id.shopname);
		store.setText(Utilities.store);
         
        return rootView;
    }
}

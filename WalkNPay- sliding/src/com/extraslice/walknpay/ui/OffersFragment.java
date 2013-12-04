package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OffersFragment extends Fragment {
	
	public OffersFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.offers, container, false);
         
        return rootView;
    }
}

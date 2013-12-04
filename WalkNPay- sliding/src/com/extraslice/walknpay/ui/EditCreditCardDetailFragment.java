package com.extraslice.walknpay.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

public class EditCreditCardDetailFragment extends Fragment implements OnClickListener,OnItemSelectedListener{
	
	FragmentManager manager;
	EditText  edit_accntholder;
	EditText edit_name;
	TextView edit_number,edit_date;
	Spinner edit_type;
	Button save_button,cancel_button;
	String num_pos;
	
	
	public EditCreditCardDetailFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.editcreditcarddetail, container, false);
        
    	edit_name = (EditText)rootView.findViewById(R.id.cardname);
		edit_number= (TextView)rootView. findViewById(R.id.cardnumber);
		edit_type = (Spinner) rootView.findViewById(R.id.cardtype);
		edit_accntholder = (EditText) rootView.findViewById(R.id.cardholder);
		save_button = (Button) rootView.findViewById(R.id.savebutton);
		cancel_button = (Button) rootView.findViewById(R.id.cancel);
		edit_date = (TextView) rootView.findViewById(R.id.expiry);
		edit_name.setText(MainActivity.cardname);
		edit_number.setText(MainActivity.cardnum);
		edit_accntholder.setText(Utilities.getUsername(getActivity()));
		edit_date.setText(MainActivity.cardyear);
		  Utilities.store = edit_accntholder.getText().toString();
		edit_date.setOnClickListener(this);

		List<String> list = new ArrayList<String>();
		list.add("VISA");
		list.add("Master");
		list.add("Amex");
		list.add("Discover");
		list.add("Diners");
		
		String [] values = 
	        {"VISA","Master","Amex","Discover","Diners"};
	
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
	
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		edit_type.setAdapter(dataAdapter);
       // Intent ni = getIntent();
         //ni.getStringExtra("position");
		edit_type.setOnItemSelectedListener(this);
		
         save_button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((edit_name.getText().toString().equals(""))&&(edit_number.getText().toString().equals(""))&&(edit_accntholder.getText().toString().equals("")))
				{
					Toast.makeText(getActivity(), "Please fill the fields above", Toast.LENGTH_SHORT).show();
					return;
				}
				else {
					String cardname = edit_name.getText().toString();
					String cardnumber = edit_number.getText().toString();
					String cardholder = edit_accntholder.getText().toString();
					/*String cardtype = edit_type.getText().toString();*/
					String editdate= edit_date.getText().toString();
					Intent in = new Intent();
					in.putExtra("editname",cardname);
					in.putExtra("cardnumber", cardnumber);
					in.putExtra("cardholder", cardholder);
					in.putExtra("position", num_pos);
				/*	in.putExtra("cardtype", MainActivity.cardtype);*/
					in.putExtra("date", editdate);
					
					onActivityResult(1, 0, in);
					
					// getActivity().finish(); 
					// finish();
					 Log.i("Card holder name", ""+cardholder);
					 
					 Utilities.card_holder=cardholder;
					 Utilities.expiry_date=editdate;
					
					 Fragment fragment = null;
						fragment = new SaveFragment();
						 if (fragment != null) {
								FragmentManager fragmentManager = getFragmentManager();
								fragmentManager.beginTransaction()
										.replace(R.id.frame_container, fragment).commit();

							}
				}
				}
			});

		
				 
		
         cancel_button.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		fragment = new CreditCardDetailFragment();
		 if (fragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();

			}
	}
});
		
         
        return rootView;
    }


	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
		MainActivity.cardtype= arg0.getItemAtPosition(arg2).toString();
		((TextView) arg0.getChildAt(0)).setTextColor(Color.BLACK);
		((TextView) arg0.getChildAt(0)).setGravity(Gravity.RIGHT);
		((TextView) arg0.getChildAt(0)).setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
		
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.expiry:
			Calendar calender = Calendar.getInstance();
	        Dialog mDialog = new DatePickerDialog(getActivity(),
	                mDatesetListener, calender.get(Calendar.YEAR),
	                calender.get(Calendar.MONTH), calender
	                        .get(Calendar.DAY_OF_MONTH));

	        mDialog.show();  
			break;

		default:
			break;
		}
		
	}


private DatePickerDialog.OnDateSetListener mDatesetListener = new OnDateSetListener() {

    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
        arg2 = arg2 + 1;
        String my_date = arg1 + "-" + arg2 + "-" + arg3;
        
        edit_date.setText(my_date);            
    }
};
}

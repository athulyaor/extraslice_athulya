package com.extraslice.walknpay.ui;

import java.util.Calendar;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class CreditCardDetailFragment extends Fragment implements OnClickListener{
	
	TextView cardname,cardtype,cardholder,cardnumber,expirydate;
	Button edit_button, save_button;
String Username;
Object num;
Context context;
	String card_holder;
	public CreditCardDetailFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.creditcarddetails, container, false);
        
        Username = Utilities.getUsername(getActivity());
		cardname= (TextView)rootView.findViewById(R.id.cardname);
		cardtype= (TextView)rootView.findViewById(R.id.cardtype);
		cardholder= (TextView)rootView.findViewById(R.id.cardholder);
		cardnumber= (TextView)rootView.findViewById(R.id.cardnumber);
		edit_button= (Button)rootView.findViewById(R.id.editbutton);
		save_button = (Button)rootView.findViewById(R.id.savebutton);
		expirydate= (TextView)rootView.findViewById(R.id.expirydate);
		edit_button.setOnClickListener(this);
		save_button.setOnClickListener(this);
		
		
		
		cardholder.setText(Username);
		cardtype.setText("VISA");
		//Intent in =  this.getIntent();
		// Bundle b = in.getExtras();
		
		// b.get("position");
		//num =  b.get("position");
		Log.i("POSITION", ""+num);
		
		//if(num.toString().contentEquals("1"))
		//{
			//Log.i("dgjh", "hjvchjdsc");
		//}
		 String cardnum = MainActivity.cardnum;
	      String str="";
	      int y =cardnum.length();
	      Log.i("STRING LENGTH", ""+y);
	     for(int i =0;i<y-3;i++)
	     {
	    	 if(i<y-3)
	    	 {
	    		 str = str+"*";
	    		 Log.i("In loop STring", ""+str+"i"+i);
	    	 }
	     }
	     Log.i("string after loop", ""+str);
	     str= str+ cardnum.substring(y-3,y);
	     Log.i("STRING AFTER APPENDING", ""+str);
	     
			cardname.setText(MainActivity.cardname);
			cardtype.setText("VISA");
			cardholder.setText(Username);
			//cardholder.setText(Utilities.card_holder);
			cardnumber.setText(str);
			MainActivity.cardnum= str;
			expirydate.setText(MainActivity.cardyear);
		
		
        return rootView;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.editbutton: 
			
			Fragment fragment = null;
			fragment = new EditCreditCardDetailFragment();
			
			 if (fragment != null) {
					FragmentManager fragmentManager = getFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.frame_container, fragment).commit();

				}
			break;
		case R.id.expirydate : 
			Calendar calender = Calendar.getInstance();
			
			
            //Dialog mDialog = new DatePickerDialog(CreditCardDetail.this,
                    //mDatesetListener, calender.get(Calendar.YEAR),
                    //calender.get(Calendar.MONTH), calender
                          //  .get(Calendar.DAY_OF_MONTH));

          //  mDialog.show();   
			           break;
		default:
			break;
		}
		
	}


@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if (requestCode == 1) {

	    if(resultCode == 1){

	      String editname=data.getStringExtra("editname");
	      String editnumber = data.getStringExtra("cardnumber");
	      String editholder = data.getStringExtra("cardholder");
	      String editdate= data.getStringExtra("date");
	      String pos= data.getStringExtra("position");
	      Log.i("POSITION ONACTIVITY RESULT", ""+pos);
	      if(num.toString().contentEquals("0"))
			{
				Log.i("dgjh", "hjvchjdsc");
			
	    	  MainActivity.card1= editname;
	      }
	      else if(num.toString().contentEquals("1"))
	      {
	    	  MainActivity.card2= editname;
	      }
	      else
	      {
	    	  MainActivity.card3= editname;
	      }
	      MainActivity.cardname = editname;
	      MainActivity.cardnum = editnumber;
	      MainActivity.cardholder= editholder;
	      MainActivity.cardyear=editdate;
	      cardname.setText(MainActivity.cardname);
	      cardtype.setText(MainActivity.cardtype);
	      cardholder.setText(MainActivity.cardholder);
	      cardnumber.setText(MainActivity.cardnum);
	      expirydate.setText(MainActivity.cardyear);
	      
	      Intent io = new Intent();
	      io.putExtra("posi", num.toString());

	}

	if (resultCode == 0) {

	     //Write your code on no result return 
		 /* cardname.setText(MainActivity.cardname);
	      cardtype.setText(MainActivity.cardtype);
	      cardholder.setText(MainActivity.cardholder);
	      cardnumber.setText(MainActivity.cardnum);*/
	      
	}
	}
}


private OnDateSetListener mDatesetListener = new OnDateSetListener() {

    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
        arg2 = arg2 + 1;

        String my_date = arg1 + "-" + arg2 + "-" + arg3;
        expirydate.setText(my_date);            
    }
};

}


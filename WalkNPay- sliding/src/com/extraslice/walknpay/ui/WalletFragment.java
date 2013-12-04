package com.extraslice.walknpay.ui;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.extraslice.walknpay.R;
import com.extraslice.walknpay.adapter.CardsAdapter1;
import com.extraslice.walknpay.bl.Utilities;
import com.extraslice.walknpay.model.Products;

public class WalletFragment extends Fragment implements OnClickListener, OnItemClickListener{
	
	 String CardsList[], RecentBills[];
		ListView cardslist, recentbills;
		ArrayList<String> Recentarray;
		ArrayList<String> CardsList1;
		Button ok,dismiss;
		View forgot_view;
		Intent intent;
		CartTab cart;
		Dialog dialog;
		String sub2 ;
		LayoutInflater inflater;
		Context context;
		ImageView creditcardsplus, searchimage, walletminus;
		boolean isClicked=false;
		
		String Password;
		LinearLayout llwallet;
		EditText pswrdcnfm;
		CardsAdapter1 cartlistadapter1;
		
	
	public WalletFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_wallet, container, false);
        
        llwallet = (LinearLayout)rootView.findViewById(R.id.lblllwallet);
        //llwallet.setVisibility(View.VISIBLE);
       
        //context = this;
       context=getActivity();
		cart= new CartTab();
		showPasswordDialog();
		
		
		cardslist = (ListView)rootView.findViewById(R.id.creditcardslist);
		recentbills = (ListView)rootView.findViewById(R.id.recentbillslist);
		CardsList = getResources().getStringArray(R.array.cardslist);
		Recentarray= new ArrayList<String>();
		CardsList1 = new ArrayList<String>();
		
		
		Log.i("Wallet", "size"+MainActivity.productslist1.size());
		if(MainActivity.productslist1.size()>0)
		{
			
		Products s= MainActivity.productslist1.get(0);
		String sub1= s.getProductdesc().toString();
		if(sub1.length()>14)
		{
			sub2= sub1.substring(0,11)+"...";
		}
		String s1 = "Today(ABC ..."+sub2; 
		String s2 ="Yesterday (blender...)";
		RecentBills = getResources().getStringArray(R.array.recentbills);
		Recentarray.add(s1);
		Recentarray.add(s2);
		Log.i("the PRODUTS DESCRIPTION", ""+Recentarray);
		
		

		}
		else
		{
			RecentBills = getResources().getStringArray(R.array.recentbills);
			String s1 = "Today (Huggies...)"; 
			String s2 ="Yesterday (blender...)";
			Recentarray.add(s1);
			Recentarray.add(s2);
			
		}
		
		CardsList1.add(MainActivity.card1);
		CardsList1.add(MainActivity.card2);
		CardsList1.add(MainActivity.card3);
		
		
		cartlistadapter1= new CardsAdapter1(getActivity(), CardsList1);
		recentbills.setAdapter(new BillsAdapter(context, Recentarray));
		cardslist.setAdapter(cartlistadapter1);
		creditcardsplus = (ImageView)rootView.findViewById(R.id.walletplus);
		searchimage = (ImageView)rootView.findViewById(R.id.searchimage);
		walletminus = (ImageView)rootView.findViewById(R.id.walletminus);

		creditcardsplus.setOnClickListener(this);
		searchimage.setOnClickListener(this);
		walletminus.setOnClickListener(this);
		
      
		cardslist.setOnItemClickListener(this);
		recentbills.setOnItemClickListener(this);
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		
		

        
        return rootView;
    }
/*
	private void showPasswordDialog() {
		// TODO Auto-generated method stub
		

		
		if(!(Utilities.islogged))
		{
            firstrun = true;
			dialog = new Dialog(WalletTab.this,
					android.R.style.Theme_Translucent_NoTitleBar);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			Window window = dialog.getWindow();
			
			window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			forgot_view = inflater.inflate(R.layout.passwordconfirmationdialog, (ViewGroup)forgot_view.findViewById(R.id.password_confirmation_dialog_layout));
			dialog.setContentView(forgot_view);
			pswrdcnfm=(EditText)dialog.findViewById(R.id.passwordconfmedittext);

			ok = (Button) dialog.findViewById(R.id.ok_button);
			dismiss = (Button) dialog.findViewById(R.id.can_button);

			ok.setOnClickListener(new OnClickListener() 
			{

				public void onClick(View v)
				{
					Password= pswrdcnfm.getText().toString();
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + Password);

					if(Password.equalsIgnoreCase(Utilities.getPassword(context)))
					{
						dialog.dismiss();
						Utilities.islogged = true;
						llwallet.setVisibility(View.VISIBLE);
					}
					else
					{
/*
						AlertDialog.Builder alert = new AlertDialog.Builder(WalletTab.this);
						alert.setTitle("ERROR");
						alert.setMessage("Enter valid password!");
						alert.create();
						alert.setNegativeButton("Ok", null);
						alert.show();

					}
				}
			});
			dismiss.setOnClickListener(new OnClickListener() 
			{
				public void onClick(View v) 
				{
					dialog.dismiss();
				}
			});


			dialog.show();

		}
		else
		{
			llwallet.setVisibility(View.VISIBLE);

		}
	  
		
	}
	*/

	private void showPasswordDialog() {
		// TODO Auto-generated method stub
		
		Log.i("value of islogged", ""+Utilities.islogged);
		Log.i("firstrun", ""+Utilities.firstrun);
		if((Utilities.islogged)&&(Utilities.firstrun==false))
		{
		
	
		
		 final Dialog dialog = new Dialog(getActivity());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.password_dialog);
			
			TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
			//text.setText(R.string.loginalertmessage);
			pswrdcnfm=(EditText)dialog.findViewById(R.id.editText1);

			Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					
					Password= pswrdcnfm.getText().toString();
					System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + Password);

					if(Password.equalsIgnoreCase(Utilities.getPassword(context)))
					{
						dialog.dismiss();
						Utilities.pass_wallet = true;
						Utilities.firstrun = true;
						llwallet.setVisibility(View.VISIBLE);
					}
					else
					{
/*
						AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
						alert.setTitle("ERROR");
						alert.setMessage("Enter valid password!");
						alert.create();
						alert.setNegativeButton("Ok", null);
						alert.show();
				*/
						
						 final Dialog dialog = new Dialog(getActivity());
							dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
							dialog.setContentView(R.layout.alert);
							dialog.setTitle("Error");
							dialog.show();
						
				 
							// set the custom dialog components - text, image and button
							TextView text = (TextView) dialog.findViewById(R.id.content);
							text.setText("Enter Email!");
							
							Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
							// if button is clicked, close the custom dialog
							dialogButton.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									
									dialog.dismiss();
								}
								});
							
							//image.setImageResource(R.drawable.ic_launcher);
				 
						

					}
					
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
		 
		
		}
		else
		{
			llwallet.setVisibility(View.VISIBLE);

		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.walletplus:
			Toast.makeText(getActivity(),
					"This feature is not yet implemented", Toast.LENGTH_SHORT)
					.show();

			break;
		case R.id.searchimage:
			        Toast.makeText(getActivity(),
					"This feature is not yet implemented", Toast.LENGTH_SHORT)
					.show();
			break;
		case R.id.walletminus:
			                Toast.makeText(getActivity(),
					         "This feature is not yet implemented", Toast.LENGTH_SHORT)
					         .show();
			break;
		default:
			break;
		}
	
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Fragment fragment = null;

		// TODO Auto-generated method stub
		Log.i("id of the clicked list", ""+arg1.getId());
		Log.i("id of the clicked list ARG0", ""+arg0);
		 if( arg0 == cardslist )
		 {
		if(arg2==0)
			MainActivity.cardname=MainActivity.card1;
		
		if(arg2==1)
			MainActivity.cardname=MainActivity.card2;
		if(arg2==2)
			MainActivity.cardname=MainActivity.card3;
		int g = arg2;
		Log.i("the details", ""+cardslist.getItemAtPosition(arg2).toString());
		
	//Fragment fragment = null;
		//Intent n1 = new Intent(WalletTab.this, CreditCardDetail.class);
		//n1.putExtra("position", g);
		//startActivityForResult(n1, 1);

		fragment = new CreditCardDetailFragment();
		 }
		 
			
		 if(arg0==recentbills)
		 {
		//	 Intent n2 = new Intent(WalletTab.this,ReceiptClass.class );
		//	 startActivity(n2);
			 
			 fragment = new ReceiptFragment();
			 
		 }

		 if (fragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();

			} else {
				
				Log.e("MainActivity", "Error in creating fragment");
			}
		
	}
		 @Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
				// TODO Auto-generated method stub
				
				Log.i("fusdgnv", "dffsdf");
				Log.i("Listview", ""+cardslist.getItemAtPosition(1));
				
				
				ArrayList<String> newlist = new ArrayList<String>();
				
				
				
				CardsList1.set(1, "AYYYOO");
				Log.i("ARRAy LSI", ""+CardsList1.get(1));
				CardsList1.set(0, MainActivity.card1);
				CardsList1.set(1, MainActivity.card2);
				CardsList1.set(2, MainActivity.card3);
				cardslist.setAdapter(new CardsAdapter1(context, CardsList1));

				cartlistadapter1.notifyDataSetChanged();
				super.onActivityResult(requestCode, resultCode, data);
			}
		 
		 @Override
		public void onResume() {
		 	// TODO Auto-generated method stub
		 
		 	cartlistadapter1.notifyDataSetChanged();
		 	super.onResume();
		 }
		 }

	class BillsAdapter extends BaseAdapter {
		Context context;
		ArrayList<String> RecentBills;

		public BillsAdapter(Context context, ArrayList<String> recentBills) {
			// TODO Auto-generated constructor stub
			this.context = context;
			this.RecentBills = recentBills;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return RecentBills.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View v, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (v == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
				v = inflater.inflate(R.layout.custom_list, null);
			}
			TextView tv = (TextView) v.findViewById(R.id.walletlisttext);
			tv.setText(RecentBills.get(position));
			ImageView img = (ImageView) v.findViewById(R.id.walletlistarrowimage);
			img.setImageResource(R.drawable.arrow);

			return v;
		}
	}


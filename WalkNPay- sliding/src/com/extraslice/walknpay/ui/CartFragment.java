package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.adapter.CartListAdapter;
import com.extraslice.walknpay.adapter.DataBaseHelper;
import com.extraslice.walknpay.model.Products;
import com.extraslice.walknpay.bl.Utilities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CartFragment extends Fragment implements OnClickListener {
	ActionBar mactionBar;
	public static ArrayList<Products> productslist;
	Fragment fragment = null;
	Products products;
	public static Button imgScanner,imgscanner0;
	static TextView tvPDescription, tvPCount, tvPPrice;
	static ImageView imgplus, imgminus, imgclear;
	String Password;
	int flag=0;
	EditText pswrdcnfm;
	
	public static Context context;
	//public static String contents="073287611054";
	String scanContent, scanFormat;
	public static Button checkout_button, ok, dismiss,confirm_tick;
	Intent intent;
	View forgot_view;
	Dialog dialog;
	LayoutInflater inflater;
	public static int count=0;
	public static ListView cartlist;
	public static double Sum = 0.00, Rewards = 0.00, Tax = 0.00, Total = 0.00;
	public static CartListAdapter cartlistadapter;
	public static LinearLayout linearcartlabels, linear_noproducts, linearcartlabels1,linear_bottomtab,linear_floating;
	public static TextView tvrewards_amt, tvtax_amt, tvtotal_amt,tax_label,taxamt_label,text_associate,textblink,confirm_text;
	String tax_amt="$00.00",rewards_amt="$00.00",total_amt="$00.00";
	 static MediaPlayer mp;
	int visibility_value,visibility_value1;
	DataBaseHelper myDbHelper;
	 public static View toastView;
	public static ImageView imageView,image;;
	public static TextView textView ;
    public static RelativeLayout rlstore;
	public static Activity a;
	public static Animation hyperspaceJump;
	public CartFragment(){}
	View rootView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.cart_tab4, container, false);
        
        
        image = (ImageView) rootView.findViewById(R.id.associate);
   	   hyperspaceJump = AnimationUtils.loadAnimation(getActivity(), R.anim.cycle);
   	   imgScanner = (Button) rootView.findViewById(R.id.scanner_button);
	   imgscanner0=(Button)rootView.findViewById(R.id.imageButton1);
	   checkout_button = (Button) rootView.findViewById(R.id.checkoutbutton);
	   checkout_button.setOnClickListener(this);
	   rlstore= (RelativeLayout) rootView.findViewById(R.id.store);
   	   context = this.getActivity();
   	   
   	   
   		dialog = new Dialog(getActivity(),
   				android.R.style.Theme_Translucent_NoTitleBar);
   		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
   		Window window = dialog.getWindow();
   		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
   		
   		
   		text_associate=(TextView) rootView.findViewById(R.id.associatetext);
   		cartlist = (ListView) rootView.findViewById(R.id.lbllist);
   		linearcartlabels = (LinearLayout) rootView.findViewById(R.id.cartlabels);
   		linearcartlabels1 = (LinearLayout) rootView.findViewById(R.id.cartlabels1);
   		linear_bottomtab=(LinearLayout)rootView.findViewById(R.id.linear56);
   		tvrewards_amt = (TextView) rootView.findViewById(R.id.rewards_amt);
   		tvtax_amt = (TextView) rootView.findViewById(R.id.tax_amt);
   		tvtotal_amt = (TextView) rootView.findViewById(R.id.total_amt);
           tax_label=(TextView) rootView.findViewById(R.id.taxlabel);
           linear_floating=(LinearLayout) rootView.findViewById(R.id.floatinglayout);
           taxamt_label=(TextView)rootView.findViewById(R.id.textlab2);
           textblink= (TextView) rootView.findViewById(R.id.textView1);
           confirm_tick = (Button) rootView.findViewById(R.id.imageView2);
          // confirm_text = (TextView) findViewById(R.id.textViewconfirm);
   		productslist = new ArrayList<Products>();
   	//	MainActivity.productslist1 = new ArrayList<Products>();
   		Log.i("PRODUCT LIST", ""+productslist.size());
   		tvtax_amt.setText(tax_amt);
   		tvrewards_amt.setText(rewards_amt);
   		tvtotal_amt.setText(total_amt);
   		
   		imgScanner.setOnClickListener(this);
   		imgscanner0.setOnClickListener(this);
   		confirm_tick.setOnClickListener(this);
   	
   		
   	
   		
 
    
    Log.i("CARTFRAGMENT", "oncreate"+Utilities.confirm_store);

   if(Utilities.confirm_store==true)
   {
	   Log.i("CART confirm store TRUE!!!!!!!!!", ""+ Utilities.confirm_store);
   	imgscanner0.setVisibility(View.VISIBLE);
   	//confirm_text.setVisibility(View.GONE);
   	rlstore.setVisibility(View.INVISIBLE);
   	//textblink.setVisibility(View.INVISIBLE);
   	
   }
   else
   {
	   Log.i("CART confirm store ELSE!!!!!!!!!", ""+Utilities.confirm_store);
	   rlstore.setVisibility(View.VISIBLE);
  

   }

   if(Utilities.confirm_store==true&&productslist.size()>0)
   {
   	imgscanner0.setVisibility(View.INVISIBLE);
   	
   }
   a= (Activity) getActivity();
   LayoutInflater inflater2 = (LayoutInflater) a.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

   
   toastView = inflater2.inflate(R.layout.toast,
		   (ViewGroup)getActivity().findViewById(R.id.toastLayout));
   textView = (TextView)toastView.findViewById(R.id.text);
   imageView = (ImageView)toastView.findViewById(R.id.image);
	cartlistadapter = new CartListAdapter(a, R.layout.row_cart,
			productslist,count) ;
	/*if (productslist.size() > 0) {
		linearcartlabels.setVisibility(View.VISIBLE);
		imgscanner0.setVisibility(View.GONE);
		linear_floating.setVisibility(View.VISIBLE);
		linear_bottomtab.setVisibility(View.VISIBLE);
		rlstore.setVisibility(View.INVISIBLE);
		image.startAnimation(hyperspaceJump);
		text_associate.startAnimation(hyperspaceJump);
		((LinearLayout) linearcartlabels1).setVisibility(View.GONE);
		checkout_button.setVisibility(View.VISIBLE);
		for (int i = 0; i < productslist.size(); i++) {
			Products product = productslist.get(i);
			//String prdct_item= productslist1.get(i);
			Sum = (float) (Sum + (product.getProductprice() * productslist
					.get(i).getProductcount()));

			System.out.println("in cart tab when "
					+ Sum
					+ (product.getProductprice() * productslist.get(i)
							.getProductcount()));

		}

		Rewards = (Sum * 0.05f);
		tvrewards_amt
		.setText("$"
				+ String.valueOf(new DecimalFormat("00.00")
				.format(Rewards)));
		Tax = (Sum * 0.1f);
		tvtax_amt.setText("$"
				+ String.valueOf(new DecimalFormat("00.00").format(Tax)));
		Total = ((Sum + Tax) - Rewards);
		
		Log.i("TOTAL AMOUNT", ""+Total);
		Utilities.total = Total;
		Utilities.rewards = Rewards;
		
		String v= String.valueOf(new DecimalFormat("##.##")
		.format(Utilities.total));
		if(v.length()<5)
		{
			String v1= v.substring(0,v.indexOf("."));
			String v2= v.substring((v.indexOf(".")+1),v.length());
			if(v1.length()<2)
			{
				v1=0+v1;
			}
			if(v2.length()<2)
			{
				v2=v2+0;
			}
			v=v1+"."+v2;
		}
		tvtotal_amt.setText("$"
				+v);

		((LinearLayout) linearcartlabels).setVisibility(View.VISIBLE);
	}*/
        return rootView;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		productslist = new ArrayList<Products>();
	
		
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.scanner_button:

			/*intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);*/
			IntentIntegrator.initiateScan(getActivity(), R.layout.capture,
                    R.id.viewfinder_view, R.id.preview_view, true);
			
		/*	IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();*/

			break;
		case R.id.imageButton1:
			IntentIntegrator.initiateScan(getActivity(), R.layout.capture,
                    R.id.viewfinder_view, R.id.preview_view, true);
                   break;
		case R.id.checkoutbutton:

			 if (!(Utilities.islogged)) {
					
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
								Utilities.islogged = true;
								MainActivity.productslist1=productslist;
								
								Log.i("Logged in", "OffersFRAGMENT0");
								 fragment = new PaymentFragment();
								dialog.dismiss();
								Log.i("Logged in", "OUTFRAGMENT0");
								if (fragment != null) {
									FragmentManager fragmentManager = getFragmentManager();
									fragmentManager.beginTransaction()
											.replace(R.id.frame_container, fragment).commit();

							}
							}
							else
							{
		
							        final Dialog dialog = new Dialog(getActivity());
									dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
									dialog.setContentView(R.layout.alert);
									dialog.setTitle("Error");
									dialog.show();
								
						 
									// set the custom dialog components - text, image and button
									TextView text = (TextView) dialog.findViewById(R.id.content);
									text.setText("Enter Valid Password!");
									
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
							
							
							dialog.dismiss();
						}
					});

					dialog.show();
			        
				
		 } 
		
		else {
			
			/*intent = new Intent(CartTab.this, PaymentScreen.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);*/
			Log.i("Logged in", "OffersFRAGMENT1");
			 fragment = new PaymentFragment();
			dialog.dismiss();
			if (fragment != null) {
				FragmentManager fragmentManager = getFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();

				// update selected item and title, then close the drawer
				
			} else {
				// error in creating fragment
				Log.e("MainActivity", "Error in creating fragment");
			}
			Log.i("Logged in", "OffersOUT1");
		}
			break;
		case R.id.imageView2:
			//textblink = (TextView) findViewById(R.id.textView1 );
				rlstore.setVisibility(View.INVISIBLE);
				//confirm_text.setVisibility(View.INVISIBLE);
				textblink.setVisibility(View.INVISIBLE);
				
				stop();
			
				Utilities.confirm_store = true;
				imgscanner0.setVisibility(View.VISIBLE);
				Toast.makeText(getActivity(),
					"Confirmed", Toast.LENGTH_SHORT)
					.show();
				
			    break;
		default:
			break;
		}
	}
	
	
	private void stop() {
		// TODO Auto-generated method stub
		

		/*confirm_tick=(ImageView)findViewById(R.id.imageView2);
		confirm_text = (TextView) findViewById(R.id.textView3);*/
    	Animation anim = new AlphaAnimation(0.0f, 0.0f);
    	anim.setDuration(0); //You can manage the time of the blink with this parameter
    	anim.setStartOffset(0);
    	anim.setRepeatMode(Animation.REVERSE);
    	//confirm_tick.startAnimation(anim);
       // confirm_text.startAnimation(anim);
		
		
	}

	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		//Log.i("resultcode,requestcode",""+requestCode+"result"+resultCode);
		/*if (requestCode == 0) {
			if (resultCode == RESULT_OK) {*/
		if(resultCode== -1)
		{
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		String contents = intent.getStringExtra("SCAN_RESULT");
		//Log.i("CONTENTS VALUE", ""+contents);
		if (scanningResult != null) {
			//we have a result
			scanContent = scanningResult.getContents();
			 scanFormat = scanningResult.getFormatName();
			//Log.i("UPSC CODE", ""+scanContent);
			//Log.i("FORMAT NAME", ""+scanFormat);
			
		}

		else{
			Toast toast = Toast.makeText(getActivity(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
		myDbHelper = new DataBaseHelper(getActivity());
		myDbHelper.openDataBase();
		// Log.i("CURSOR", ""+contents);
		Cursor cursor = myDbHelper.getProductDetails(contents);
       
		products = new Products();
		String productDescription;
		String productPrice1;
		String product_code;
		int product_count;
		double productPrice;
		if (cursor != null && cursor.getCount() > 0) {
			if (cursor.moveToFirst()) {
				do {
			 productDescription = cursor.getString(cursor
						.getColumnIndex("Product_Description"));
			 productPrice = cursor.getDouble(cursor
						.getColumnIndex("Product_Price"));
				 productPrice1 = cursor.getString(cursor
						.getColumnIndex("Product_Price"));
				 product_code = cursor.getString(cursor.getColumnIndex("Product_Code"));
				 product_count=products.getProductcount();
					Log.i("Upc code", ""+product_code);
					Log.i("PRODUCT COUNT", ""+product_count);
				products.setProductdesc(productDescription);
				products.setProductprice(productPrice);
				products.setProductcode(product_code);
				products.setProductcount(product_count);
				
				} while (cursor.moveToNext());
                
				productslist.add(products);
				/*productslist1.add(productDescription);
				productslist1.add(productPrice1);*/
				//Log.i("ARRAY LIST SIZE", ""+productslist.size());
				if(productslist.size()>1)
				{
					int i;
			    for( i=0;i<productslist.size()-1;i++)
			    {
				//Log.i("I VALUE IS", ""+i);
				if(productslist.get(i).getProductcode().contains(product_code))
				{
					flag=1;
					//Log.i("IN iF BLOCK", "");
					productslist.remove(productslist.size()-1);
					productslist.get(i).setProductcount(productslist.get(i).getProductcount()+1);
					
					productslist.add(productslist.get(i));
					 
					productslist.remove(i);
					
					Log.i("When item repeated", ""+productslist.get(i).getProductcount());
					count=1;
					
					  //contents="038000844966";
					textView.setText(" "+products.getProductdesc()+" is already added to the list.");
					imageView.setImageResource(R.drawable.associate40);
					Toast toast = new Toast(a);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					toast.show();
					//Toast.makeText(a, ""+products.getProductdesc()+" is already added to the list", Toast.LENGTH_LONG).show();
				}
				}
				if(flag!=1)
				{
					//Log.i("IN ELSE BLOCK", "");
					textView.setText(" "+products.getProductdesc()+products.getProductdesc()+" is added to the list.");
					imageView.setImageResource(R.drawable.associate40);
					Toast toast = new Toast(a);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					//toast.show();
					//Toast.makeText(a, ""+products.getProductdesc()+" added .Enquiries ??? Click help button", Toast.LENGTH_LONG).show();
					/*products.setProductcode(product_code);
					products.setProductdesc(productDescription);
					products.setProductprice(productPrice);
					productslist.add(products);*/
				}
				flag=0;	
				}
				else
				{
					textView.setText(" "+products.getProductdesc()+" is added to the list. For help click this button");
					imageView.setImageResource(R.drawable.associate40);
					
					Toast toast = new Toast(a);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					toast.show();
					//Toast.makeText(a, " "+products.getProductdesc()+" added .Enquiries ??? Click help button", Toast.LENGTH_LONG).show();

				}
				// Play sound
				try {
					playSound(getActivity());
				} catch (Exception e) {

				}
			}
			cursor.close();
		} else {
			// Product not found
			try {
				
				Log.i("CART FRAGMENT", "I am in else UNKNOWN PRODUCT");
                 playSoundbeep(getActivity());
                 
                 final Dialog dialog = new Dialog(a);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.alert);
					dialog.setTitle("Unknown product");
					dialog.show();
				
		 
					// set the custom dialog components - text, image and button
					TextView text = (TextView) dialog.findViewById(R.id.content);
					TextView head = (TextView) dialog.findViewById(R.id.alertTitle);
					text.setText("The scanned item is not in the library!");
					head.setText("Unknown product");
					
					Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
					// if button is clicked, close the custom dialog
					dialogButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							
							dialog.dismiss();
						}
						});
              
                 

				
			} catch (Exception e) {

			}
		}

		myDbHelper.close();
		//Log.i("AFTER DB CLOSE", ""+productslist1.size());
		if (productslist.size() > 0) {
			linearcartlabels.setVisibility(View.VISIBLE);
		}
		/*productslist = localList;
		cartlistadapter.notifyDataSetChanged();
		
		calculate();*/
		  Activity myActivity = getActivity();
		 if (a==null) 
		 {
			 Log.i("ACTIVITY VALUE", ""+myActivity);
			 return;
			 
		 }// Fragment not active anymore, bail out
		
		 else
		cartlistadapter = new CartListAdapter(a, R.layout.row_cart,
				productslist,count) {
			@Override
			public void refresh() {

				productslist = localList;
				Log.i("the list in adpater", ""+productslist);
			cartlistadapter.notifyDataSetChanged();
				
			calculate();
			}
		};
		
		
		cartlist.setAdapter(cartlistadapter);
		
		scrollMyListViewToBottom();
		count=0;
		}
		
	}
public static void playSoundbeep(Context context) {
	// TODO Auto-generated method stub
	
	Log.i("PLAYSOUNDBEEP beep beep", "CART!!!!!!!!!!!!!!!!!");
	mp = MediaPlayer.create(a, R.raw.beepbeep);
	mp.setOnCompletionListener(new OnCompletionListener() {

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			mp.release();
		}

	});
	mp.start();
	
}


public static void playSound(Context context) {
	
	Log.i("PLAYSOUND", "CART!!!!!!!!!!!!");
	mp = MediaPlayer.create(a, R.raw.beep);
	mp.setOnCompletionListener(new OnCompletionListener() {

		@Override
		public void onCompletion(MediaPlayer mp) {
			// TODO Auto-generated method stub
			Log.i("Playsound oncompletion", "");
			mp.release();
		}

	});
	Log.i("Playsound hhhhhh", "");
	mp.start();
	Log.i("Playsound iiiiiiii", "");
}
private void scrollMyListViewToBottom() {
	cartlist.post(new Runnable() {
        @Override
        public void run() {
            // Select the last row so it will scroll into view...
        	cartlist.setSelection(cartlistadapter.getCount() - 1);
        }
    });
}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	if(Utilities.confirm_store==true)
	{
		imgscanner0.setVisibility(View.VISIBLE);
		//confirm_text.setVisibility(View.GONE);
		rlstore.setVisibility(View.INVISIBLE);
		textblink.setVisibility(View.INVISIBLE);
		stop();
		
	}
	
	if(Utilities.confirm_store==true&&productslist.size()>0)
	{
		imgscanner0.setVisibility(View.INVISIBLE);
		rlstore.setVisibility(View.INVISIBLE);
	}
	

	

}
public void calculate() {
	Sum = 00.00;

	if (productslist.size() == 0 || productslist == null) {

		tvrewards_amt.setText("$00.00");
		tvtax_amt.setText("$00.00");
		tvtotal_amt.setText("$00.00");
		Utilities.total = 00.00;
		((LinearLayout) linearcartlabels).setVisibility(View.GONE);
		((LinearLayout) linearcartlabels1).setVisibility(View.VISIBLE);
		checkout_button.setVisibility(View.GONE);
		linear_bottomtab.setVisibility(View.GONE);
		linear_floating.setVisibility(View.GONE);
		imgscanner0.setVisibility(View.VISIBLE);

	} else {
		Log.i("VIew", ""+getView());
		Log.i("Activity", ""+getActivity());
		//imgScanner = (Button) getView().findViewById(R.id.scanner_button);
		//imgscanner0=(Button)rootView.findViewById(R.id.imageButton1);
		imgscanner0.setVisibility(View.GONE);
		linear_floating.setVisibility(View.VISIBLE);
		linear_bottomtab.setVisibility(View.VISIBLE);
		confirm_tick.setVisibility(View.INVISIBLE);
		image.startAnimation(hyperspaceJump);
		text_associate.startAnimation(hyperspaceJump);
		/* RelativeLayout.LayoutParams params
	     = new RelativeLayout.LayoutParams(
	       LayoutParams.WRAP_CONTENT,
	       LayoutParams.WRAP_CONTENT);
	    params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	    linear_bottomtab.setLayoutParams(params);*/
		((LinearLayout) linearcartlabels1).setVisibility(View.GONE);
		checkout_button.setVisibility(View.VISIBLE);
		//Log.i("VISIBILITY CHECKOUT", ""+checkout_button.getVisibility());
		for (int i = 0; i < productslist.size(); i++) {
			Products product = productslist.get(i);
			//String prdct_item= productslist1.get(i);
			Sum = (float) (Sum + (product.getProductprice() * productslist
					.get(i).getProductcount()));

			System.out.println("in cart tab when "
					+ Sum
					+ (product.getProductprice() * productslist.get(i)
							.getProductcount()));

		}

		Rewards = (Sum * 0.05f);
		tvrewards_amt
		.setText("$"
				+ String.valueOf(new DecimalFormat("00.00")
				.format(Rewards)));
		Tax = (Sum * 0.1f);
		tvtax_amt.setText("$"
				+ String.valueOf(new DecimalFormat("00.00").format(Tax)));
		Total = ((Sum + Tax) - Rewards);
		
		Log.i("TOTAL AMOUNT", ""+Total);
		Utilities.total = Total;
		Utilities.rewards = Rewards;
		
		String v= String.valueOf(new DecimalFormat("##.##")
		.format(Utilities.total));
		if(v.length()<5)
		{
			String v1= v.substring(0,v.indexOf("."));
			String v2= v.substring((v.indexOf(".")+1),v.length());
			if(v1.length()<2)
			{
				v1=0+v1;
			}
			if(v2.length()<2)
			{
				v2=v2+0;
			}
			v=v1+"."+v2;
		}
		tvtotal_amt.setText("$"
				+v);

	((LinearLayout) linearcartlabels).setVisibility(View.VISIBLE);
	}
}

@Override
public void onSaveInstanceState(final Bundle outState) {         
   super.onSaveInstanceState(outState);
}
}

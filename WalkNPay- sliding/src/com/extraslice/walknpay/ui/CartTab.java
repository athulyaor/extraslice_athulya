package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import jim.h.common.android.zxinglib.integrator.IntentIntegrator;
import jim.h.common.android.zxinglib.integrator.IntentResult;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.adapter.CartListAdapter;
import com.extraslice.walknpay.adapter.DataBaseHelper;
import com.extraslice.walknpay.model.Products;
import com.extraslice.walknpay.bl.Utilities;


public class CartTab extends Activity implements OnClickListener {

	ActionBar mactionBar;
	public static ArrayList<Products> productslist;
	Fragment fragment = null;
	Products products;
	Button imgScanner,imgscanner0;
	TextView tvPDescription, tvPCount, tvPPrice;
	ImageView imgplus, imgminus, imgclear;
	String Password;
	int flag=0;
	EditText pswrdcnfm;
	
	Context context;
	//public static String contents="073287611054";
	String scanContent, scanFormat;
	Button checkout_button, ok, dismiss;
	Intent intent;
	View forgot_view;
	Dialog dialog;
	LayoutInflater inflater;
	public static int count=0;
	ListView cartlist;
	double Sum = 0.00, Rewards = 0.00, Tax = 0.00, Total = 0.00;
	CartListAdapter cartlistadapter;
	LinearLayout linearcartlabels, linear_noproducts, linearcartlabels1,linear_bottomtab,linear_floating;
	TextView tvrewards_amt, tvtax_amt, tvtotal_amt,tax_label,taxamt_label,text_associate,textblink,confirm_text;
	String tax_amt="$00.00",rewards_amt="$00.00",total_amt="$00.00";

	
	static MediaPlayer mp;
	int visibility_value,visibility_value1;
	DataBaseHelper myDbHelper;
	View toastView;
	ImageView imageView,confirm_tick;
	TextView textView ;
	ImageView image;
	Animation hyperspaceJump;
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cart_tab4);
        Log.i("ONCREATE", ""+savedInstanceState);
		imgScanner = (Button) findViewById(R.id.scanner_button);
		imgscanner0=(Button)findViewById(R.id.imageButton1);
		checkout_button = (Button) findViewById(R.id.checkoutbutton);
		checkout_button.setOnClickListener(this);
		//checkout_button.setVisibility(View.VISIBLE);
		 image = (ImageView) findViewById(R.id.associate);
	 hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.cycle);

		
		context = this;
		dialog = new Dialog(CartTab.this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		text_associate=(TextView) findViewById(R.id.associatetext);
		cartlist = (ListView) findViewById(R.id.lbllist);
		linearcartlabels = (LinearLayout) findViewById(R.id.cartlabels);
		linearcartlabels1 = (LinearLayout) findViewById(R.id.cartlabels1);
		linear_bottomtab=(LinearLayout)findViewById(R.id.linear56);
		tvrewards_amt = (TextView) findViewById(R.id.rewards_amt);
		tvtax_amt = (TextView) findViewById(R.id.tax_amt);
		tvtotal_amt = (TextView) findViewById(R.id.total_amt);
        tax_label=(TextView) findViewById(R.id.taxlabel);
        linear_floating=(LinearLayout) findViewById(R.id.floatinglayout);
        taxamt_label=(TextView)findViewById(R.id.textlab2);
        textblink= (TextView) findViewById(R.id.textView1);
        confirm_tick = (ImageView) findViewById(R.id.imageView2);
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

toastView = getLayoutInflater().inflate(R.layout.toast,
(ViewGroup)findViewById(R.id.toastLayout));
textView = (TextView)toastView.findViewById(R.id.text);
 imageView = (ImageView)toastView.findViewById(R.id.image);
Log.i("productlist 1111", "SIZE"+MainActivity.productslist1.size());
 // imageView.setImageResource(R.drawable.associate40);
//    imageView.setBackgroundDrawable(bitmapDrawable);
if(Utilities.confirm_store==true)
{
	imgscanner0.setVisibility(View.VISIBLE);
	//confirm_text.setVisibility(View.GONE);
	confirm_tick.setVisibility(View.INVISIBLE);
	textblink.setVisibility(View.INVISIBLE);
	
}
else
{
	blinkText2();
	//Toast.makeText(getApplicationContext(), "Please confirm the store name in Home tab ", Toast.LENGTH_SHORT).show();
	//return;

}

if(Utilities.confirm_store==true&&productslist.size()>0)
{
	imgscanner0.setVisibility(View.INVISIBLE);
}


//confirm_text.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(Utilities.confirm_store==true)
		{
			imgscanner0.setVisibility(View.VISIBLE);
			//confirm_text.setVisibility(View.GONE);
			confirm_tick.setVisibility(View.INVISIBLE);
			textblink.setVisibility(View.INVISIBLE);
			stop();
			
		}
		
		if(Utilities.confirm_store==true&&productslist.size()>0)
		{
			imgscanner0.setVisibility(View.INVISIBLE);
			confirm_tick.setVisibility(View.INVISIBLE);
		}
		
			
	}
@Override
protected void onRestoreInstanceState(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onRestoreInstanceState(savedInstanceState);
	Log.i("RESTORE INSTANCE", ""+productslist.size());
	/*rewards_amt= savedInstanceState.getString("rewards");
	tax_amt=savedInstanceState.getString("tax amount");
	total_amt=savedInstanceState.getString("total amount");
	visibility_value=savedInstanceState.getInt("linear visibility");
	visibility_value1=savedInstanceState.getInt("linear visibility1");
	productslist1=savedInstanceState.getStringArrayList("ARRAYLIST");
	
	cartlist.setAdapter(cartlistadapter);
	Log.i("T value", ""+tax_amt);
	tvtax_amt.setText(tax_amt);
	tvrewards_amt.setText(rewards_amt);
	tvtotal_amt.setText(total_amt);
	linearcartlabels.setVisibility(visibility_value);
	linearcartlabels1.setVisibility(View.GONE);*/
	//setContentView(R.layout.cart_tab);
	count=savedInstanceState.getInt("rewards");
}

private void blinkText2() {
	// TODO Auto-generated method stub
		
		
		      /* confirm_tick=(ImageView)findViewById(R.id.imageView2);
		       confirm_text = (TextView) findViewById(R.id.textView3);*/
		    	Animation anim = new AlphaAnimation(0.0f, 1.0f);
		    	anim.setDuration(200); //You can manage the time of the blink with this parameter
		    	anim.setStartOffset(50);
		    	anim.setRepeatMode(Animation.REVERSE);
		    	anim.setRepeatCount(Animation.INFINITE);
		    	confirm_tick.startAnimation(anim);
		    	//confirm_text.startAnimation(anim);
		    }
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	
		outState.putInt("rewards", count);
	/*outState.putString("rewards", tvrewards_amt.getText().toString());
	outState.putString("tax amount", tvtax_amt.getText().toString());
	outState.putString("total amount", tvtotal_amt.getText().toString());
	outState.putInt("linear visibility",linearcartlabels.getVisibility());
	outState.putInt("linear visibility1", linearcartlabels1.getVisibility());
	outState.putStringArrayList("ARRAYLIST", productslist1);*/
	//outState.putStringArrayList(key, value)
	//outState.putStringArrayList("arraylist", productslist);
		//calculate();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}
	/** calculating Rewards,Tax,Total */
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
			Log.i("VISIBILITY CHECKOUT", ""+checkout_button.getVisibility());
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.scanner_button:

			/*intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);*/
			IntentIntegrator.initiateScan(CartTab.this, R.layout.capture,
                    R.id.viewfinder_view, R.id.preview_view, true);
		
		/*	IntentIntegrator scanIntegrator = new IntentIntegrator(this);
			scanIntegrator.initiateScan();*/

			break;
		case R.id.imageButton1:
			IntentIntegrator.initiateScan(CartTab.this, R.layout.capture,
                    R.id.viewfinder_view, R.id.preview_view, true);
                   break;
		case R.id.checkoutbutton:

			if (!(Utilities.islogged)) {

				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				forgot_view = inflater
						.inflate(
								R.layout.passwordconfirmationdialog,
								(ViewGroup) findViewById(R.id.password_confirmation_dialog_layout));
				dialog.setContentView(forgot_view);
				pswrdcnfm = (EditText) dialog
						.findViewById(R.id.passwordconfmedittext);

				ok = (Button) dialog.findViewById(R.id.ok_button);
				dismiss = (Button) dialog.findViewById(R.id.can_button);
				
				ok.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						Password = pswrdcnfm.getText().toString();

						if (Password.equalsIgnoreCase(Utilities
								.getPassword(context))) {
							dialog.dismiss();
							Utilities.islogged = true;
							MainActivity.productslist1=productslist;
							Log.i("productlist1", "111 size"+MainActivity.productslist1.size());
							/*intent = new Intent(CartTab.this,
									PaymentScreen.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(intent);*/
							Log.i("Logged in", "OffersFRAGMENT0");
							 fragment = new PaymentFragment();
							dialog.dismiss();
							Log.i("Logged in", "OUTFRAGMENT0");
						} else {

							AlertDialog.Builder alert = new AlertDialog.Builder(
									CartTab.this);
							alert.setTitle("ERROR");
							alert.setMessage("Enter valid password!");
							alert.create();
							alert.setNegativeButton("Ok", null);
							alert.show();

						}

						// finish();
					}
				});
				dismiss.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				dialog.show();

			} else {
				
				/*intent = new Intent(CartTab.this, PaymentScreen.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);*/
				Log.i("Logged in", "OffersFRAGMENT1");
				 fragment = new PaymentFragment();
				dialog.dismiss();
				Log.i("Logged in", "OffersOUT1");
			}

			break;
		case R.id.imageView2:
			//textblink = (TextView) findViewById(R.id.textView1 );
				confirm_tick.setVisibility(View.INVISIBLE);
				//confirm_text.setVisibility(View.INVISIBLE);
				textblink.setVisibility(View.INVISIBLE);
				
				stop();
			
				Utilities.confirm_store = true;
				imgscanner0.setVisibility(View.VISIBLE);
				Toast.makeText(getApplicationContext(),
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
    	confirm_tick.startAnimation(anim);
       // confirm_text.startAnimation(anim);
		
		
	}
@Override
protected void onPostResume() {
	// TODO Auto-generated method stub
	super.onPostResume();
}
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
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
			Toast toast = Toast.makeText(getApplicationContext(),
					"No scan data received!", Toast.LENGTH_SHORT);
			toast.show();
		}
		myDbHelper = new DataBaseHelper(this);
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
					//imageView.setImageResource(R.drawable.associate40);
					Toast toast = new Toast(context);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					toast.show();
					//Toast.makeText(getApplicationContext(), ""+products.getProductdesc()+" is already added to the list", Toast.LENGTH_LONG).show();
				}
				}
				if(flag!=1)
				{
					//Log.i("IN ELSE BLOCK", "");
					textView.setText(" "+products.getProductdesc()+products.getProductdesc()+" is added to the list.");
					//imageView.setImageResource(R.drawable.associate40);
					Toast toast = new Toast(context);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					toast.show();
					//Toast.makeText(getApplicationContext(), ""+products.getProductdesc()+" added .Enquiries ??? Click help button", Toast.LENGTH_LONG).show();
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
					//imageView.setImageResource(R.drawable.associate40);
					Toast toast = new Toast(context);

					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.setDuration(Toast.LENGTH_LONG);
					toast.setView(toastView);

					toast.show();
					//Toast.makeText(getApplicationContext(), " "+products.getProductdesc()+" added .Enquiries ??? Click help button", Toast.LENGTH_LONG).show();

				}
				// Play sound
				try {
					playSound(this);
				} catch (Exception e) {

				}
			}
			cursor.close();
		} else {
			// Product not found
			try {
				AlertDialog.Builder alert = new AlertDialog.Builder(
						CartTab.this);
				alert.setTitle("UNKNOWN PRODUCT");
				alert.setMessage(" The scanned item is not in the library!");
				alert.create();
				alert.setNegativeButton("OK", null);
				alert.show();
				
			
				

				playSoundbeep(this);
				
			} catch (Exception e) {

			}
		}

		myDbHelper.close();
		//Log.i("AFTER DB CLOSE", ""+productslist1.size());
		if (productslist.size() > 0) {
			linearcartlabels.setVisibility(View.VISIBLE);
		}

		cartlistadapter = new CartListAdapter(this, R.layout.row_cart,
				productslist,count) {
			@Override
			public void refresh() {

				productslist = localList;
				cartlistadapter.notifyDataSetChanged();
				
				calculate();
			}
		};
		cartlist.setAdapter(cartlistadapter);
		scrollMyListViewToBottom();
		count=0;
		}
		
	}
	public void refreshOther() {

		
		cartlistadapter.notifyDataSetChanged();
		
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	     CartTab.this.finish();   
	   
	    }
	    return super.onKeyDown(keyCode, event);
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
	private void playSoundbeep(Context context) {
		// TODO Auto-generated method stub
		
		mp = MediaPlayer.create(context, R.raw.beepbeep);
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
		mp = MediaPlayer.create(context, R.raw.beep);
		mp.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.release();
			}

		});
		mp.start();
	}

	public void deleteItem(final int itemno) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Confirm");
		builder.setMessage("Do you want to Delete?");

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog

				if (productslist.size() > 0) {

					productslist.remove(itemno);

				}
			}

		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
				dialog.dismiss();
			}
		});

		builder.create();
		builder.show();

	}
}
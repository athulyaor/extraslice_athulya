package com.extraslice.walknpay.ui;

import java.util.zip.Inflater;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.sax.RootElement;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;

public class HomeFragment extends Fragment implements OnClickListener {
	Button bYes,bNo;
	ImageButton bcross;
	RelativeLayout relative1;
	LinearLayout myGallery;
	HorizontalScrollView hz,hz1;
	TextView tv,tv_thanks;
	public static TextView tv_storename;
	TextView tv_restmsg;
	ImageView img_associate,img_arrow,store_name;
	static EditText change_store;
	Dialog dialog;
	static Context context;
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.hometab1, container, false);
         bYes= (Button)rootView.findViewById(R.id.buttonYes);
         bNo= (Button)rootView.findViewById(R.id.buttonNo);
         bcross= (ImageButton)rootView.findViewById(R.id.cross);
         tv_thanks = (TextView)rootView.findViewById(R.id.othertext);
         tv_storename = (TextView)rootView.findViewById(R.id.storename);
         tv_restmsg= (TextView)rootView.findViewById(R.id.restmsg);
         img_associate= (ImageView)rootView.findViewById(R.id.imageassociate);
         img_arrow= (ImageView)rootView.findViewById(R.id.imagearrow);
        myGallery =(LinearLayout) rootView.findViewById(R.id.linear1);
        store_name=(ImageView)rootView.findViewById(R.id.imageView1);
        context = this.getActivity();
         
        relative1 =(RelativeLayout)rootView.findViewById(R.id.storelayout);
       tv_storename.setText(Utilities.store);
        bYes.setOnClickListener(this);
        bNo.setOnClickListener(this);
        bcross.setOnClickListener(this);
        img_associate.setOnClickListener(this);
        store_name.setOnClickListener(this);
        Log.i("HOMEFRAGMENT", "oncreate"+Utilities.confirm_store);
        if(Utilities.confirm_store==true)
        {
        	relative1.setVisibility(View.GONE);
        	myGallery.setVisibility(View.VISIBLE);
			tv_restmsg.setVisibility(View.VISIBLE);
			img_arrow.setVisibility(View.VISIBLE);
			img_associate.setVisibility(View.VISIBLE);
			 blinkText2();
        }
        else
        {
        	relative1.setVisibility(View.VISIBLE);
        	myGallery.setVisibility(View.GONE);
			tv_restmsg.setVisibility(View.GONE);
			img_arrow.setVisibility(View.GONE);
			img_associate.setVisibility(View.GONE);
        }
        return rootView;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonYes:
			Utilities.confirm_store=true;
			relative1.setVisibility(View.GONE);  
			myGallery.setVisibility(View.VISIBLE);
			tv_restmsg.setVisibility(View.VISIBLE);
			img_arrow.setVisibility(View.VISIBLE);
			img_associate.setVisibility(View.VISIBLE);
			 blinkText2();
			//hz.setVisibility(View.VISIBLE);
			//hz1.setVisibility(View.VISIBLE);
			
			break;
		case R.id.cross : 
			
			              tv_storename.setText("");
			break;

		case R.id.imageassociate : 
			
			Toast.makeText(getActivity(),
					"This feature is not yet implemented", Toast.LENGTH_SHORT)
					.show();
       case R.id.imageView1 : 
			

   		 final Dialog dialog = new Dialog(getActivity());
   			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
   			dialog.setContentView(R.layout.changestore);
   			
   			TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
   			//text.setText(R.string.loginalertmessage);
   			change_store=(EditText)dialog.findViewById(R.id.editText1);
   			

   			Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
   			// if button is clicked, close the custom dialog
   			dialogButton.setOnClickListener(new OnClickListener() {
   				@Override
   				public void onClick(View v) {
   					
   					 final String Storename = change_store.getText().toString();  
   					   if (Storename.length() == 0 || 
   									//Password.length() == 0
   							   Storename.equalsIgnoreCase("")
   									//|| Password.equalsIgnoreCase("")
   									|| Storename.equalsIgnoreCase(null)
   									//|| Password.equalsIgnoreCase(null)
   									)
   								{


   							// dialog = new Dialog();
   								//dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
   								dialog.setContentView(R.layout.store);
   								
   					 
   								// set the custom dialog components - text, image and button
   								TextView text = (TextView) dialog.findViewById(R.id.content);
   								//text.setText("Enter Email!");
   								
   								
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
   					   else {
   					
   				
   					Utilities.store = change_store.getText().toString();
                     tv_storename.setText(Utilities.store);
                     dialog.dismiss();	
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
   	        
   		
   		
   	
          break;

			
		default:
			break;
		}
	}
	private void blinkText2() {
		// TODO Auto-generated method stub
			
			
			       
			      //  invisible = (TextView) findViewById(R.id.textView3);
			    	Animation anim = new AlphaAnimation(0.0f, 1.0f);
			    	anim.setDuration(200); //You can manage the time of the blink with this parameter
			    	anim.setStartOffset(50);
			    	anim.setRepeatMode(Animation.REVERSE);
			    	anim.setRepeatCount(Animation.INFINITE);
			    	img_arrow.startAnimation(anim);
			    	//invisible.startAnimation(anim);
			    }
	

	
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("ONRESUME", "HOMEFRAGMENT"+Utilities.confirm_store);
		  if(Utilities.confirm_store==true)
	        {
	        	relative1.setVisibility(View.GONE);
	        	myGallery.setVisibility(View.VISIBLE);
				tv_restmsg.setVisibility(View.VISIBLE);
				img_arrow.setVisibility(View.VISIBLE);
				img_associate.setVisibility(View.VISIBLE);
				 blinkText2();
	        }
	        else
	        {
	        	relative1.setVisibility(View.VISIBLE);
	        	myGallery.setVisibility(View.GONE);
				tv_restmsg.setVisibility(View.GONE);
				img_arrow.setVisibility(View.GONE);
				img_associate.setVisibility(View.GONE);
	        }
	}

	public static void showHelp() {}
	

}



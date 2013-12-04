package com.extraslice.walknpay.ui;



import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.adapter.NavDrawerListAdapter;
import com.extraslice.walknpay.bl.Utilities;
import com.extraslice.walknpay.model.NavDrawerItem;

public class MenuActivity extends Activity implements OnClickListener{
	public DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	public ActionBarDrawerToggle mDrawerToggle;
	//ActionBar mactionBar;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	PopupWindow popUp;
	FrameLayout layout;
	LayoutParams params;
	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	Dialog dialog;
	EditText pswrdcnfm, storename;
	String Password,actionbartitle;
	TextView myText;
	ActionBar mactionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		

		/*requestWindowFeature(Window.FEATURE_ACTION_BAR);
		requestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
		requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		mactionBar = getActionBar();
		mactionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_actionbar, null);
		mactionBar.setCustomView(customActionBarView,
				new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
						ActionBar.LayoutParams.WRAP_CONTENT));

		actionbartitle = (TextView) customActionBarView
				.findViewById(R.id.actionbartitle);
		actionbartitle.setTypeface(Utilities
				.getStyleTangerine(getApplicationContext()));

		mactionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
	
		mactionBar.show();*/
		
	/*	getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_CUSTOM);

		getActionBar().setTitle(R.id.mytextactionbar);*/
		
	   /* s.setSpan(new TypefaceSpan("tangerinebold.ttf"), 0, s.length(),
	            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
		/*Log.i("Result", ""+Utilities
				.getStyleTangerine(getApplicationContext()));
	    s.setSpan(Utilities
				.getStyleTangerine(getApplicationContext()), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	 
	    // Update the action bar title with the TypefaceSpan instance
	    ActionBar actionBar = getActionBar();
	    actionBar.setTitle(s);*/
		setContentView(R.layout.activity_main);
		
		LayoutInflater inflater = (LayoutInflater) MenuActivity.this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.popup_window,
				(ViewGroup) findViewById(R.id.popup_element));
				popUp = new PopupWindow(layout, 300, 370, true);

				
		/*popUp = new PopupWindow(this);
		
		 popUp.showAtLocation(layout, Gravity.TOP | Gravity.RIGHT,
                 0, 0);
         popUp.update(30, 75, 500, 400);
        
         params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT);

         popUp.setBackgroundDrawable(null);
         // layout.setBackgroundColor(Color.TRANSPARENT);
         popUp.setContentView(R.layout.popup_window);*/

/*
		*/

		// load slide menu items
				
				 
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		mTitle = mDrawerTitle = getTitle();
		
		   	// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
		// What's hot, We  will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
		
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
		
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons.getResourceId(7, -1)));
		
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons.getResourceId(8, -1)));
		
		

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// enabling action bar app icon and behaving it as toggle button
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		/*mactionBar.setDisplayHomeAsUpEnabled(true);
		mactionBar.setHomeButtonEnabled(true);*/

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, // nav drawer open - description for accessibility
				R.string.app_name // nav drawer close - description for accessibility
		) {
			public void onDrawerClosed(View view) {
				/*getActionBar().setTitle("WalkNPay");*/
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				/*getActionBar().setTitle("WalkNPay");*/
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}
	

	}
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	
}
	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
			
		 case R.id.help_btn:
	            showHelp();
	            return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void showHelp() {
		// TODO Auto-generated method stub
		

		
		
		
		 final Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.changestore);
			
			TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
			//text.setText(R.string.loginalertmessage);
			pswrdcnfm=(EditText)dialog.findViewById(R.id.editText1);
			

			Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					 final String Storename = pswrdcnfm.getText().toString();  
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
					
					myText = (TextView) findViewById(R.id.storename );
					Utilities.store = pswrdcnfm.getText().toString();
                  myText.setText(Utilities.store);
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
	        
		
		
	}
	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new CartFragment();
			break;
		case 2:
			fragment = new WalletFragment();
			break;
		case 3:
			fragment = new RewardsFragment();
			break;
		case 4:
			fragment = new OffersFragment();
			break;
		case 5:
			fragment = new AssociateFragment();
			break;
		case 6:
			fragment = new AboutFragment();
			break;
		case 7:
			fragment = new HelpFragment();
			break;
		case 8:
			fragment = new LogoutFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle("WalkNPay");
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Fragment fragment = new CartFragment();
		Log.i("activity result in menu", "ghgcg");
	    fragment.onActivityResult(requestCode, resultCode, data);
	    Log.i("activity result in menu", "ghgcg");
		

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		popUp.showAtLocation(layout, Gravity.CENTER, 0, 0);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		
	 dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.custom_alert);
			
 
			// set the custom dialog components - text, image and button
			TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
			text.setText(R.string.loginalertmessage);
			
			
			//image.setImageResource(R.drawable.ic_launcher);
 
			Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
			// if button is clicked, close the custom dialog
			dialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					System.exit(0);
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
		return super.onKeyDown(keyCode, event);
	}

}




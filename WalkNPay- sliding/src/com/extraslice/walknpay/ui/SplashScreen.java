package com.extraslice.walknpay.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;


import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;



public class SplashScreen extends Activity {
	private Thread SplashThread;
	Boolean back = false;
	String username;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_splash);

		SplashThread = new Thread() {
			@Override
			public void run() {
				try {
					synchronized (this) {
						wait(3000);
					}
				} catch (InterruptedException ex) {

				}

				if (back) {

					System.exit(0);

				} else {
					System.out.println("########################### "+Utilities.getUsername(SplashScreen.this));
					if (Utilities.getUsername(SplashScreen.this).length() > 0
							|| !Utilities.getUsername(SplashScreen.this)
									.equalsIgnoreCase("")) {
						// Run next activity
						Intent intent = new Intent(SplashScreen.this,
								MenuActivity.class);
						startActivity(intent);
						
					} else {
						// Run next activity
						Intent intent = new Intent(SplashScreen.this,
								LoginScreen.class);
						startActivity(intent);
					}
				}
			}
		};
		SplashThread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
			synchronized (SplashThread) {
				SplashThread.notifyAll();
			}
		}
		return true;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		finish();
		super.onPause();
	}

	// Device back key
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			back = true;
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
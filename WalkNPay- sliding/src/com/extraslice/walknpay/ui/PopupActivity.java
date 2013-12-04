package com.extraslice.walknpay.ui;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PopupActivity extends Activity implements OnClickListener{
	Button Byes,BNo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		/*setContentView(R.layout.popup_window);
		
		Byes= (Button)findViewById(R.id.buttonYes);
		BNo= (Button)findViewById(R.id.buttonNo);
		
		Byes.setOnClickListener(this);
		BNo.setOnClickListener(this);
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonYes:
			    Intent in = new Intent(PopupActivity.this, MenuActivity.class);
			    startActivity(in);
			   PopupActivity.this.finish();
			   
			break;

		default:
			break;
		}
		
	}*/
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}

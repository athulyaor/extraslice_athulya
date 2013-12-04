package com.extraslice.walknpay.ui;

import java.text.DecimalFormat;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;


public class PaymentScreen extends Activity implements OnClickListener {
	ActionBar mactionBar;
	Button pay, cancel, ok, dismiss;
	TextView tvAmount, actionbartitle;
	Intent intent;
	Dialog dialog;
	LayoutInflater inflater;
	View forgot_view;
	RadioGroup rdGroup;
	RadioButton rdbtn1, rdbtn2, rdbtn3;

	String text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
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

		setContentView(R.layout.activity_pay);
		mactionBar.show();

		dialog = new Dialog(PaymentScreen.this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = dialog.getWindow();
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		tvAmount = (TextView) findViewById(R.id.lbltvAmount);
		pay = (Button) findViewById(R.id.lblpay);
		cancel = (Button) findViewById(R.id.lblcancel);

		rdGroup = (RadioGroup) findViewById(R.id.radioGroup);
		rdbtn1 = (RadioButton) findViewById(R.id.radio0);
		rdbtn2 = (RadioButton) findViewById(R.id.radio1);
		rdbtn3 = (RadioButton) findViewById(R.id.radio2);

		if (Utilities.total == 0.00) {
			tvAmount.setText("$0.00");
		} else {
			tvAmount.setText("$"
					+ String.valueOf(new DecimalFormat("##.##")
							.format(Utilities.total)));
		}
		pay.setOnClickListener(this);
		cancel.setOnClickListener(this);

		text = "My City Master (Card ending with 8890)";
		Utilities.card = text;

		rdGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.radio0:
					text = "My City Master (Card ending with 8890)";
					Utilities.card = text;
					break;
				case R.id.radio1:
					text = "Bofa Visa(Card ending with 347)";
					Utilities.card = text;
					break;
				case R.id.radio2:
					text = "Chase Debit (Card ending with 745)";
					Utilities.card = text;
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.lblpay:
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			forgot_view = inflater.inflate(R.layout.forgot_pass_dialog,
					(ViewGroup) findViewById(R.id.forgot_dialog_layout));
			dialog.setContentView(forgot_view);
			ok = (Button) dialog.findViewById(R.id.ok_button);
			dismiss = (Button) dialog.findViewById(R.id.can_button);
			ok.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					intent = new Intent(PaymentScreen.this,
							ConfirmationScreen.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					dialog.dismiss();
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
			break;
		case R.id.lblcancel:
			finish();
			break;
		default:
			break;
		}
	}
}

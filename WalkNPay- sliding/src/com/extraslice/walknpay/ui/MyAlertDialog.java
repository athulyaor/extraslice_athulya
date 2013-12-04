package com.extraslice.walknpay.ui;

import com.extraslice.walknpay.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class MyAlertDialog extends DialogFragment {
	
	
	

        public static MyAlertDialog newInstance(int title) {
            MyAlertDialog frag = new MyAlertDialog();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.logo)
                    .setTitle(title)
                    .setPositiveButton(R.string.alert_dialog_ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
//                                    ((FragmentDialogAlarmActivity) getActivity())
//                                            .doPositiveClick();
                                }
                            })
                    .setNegativeButton(R.string.alert_dialog_cancel,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                        int whichButton) {
                                   /* ((FragmentDialogAlarmActivity) getActivity())
                                            .doNegativeClick();*/
                                }
                            }).create();
        }
    }





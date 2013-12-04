package com.extraslice.walknpay.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.extraslice.walknpay.R;
import com.extraslice.walknpay.bl.Utilities;
import com.extraslice.walknpay.model.Products;
import com.extraslice.walknpay.ui.CartTab;

import android.app.Activity;
import android.app.Dialog;


import android.content.Context;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;



public class CartListAdapter extends ArrayAdapter<Products> {
	Context localcontext;
	public static  ArrayList<Products> localList;
	int layout;
    CartTab cart1;
	Products products;
	int count1;
	public CartListAdapter(Context context, int textViewResourceId,
			ArrayList<Products> theTRList,int count) {
		super(context, textViewResourceId, theTRList);
		CartListAdapter.localList = theTRList;
		this.localcontext = context;
		this.layout = textViewResourceId;
		this.count1=count;
	}

	public void refresh() {
  
	}

	public int getCount() {
		return localList.size();
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final TRHolder trHolder;
		products = localList.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) localcontext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layout, null);
			trHolder = new TRHolder();
			trHolder.tvPDescription = (TextView) convertView
					.findViewById(R.id.lbltvproductname);
			trHolder.tvPCount = (TextView) convertView
					.findViewById(R.id.lbltvproductcount);
			trHolder.tvPPrice = (TextView) convertView
					.findViewById(R.id.lbltvrowprice);
			trHolder.imgplus = (ImageView) convertView
					.findViewById(R.id.imgplus);
			trHolder.imgminus = (ImageView) convertView
					.findViewById(R.id.imgminus);
			trHolder.imgclear = (ImageView) convertView
					.findViewById(R.id.imgclear);
			
/*if(count1==1&&position==1)
{
	Products pr= (Products) trHolder.tvPCount.getTag();
	//pr.setProductcount(position);
	pr.setProductcount(localList.get(position).getProductcount());
}*/
			convertView.setTag(trHolder);
			trHolder.tvPCount.setTag(localList.get(position));
			

		} else {
			trHolder = (TRHolder) convertView.getTag();
			// ((TRHolder) convertView.getTag()).tvPCount.setTag(localList.get(position));
		}

		trHolder.tvPDescription.setText(products.getProductdesc());
		trHolder.tvPDescription.setTag(products);
		trHolder.tvPPrice.setText(String.valueOf(new DecimalFormat("##.##").format(products.getProductprice())));
        trHolder.tvPCount.setText(String.valueOf(localList.get(position).getProductcount()));
     
		refresh();

		trHolder.imgplus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Products product = (Products) trHolder.tvPDescription.getTag();
				String count = trHolder.tvPCount.getText().toString();
				product.setProductcount(Integer.parseInt(count) + 1);
				trHolder.tvPCount.setText(String.valueOf(Integer
						.parseInt(count) + 1));

				String price = trHolder.tvPPrice.getText().toString();

				double ProdPrice = Double.parseDouble(price)
						* (Integer.parseInt(count) + 1);
				Log.i("product price", ""+ProdPrice);
				String totalprice = String.valueOf(ProdPrice);
				System.out.println("Price " + totalprice);
				trHolder.tvPPrice.setText(String.valueOf(ProdPrice));

				refresh();
			}
		});

		if (Integer.parseInt(trHolder.tvPCount.getText().toString()) <= 1) {

			trHolder.imgminus.setVisibility(View.INVISIBLE);

		} else {

			trHolder.imgminus.setVisibility(View.VISIBLE);

			int quantitynum = Integer.parseInt(trHolder.tvPCount.getText()
					.toString());

			System.out.println("Quantity Number in else block" + quantitynum);

			trHolder.imgminus.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Products product = (Products) trHolder.tvPDescription
							.getTag();
					String count = trHolder.tvPCount.getText().toString();
					product.setProductcount(Integer.parseInt(count) - 1);
					trHolder.tvPCount.setText(String.valueOf(Integer
							.parseInt(count) - 1));

					String price = trHolder.tvPPrice.getText().toString();
					double ProdPrice = Double.parseDouble(price)
							* (Integer.parseInt(count) - 1);
					Log.i("product price", ""+ProdPrice);
					String totalprice = String.valueOf(ProdPrice);
					System.out.println("Price " + totalprice);
					trHolder.tvPPrice.setText(String.valueOf(ProdPrice));

					refresh();
				}
			});
		}

		trHolder.imgclear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				 final Dialog dialog = new Dialog(localcontext);
						dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
						dialog.setContentView(R.layout.custom_alert);
						
			 
						// set the custom dialog components - text, image and button
						TextView text = (TextView) dialog.findViewById(R.id.alertTitle);
						text.setText("Do you want to Delete?");
						
						
						//image.setImageResource(R.drawable.ic_launcher);
			 
						Button dialogButton = (Button) dialog.findViewById(R.id.alertpositivebutton);
						// if button is clicked, close the custom dialog
						dialogButton.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								
								localList.remove(position);
								refresh();
								dialog.dismiss();
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
		});

		return convertView;
	}

	public class TRHolder {

		public TextView tvPDescription, tvPCount, tvPPrice;
		public ImageView imgplus,imgclear,imgminus;

	}
}
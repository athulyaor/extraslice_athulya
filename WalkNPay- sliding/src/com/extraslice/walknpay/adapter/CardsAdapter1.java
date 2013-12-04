package com.extraslice.walknpay.adapter;

import java.util.ArrayList;

import com.extraslice.walknpay.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CardsAdapter1 extends BaseAdapter {
        
        Context context;
        ArrayList<String> CardsList;

        public CardsAdapter1(Context context, ArrayList<String> cardsList) {
                // TODO Auto-generated constructor stub
                Log.i("ADAPTER", "Log in");
                this.context = context;
                this.CardsList = cardsList;
        }

        @Override
        public int getCount() {
                // TODO Auto-generated method stub
                return CardsList.size();
        }

        @Override
        public Object getItem(int position) {
                // TODO Auto-generated method stub
                return position;
        }

        @Override
        public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position++;
        }
 class ViewHolder {
                TextView walletlisttext;
                ImageView walletlistarrowimage;
        }
        

        public View getView(int position, View v, ViewGroup parent) {
                // TODO Autogenerated method stub
                ViewHolder viewholder = null;
                LayoutInflater inflater = (LayoutInflater) context
                                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (v == null) {
                        v = inflater.inflate(R.layout.custom_list, null);

                        viewholder = new ViewHolder();
                        viewholder.walletlisttext = (TextView) v
                                        .findViewById(R.id.walletlisttext);
                        viewholder.walletlistarrowimage = (ImageView) v
                                        .findViewById(R.id.walletlistarrowimage);
                        v.setTag(viewholder);

                }

                else {
                        viewholder = (ViewHolder) v.getTag();
                }
Log.i("cardslist", ""+CardsList.get(position));
                viewholder.walletlisttext.setText(CardsList.get(position));

                viewholder.walletlistarrowimage.setBackgroundResource(R.drawable.arrow);
                
                return v;

        }


}
package com.example.android.datevalidation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView timingList;
    private InvoiceRequestAdapter invoiceRequestAdapter;
    private ArrayList<String> tempList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                timingList= (ListView) findViewById(R.id.timingList);
        tempList=new ArrayList<String>();
        tempList.add("7:00 AM");
        tempList.add("8:00 AM");
        tempList.add("10:00 AM");
        tempList.add("11:00 AM");
        tempList.add("11:30 AM");
        tempList.add("12:00 PM");
        tempList.add("1:00 PM");
        tempList.add("7:00 PM");


        checkTiming();
        invoiceRequestAdapter=new InvoiceRequestAdapter(MainActivity.this,tempList);
        timingList.setAdapter(invoiceRequestAdapter);
    }

    private void checkTiming() {
        
    }

    private class InvoiceRequestAdapter extends BaseAdapter {

        private ArrayList<String> redeemList;
        private Context context;
        private LayoutInflater mInflater;
        private ViewHolder holder;

        public InvoiceRequestAdapter(FragmentActivity activity, ArrayList<String> redeemGiftCodesList) {
            this.context = activity;
            this.redeemList = redeemGiftCodesList;
        }

        @Override
        public int getCount() {

            return redeemList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            private TextView date;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                convertView = mInflater.inflate(R.layout.item, parent, false);

                holder = new ViewHolder();

                holder.date= (TextView) convertView.findViewById(R.id.txtDate);


                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.date.setText(tempList.get(position)+ "");


            return convertView;
        }

    }

}

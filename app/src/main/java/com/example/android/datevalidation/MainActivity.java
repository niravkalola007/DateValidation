package com.example.android.datevalidation;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private ListView timingList;
    private InvoiceRequestAdapter invoiceRequestAdapter;
    private ArrayList<String> tempList;
    private ArrayList<String> compareTempList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                timingList= (ListView) findViewById(R.id.timingList);
        tempList=new ArrayList<String>();
        compareTempList=new ArrayList<String>();
        tempList.add("7:00 AM");
        tempList.add("8:00 AM");
        tempList.add("10:00 AM");
        tempList.add("11:00 AM");
        tempList.add("11:30 AM");
        tempList.add("12:00 PM");
        tempList.add("4:00 PM");
        tempList.add("7:00 PM");


        checkTiming();

    }

    private void checkTiming() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
//            SimpleDateFormat timeFormater = new SimpleDateFormat("hh:mm a");
            Date currentDate = new Date();
            String currentDateString = dateTimeFormat.format(currentDate);
            Log.e("current date in string", currentDateString + "");
            String currentDateStringForCompare = dateFormat.format(currentDate);
            Log.e("current date compare", currentDateString + "");
            Date currentDateAndTime = dateTimeFormat.parse(currentDateString);
            Log.e("current date in date", currentDateAndTime + "");
            for(int i=0;i<tempList.size();i++){
            String timingDateAndTime=currentDateStringForCompare+" "+tempList.get(i);
             Date newTimeAndDate= dateTimeFormat.parse(timingDateAndTime);
//                Log.e("newTimeAndDate",newTimeAndDate+"");
            if(currentDateAndTime.before(newTimeAndDate)){
                compareTempList.add(tempList.get(i));
                Log.e("newTimeAndDate result",newTimeAndDate+" ,,,"+tempList.get(i));
            }
            }

            invoiceRequestAdapter = new InvoiceRequestAdapter(MainActivity.this, compareTempList);
            timingList.setAdapter(invoiceRequestAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
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

            holder.date.setText(compareTempList.get(position)+ "");


            return convertView;
        }

    }

}

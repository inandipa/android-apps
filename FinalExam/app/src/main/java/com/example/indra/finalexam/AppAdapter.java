package com.example.indra.finalexam;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by indra on 6/14/16.
 */
public class AppAdapter extends ArrayAdapter<PersonDetails> {

    Context mContect;
    int mResource;
    ArrayList<PersonDetails> list;
    int img = R.drawable.person;


    public AppAdapter(Context context, int resource, List<PersonDetails> objects) {
        super(context, resource, objects);
        list = (ArrayList<PersonDetails>) objects;
        mContect = context;
        mResource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonDetails personDetails = list.get(position);
        ViewHolder holder;
        if( convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.txt_name = (TextView) convertView.findViewById(R.id.textViewName);
            holder.txt_Gifts = (TextView) convertView.findViewById(R.id.textViewGifts);
            holder.img_person =(ImageView) convertView.findViewById(R.id.imageView);
            holder.txt_Budget = (TextView) convertView.findViewById(R.id.textViewBudget);


            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();


        TextView textView = holder.txt_name;
        textView.setText(personDetails.getName());
        textView = holder.txt_Budget;
        String s = personDetails.budgetSpent+"/"+personDetails.getBudgetGiven();
        textView.setText(s);
        if(personDetails.getBudgetGiven() == personDetails.getBudgetSpent()){
            textView.setTextColor(ContextCompat.getColor(mContect, R.color.Green));
        }
        else{
            textView.setTextColor(ContextCompat.getColor(mContect, R.color.Red));
        }
        ImageView imageView = holder.img_person;
        imageView.setImageResource(img);
        textView = holder.txt_Gifts;
        textView.setText(String.valueOf( personDetails.getItems()));


        return convertView;
    }
    static class ViewHolder{
        TextView txt_name;
        TextView txt_Gifts;
        ImageView img_person;
        TextView txt_Budget;
    }
}

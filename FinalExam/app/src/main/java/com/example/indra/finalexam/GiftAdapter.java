package com.example.indra.finalexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra on 6/28/16.
 */
public class GiftAdapter extends ArrayAdapter<Gifts> {

    Context mContect;
    int mResource;
    ArrayList<Gifts> list;


    public GiftAdapter(Context context, int resource, List<Gifts> objects) {
        super(context, resource, objects);
        list = (ArrayList<Gifts>) objects;
        mContect = context;
        mResource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Gifts gifts = list.get(position);
        ViewHolder holder;
        if( convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.txt_name = (TextView) convertView.findViewById(R.id.textViewGiftName);
            holder.img =(ImageView) convertView.findViewById(R.id.imageView);
            holder.txt_Budget = (TextView) convertView.findViewById(R.id.textViewPrice);


            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();


        TextView textView = holder.txt_name;
        textView.setText(gifts.getGift());
        textView = holder.txt_Budget;
        textView.setText(gifts.getPrice()+"");
        ImageView imageView = holder.img;
        Picasso.with(mContect).load(gifts.getImageUrl()).into(imageView);


        return convertView;
    }
    static class ViewHolder{
        TextView txt_name;

        ImageView img;
        TextView txt_Budget;
    }
}

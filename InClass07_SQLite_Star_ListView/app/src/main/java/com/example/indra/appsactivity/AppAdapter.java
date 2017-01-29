package com.example.indra.appsactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by indra on 6/14/16.
 */
public class AppAdapter extends ArrayAdapter<AppDetails> {

    Context mContect;
    int mResource;
    ArrayList<AppDetails> mAppDetails;


    public AppAdapter(Context context, int resource, List<AppDetails> objects) {
        super(context, resource, objects);
        mAppDetails = (ArrayList<AppDetails>) objects;
        mContect = context;
        mResource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AppDetails appDetails = mAppDetails.get(position);
        ViewHolder holder;
        if( convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.txt_app = (TextView) convertView.findViewById(R.id.txt_app);
            holder.txt_cat = (TextView) convertView.findViewById(R.id.txt_cat);
            holder.txt_date =(TextView) convertView.findViewById(R.id.txt_date);
            holder.txt_price = (TextView) convertView.findViewById(R.id.txt_price);
            holder.txt_dev = (TextView) convertView.findViewById(R.id.txt_dev);
            holder.img_app = (ImageView) convertView.findViewById(R.id.img_app);
            holder.img_star = (ImageView) convertView.findViewById(R.id.star);

            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();


        TextView textView = holder.txt_app;
        textView.setText(appDetails.getApp_Title());
        textView = holder.txt_dev;
        textView.setText(appDetails.getDeveloper_name());
        textView = holder.txt_date;
        textView.setText(appDetails.getRelease_date());
        textView = holder.txt_price;
        textView.setText(appDetails.getApp_price());
        textView = holder.txt_cat;
        textView.setText(appDetails.getCategory());
        ImageView imageView = holder.img_app;
        Picasso.with(convertView.getContext())
                .load(appDetails.getSmall_photo_url())
                .into(imageView);
        imageView = holder.img_star;
        if(appDetails.isFav()) {
            imageView.setImageResource( R.drawable.yellow);
        }else{
            imageView.setImageResource(R.drawable.grey);
        }
        

        return convertView;
    }
    static class ViewHolder{
        TextView txt_app;
        TextView txt_dev;
        TextView txt_date;
        TextView txt_price;
        TextView txt_cat;
        ImageView img_app;
        ImageView img_star;
    }
}

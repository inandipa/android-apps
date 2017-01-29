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


        if( convertView == null) {
            LayoutInflater inflater;

            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);

        }
        AppDetails appDetails = mAppDetails.get(position);

        TextView textView = (TextView) convertView.findViewById(R.id.txt_app);
        textView.setText(appDetails.getApp_Title());
        textView = (TextView) convertView.findViewById(R.id.txt_dev);
        textView.setText(appDetails.getDeveloper_name());
        textView = (TextView) convertView.findViewById(R.id.txt_date);
        textView.setText(appDetails.getRelease_date());
        textView = (TextView) convertView.findViewById(R.id.txt_price);
        textView.setText(appDetails.getApp_price());
        textView = (TextView) convertView.findViewById(R.id.txt_cat);
        textView.setText(appDetails.getCategory());
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_app);
        Picasso.with(convertView.getContext())
                .load(appDetails.getSmall_photo_url())
                .into(imageView);

        return convertView;
    }
}

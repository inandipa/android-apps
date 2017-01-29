package com.example.indra.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by indra on 6/14/16.
 */
public class ColorAdapter extends ArrayAdapter<Colors> {
    Context mContect;
    int mResource;
    List<Colors> mColors;

    public ColorAdapter(Context context, int resource, List<Colors> objects) {
        super(context, resource, objects);
        this.mColors = objects;
        this.mContect= context;
        this.mResource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if( convertView == null) {
            LayoutInflater inflater;

            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);

        }
        Colors colors = mColors.get(position);

        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(colors.name);
         TextView textView1 = (TextView) convertView.findViewById(R.id.textView2);
       textView1.setText(colors.Hexname);

        return convertView;



    }
}

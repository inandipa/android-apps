package com.example.indra.messageme;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by indra on 6/14/16.
 */
public class AppAdapter extends ArrayAdapter<MessageDetails> {

    Context mContect;
    int mResource;
    ArrayList<MessageDetails> mMessageDetails;


    public AppAdapter(Context context, int resource, List<MessageDetails> objects) {
        super(context, resource, objects);
        mMessageDetails = (ArrayList<MessageDetails>) objects;
        mContect = context;
        mResource = resource;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageDetails messageDetails = mMessageDetails.get(position);
        ViewHolder holder;
        if( convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) mContect.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.txt_name = (TextView) convertView.findViewById(R.id.textViewName);
            holder.txt_time = (TextView) convertView.findViewById(R.id.textViewtime);
            holder.img_star =(ImageView) convertView.findViewById(R.id.imageView);


            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();


        TextView textView = holder.txt_name;
        textView.setText(messageDetails.getSender());
        textView = holder.txt_time;
        Date date = new Date(messageDetails.getCreationDateLong());
        //Log.d("demo",date.toString());
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy, HH:mm");
        textView.setText(df.format(date));
        ImageView imageView = holder.img_star;
        if(messageDetails.isRead()) {
            imageView.setImageResource( Resources.getSystem().getIdentifier("radiobutton_off_background", "drawable", "android"));
        }else{
            imageView.setImageResource(Resources.getSystem().getIdentifier("radiobutton_on_background", "drawable", "android"));
        }
        

        return convertView;
    }
    static class ViewHolder{
        TextView txt_name;
        TextView txt_time;
        ImageView img_star;
    }
}

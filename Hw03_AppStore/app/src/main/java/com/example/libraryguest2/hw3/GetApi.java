package com.example.libraryguest2.hw3;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;

/**
 * Created by Siddharth on 6/12/2016.
 */
public class GetApi extends AsyncTask<String,Void,ArrayList<App>> {

    MainActivity mainActivity;
    int count=0;
    public GetApi(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onPostExecute(final ArrayList<App> appArrayList) {

        Log.d("title fetched",appArrayList.size()+"Title:"+appArrayList.get(0).getTitle());
        Log.d("title fetched",appArrayList.size()+"Name:"+appArrayList.get(0).getName());
        Log.d("title fetched",appArrayList.size()+"Price:"+appArrayList.get(0).getPrice());
        Log.d("title fetched",appArrayList.size()+"Release Date:"+appArrayList.get(0).getDate());
        Log.d("title fetched",appArrayList.size()+"Icon Large:"+appArrayList.get(0).getIcon());
        super.onPostExecute(appArrayList);

        TextView titleView=(TextView)mainActivity.findViewById(R.id.textView);
        TextView devView=(TextView)mainActivity.findViewById(R.id.textView2);
        TextView releaseDate=(TextView)mainActivity.findViewById(R.id.textView3);
        TextView price=(TextView)mainActivity.findViewById(R.id.textView4);
        ImageView imgView=(ImageView)mainActivity.findViewById(R.id.imageView);

        titleView.setText(appArrayList.get(0).getTitle());
        devView.setText(appArrayList.get(0).getName());
        releaseDate.setText(appArrayList.get(0).getDate());
        price.setText(String.valueOf(appArrayList.get(0).getPrice()));
        Picasso.with(mainActivity).load(appArrayList.get(0).getIcon()).into(imgView);


        mainActivity.findViewById(R.id.btn_nxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count==100)
                    count=0;
                TextView titleView=(TextView)mainActivity.findViewById(R.id.textView);
                TextView devView=(TextView)mainActivity.findViewById(R.id.textView2);
                TextView releaseDate=(TextView)mainActivity.findViewById(R.id.textView3);
                TextView price=(TextView)mainActivity.findViewById(R.id.textView4);
                ImageView imgView=(ImageView)mainActivity.findViewById(R.id.imageView);

                titleView.setText(appArrayList.get(count).getTitle());
                devView.setText(appArrayList.get(count).getName());
                releaseDate.setText(appArrayList.get(count).getDate());
                price.setText(String.valueOf(appArrayList.get(count).getPrice()));
                Picasso.with(mainActivity).load(appArrayList.get(count).getIcon()).into(imgView);
            }
        });

           mainActivity.findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView titleView = (TextView) mainActivity.findViewById(R.id.textView);
                TextView devView = (TextView) mainActivity.findViewById(R.id.textView2);
                TextView releaseDate = (TextView) mainActivity.findViewById(R.id.textView3);
                TextView price = (TextView) mainActivity.findViewById(R.id.textView4);
                ImageView imgView = (ImageView) mainActivity.findViewById(R.id.imageView);

                count--;
                if(count==-1) {
                    count = 99;
                }
                    titleView.setText(appArrayList.get(count).getTitle());
                    devView.setText(appArrayList.get(count).getName());
                    releaseDate.setText(appArrayList.get(count).getDate());
                    price.setText(String.valueOf(appArrayList.get(count).getPrice()));
                    Picasso.with(mainActivity).load(appArrayList.get(count).getIcon()).into(imgView);


            }
        });



       mainActivity.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(appArrayList.get(count).getUri()));
                mainActivity.startActivity(i);

            }
        });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<App> doInBackground(String... params) {

        try {
            URL url=new URL(params[0]);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int statusCode=conn.getResponseCode();
            if(statusCode==HttpURLConnection.HTTP_OK)
            {
                return AppUtil.AppPullParser.parseApp(conn.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.indra.madweather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by indra on 6/9/16.
 */
public class GetJson extends AsyncTask<String,Void,String> {
    BufferedReader reader=null;

    MainActivity activity;


    GetJson(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        Log.d("demo1",s);

        new JsonParse(activity).execute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        try{
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int status = con.getResponseCode();
                if(status == HttpURLConnection.HTTP_OK) {
                    InputStream in = con.getInputStream();
                    Log.d("demo", "sent for parsing");
                    return in.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        finally {
        } {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}

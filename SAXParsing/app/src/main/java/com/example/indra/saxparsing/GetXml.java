package com.example.indra.saxparsing;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by indra on 6/12/16.
 */
public class GetXml extends AsyncTask<String,Void,Weather> {
    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        Log.d("demo",weather.getName());
    }

    @Override
    protected Weather doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode = con.getResponseCode();
            if(statuscode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                return WeatherUtil.WeatherParse.ParseWeather(in);


            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return null;
    }
}

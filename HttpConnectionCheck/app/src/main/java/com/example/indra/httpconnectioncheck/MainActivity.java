package com.example.indra.httpconnectioncheck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            new GetData().execute("http://rss.cnn.com/rss/cnn_us.rss");
            new GetImage().execute("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
        }
            else{

            Log.d("demo","failed");
            }

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
            return false;

    }

    private class GetImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);
            ImageView im = (ImageView) findViewById(R.id.imageView);
                    im.setImageBitmap(s);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            InputStream in = null;
            try {
                URL url = new URL(params[0]);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                     in = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                   return bitmap;


                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }finally {
                if(in!=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("demo",s);
        }

        @Override
        protected String doInBackground(String... params) {

            BufferedReader inputStream=null;
            try {
                URL url = new URL(params[0]);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line="";
                    if((line =inputStream.readLine()) !=null){
                        sb.append(line+"\n");
                    }
                    return sb.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}

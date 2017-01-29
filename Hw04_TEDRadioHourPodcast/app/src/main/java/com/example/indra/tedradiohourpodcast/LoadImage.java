package com.example.indra.tedradiohourpodcast;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by indra on 6/19/16.
 */
public class LoadImage extends AsyncTask<String,Void,Bitmap> {

    MyAdapter myAdapter;
    public LoadImage(MyAdapter myAdapter) {
        this.myAdapter = myAdapter;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        myAdapter.setBitmap(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;

        try {
            url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status==HttpURLConnection.HTTP_OK){
                Bitmap bit = BitmapFactory.decodeStream((InputStream)url.getContent());
                return bit;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;

    }
}
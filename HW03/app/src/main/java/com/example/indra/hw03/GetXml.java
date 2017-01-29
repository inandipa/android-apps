package com.example.indra.hw03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by indra on 6/12/16.
 */
public class GetXml extends AsyncTask<String,Void,AppDetails[]> {
    ProgressDialog progressDialog;
    MainActivity activity;

    public GetXml(MainActivity activity){
        this.activity = activity;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(AppDetails[] appDetailses) {

        /*super.onPostExecute(appDetailses);
       for(int i = 0;i<100;i++) {
           Log.d("demo", appDetailses[i].toString());

       }
       */
        progressDialog.dismiss();
        Intent intent = new Intent(activity,Apps_Activity.class);
        intent.putExtra("Data",appDetailses);
        activity.startActivity(intent);

    }

    @Override
    protected AppDetails[] doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status = con.getResponseCode();
            if(status == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                Log.d("demo","sent for parsing");
                return AppDetailsUtill.ParseAppDetails.ParseXml(in);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return new AppDetails[0];
    }
}

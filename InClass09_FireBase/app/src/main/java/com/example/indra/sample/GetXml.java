package com.example.indra.sample;

import android.app.ProgressDialog;
import android.content.Context;
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
 * Created by indra on 6/14/16.
 */
public class GetXml extends AsyncTask<String,Void,ArrayList<AppDetails>> {
    Context activity;
    IData data;
    ProgressDialog progressDialog;

    public GetXml(Context activity,IData data){
        this.activity = activity;
        this.data = data;

    }


    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading results");
        progressDialog.show();

    }

    @Override
    protected void onPostExecute(ArrayList<AppDetails> appDetailses) {
        super.onPostExecute(appDetailses);
        progressDialog.dismiss();

        data.getData(appDetailses);
    }

    @Override
    protected ArrayList<AppDetails> doInBackground(String... params) {

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
        return null;
    }

     public interface IData{

         void getData(ArrayList<AppDetails> w);
    }

}

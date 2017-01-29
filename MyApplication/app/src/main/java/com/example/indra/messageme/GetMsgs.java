package com.example.indra.messageme;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by indra on 6/27/16.
 */
public class GetMsgs extends AsyncTask<String,Void,ArrayList<MessageDetails>> {
    ProgressDialog progressDialog;
    Context context;
    IData iData;
    BufferedReader reader=null;

    GetMsgs(Context context,IData iData){
        this.context = context;
        this.iData = iData;

    }

    @Override
    protected void onPostExecute(ArrayList<MessageDetails> messageDetailses) {
        super.onPostExecute(messageDetailses);
        progressDialog.dismiss();
        iData.GetData(messageDetailses);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList<MessageDetails> doInBackground(String... params) {
        ArrayList<MessageDetails> list = new ArrayList<>();
        try {
            URL  url= new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line="";
            while((line= reader.readLine())!=null){
                sb.append(line);
            }




            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    interface IData{
        public void GetData(ArrayList<MessageDetails> result);
    }
}

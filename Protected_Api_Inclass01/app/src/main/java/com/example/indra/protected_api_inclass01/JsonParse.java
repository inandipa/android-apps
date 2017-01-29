package com.example.indra.protected_api_inclass01;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by indra on 6/9/16.
 */
public class JsonParse extends AsyncTask<String,Void,Integer> {

    Integer code,status;
    MainActivity activity;


    JsonParse(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer status) {

        super.onPostExecute(status);
     //  Log.d("demo1",weather.getCode()+"");
        activity.action_response(status);


    }

    @Override
    protected Integer doInBackground(String... params) {


        JSONObject root  = null;
        try {
            root = new JSONObject(params[0]);

            code = root.getInt("status");
            status = root.getInt("id");
            return status;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

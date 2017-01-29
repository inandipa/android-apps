package com.example.indra.protected_api_inclass01;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by indra on 9/8/16.
 */
public class JsonParseData extends AsyncTask<String,Void,String> {


    Home activity;
    JSONArray array;


    JsonParseData(Home activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String name) {

        super.onPostExecute(name);
        //  Log.d("demo1",weather.getCode()+"");
        activity.show(name);


    }

    @Override
    protected String doInBackground(String... params) {


        JSONObject root  = null;
        try {
            root = new JSONObject(params[0]);

            JSONObject object = root.getJSONObject("data");
            String name = object.getString("name");
            return name;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

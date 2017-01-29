package com.example.indra.protected_api_inclass01;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by indra on 9/8/16.
 */
public class JsonParseToken  extends AsyncTask<String,Void,String> {

    String code;
    Login activity;


    JsonParseToken(Login activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String code) {

        super.onPostExecute(code);
        //  Log.d("demo1",weather.getCode()+"");

        MainActivity.token = code;
        activity.secure_page(code);


    }

    @Override
    protected String doInBackground(String... params) {


        JSONObject root  = null;
        try {
            root = new JSONObject(params[0]);


            code = root.getString("token");

            return code;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}


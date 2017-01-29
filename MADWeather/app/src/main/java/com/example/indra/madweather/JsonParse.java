package com.example.indra.madweather;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by indra on 6/9/16.
 */
public class JsonParse extends AsyncTask<String,Void,Weather> {

    String code;
    MainActivity activity;


    JsonParse(MainActivity activity){
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Weather weather) {

     //  Log.d("demo1",weather.getCode()+"");
        activity.showweatherdata(weather);

        super.onPostExecute(weather);
    }

    @Override
    protected Weather doInBackground(String... params) {

        Weather weather = new Weather();
        JSONObject root  = null;
        try {
            root = new JSONObject(params[0]);
            code = root.getString("cod");
            weather.setCode(code);
            if(code.equals("200")) {
                weather.setName((root.getJSONObject("city")).getString("name"));
                JSONArray JsonArray = root.getJSONArray("list");
                JSONObject jsonObject = JsonArray.getJSONObject(0);
                JSONObject main = jsonObject.getJSONObject("main");
                ArrayList<String> icon = new ArrayList<>();
                ArrayList<String> weatherlist = new ArrayList<>();
                weather.setHumidity(main.getInt("humidity"));
                weather.setPressure(main.getInt("pressure"));
                weather.setTemp((Double) main.getDouble("temp"));
                JSONArray weatherarray = jsonObject.getJSONArray("weather");
                for (int i = 0; i < weatherarray.length(); i++) {
                    jsonObject = weatherarray.getJSONObject(i);
                    weatherlist.add(jsonObject.getString("description"));
                    icon.add(jsonObject.getString("icon"));
                }
                weather.setWeatherlist(weatherlist);
                weather.setIcon(icon);

                return weather;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

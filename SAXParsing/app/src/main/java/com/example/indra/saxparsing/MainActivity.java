package com.example.indra.saxparsing;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    String units;
    String input;
    String url;
    boolean metric1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (iSConnectedOnline()) {
            Log.d("demo1", "success");
            findViewById(R.id.buttonSearch).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    EditText editText = (EditText)findViewById(R.id.editTextSearch);
                    input = editText.getText().toString();
                    String  some=input;
                    try {
                        some  = URLEncoder.encode(input,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Switch metric =(Switch) findViewById(R.id.switch1);
                    if(metric.isChecked()){
                        units = "metric";
                        metric1 = true;
                    }else{
                        units="imperial";
                        metric1=false;
                    }
                    url = "http://api.openweathermap.org/data/2.5/forecast/city?q="+some+"&units="+units+"&mode=xml&APPID="+"5050c7749fce473ffc48929176784e88";

                    new GetXml().execute(url);

                }
            });


        } else {

            Toast.makeText(this, "NO Network connection", Toast.LENGTH_SHORT).show();
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

    /*

    public void showweatherdata(Weather weather) {
        if(weather.getCode().equals("200")){
            Intent intent = new Intent(MainActivity.this,Weather_summary.class);
            intent.putExtra("weather", weather);
            intent.putExtra("metric",metric1);
            startActivity(intent);

        }
        else {
            Toast.makeText(MainActivity.this, "invalid inputs", Toast.LENGTH_SHORT).show();
        }
    }
    */

}

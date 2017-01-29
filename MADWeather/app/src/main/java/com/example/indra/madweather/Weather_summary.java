package com.example.indra.madweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Weather_summary extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_summary);

        Weather weather = (Weather)getIntent().getExtras().getSerializable("weather");
        setTitle(weather.getName());

        String s1;
        if(getIntent().getExtras().getBoolean("metric")){
            s1 = "Celsius";
        }
        else{
            s1 = "Fahrenheit";
        }
        String s;
        textView = (TextView) findViewById(R.id.textViewtem_value);
        s = String.valueOf(weather.getTemp())+s1;
        textView.setText(s);
        textView = (TextView) findViewById(R.id.textViewHum_value);
         s =String.valueOf(weather.getHumidity())+"%";
        textView.setText(s);
        textView = (TextView) findViewById(R.id.textViewpre_value);
        s = String.valueOf(weather.getPressure())+ "hPa";
        textView.setText(s);
        textView = (TextView) findViewById(R.id.textViewweather_value);
        ArrayList<String> list = weather.getWeatherlist();
        String string="";
        for(int i = 0 ; i<list.size();i++){
            if(string.length()!=0){
                string = string.concat(",");
            }
            string = string.concat(list.get(i));

        }
        textView.setText(string);



    }
}

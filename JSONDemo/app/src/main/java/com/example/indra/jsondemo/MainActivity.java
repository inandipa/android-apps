package com.example.indra.jsondemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class MainActivity extends Activity {
    String[] Keywords = {"Uncc","Android","Winter","Aurora","Wonders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose a Keywoard");
        builder.setItems(Keywords, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new DoWork().execute(Keywords[which]);


            }
        });
        final AlertDialog alertDialog = builder.create();
        findViewById(R.id.buttonGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo","clicling");
                alertDialog.show();

                Log.d("demo","clicked");
            }
        });

    }

    public class DoWork extends AsyncTask<String,Void,String>{

        BufferedReader reader = null;

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("demo",s);
        }

        @Override
        protected String doInBackground(String... params) {
            String link = "http://dev.theappsdr.com/apis/summer_2016/inclass5/index.php?keyword=";
            link = link+params[0];
            try {
                URL url = new URL(link);
                try {
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line="";
                    while((line= reader.readLine())!=null){
                        sb.append(line).append("\n");
                    }

                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }finally {
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
    }
}

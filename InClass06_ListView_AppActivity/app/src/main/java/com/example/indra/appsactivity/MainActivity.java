package com.example.indra.appsactivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetXml.IData{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml";

            new GetXml(this).execute(url);

        }
        else{
            Toast.makeText(MainActivity.this, "NO Network connection", Toast.LENGTH_SHORT).show();
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

    @Override
    public void getData(final ArrayList<AppDetails> result) {
        for(int i = 0;i<result.size();i++) {
            Log.d("demo", result.get(i).toString());

        }
        AppAdapter adapter = new AppAdapter(this,R.layout.row,result);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", (result.get(position)).toString());
                Intent intent = new Intent(MainActivity.this, Preview_Activity.class);
                intent.putExtra("Data",result.get(position));
                startActivity(intent);
            }
        });

    }
}
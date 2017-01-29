package com.example.indra.hw03;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml";

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
}

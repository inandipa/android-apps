package com.example.libraryguest2.hw3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        if(isConnection())
        {
                new GetApi(this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");
        }
        else{
            Toast.makeText(MainActivity.this, "Connection interrupted!", Toast.LENGTH_SHORT).show();
        }


    }


    protected boolean isConnection()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo obj=cm.getActiveNetworkInfo();
        if(obj!=null && obj.isConnected())
            return true;
        else
            return false;
    }


}

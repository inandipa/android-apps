package com.example.indra.appactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements Login.OnFragmentInteractionListener,List.OnDataListener,Preview.OnItemsListener{

    AppDetails appDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Login() ,"login" )
                .commit();
    }

    @Override
    public void onFragmentInteraction() {
        Log.d("demo","login sucesss");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new List() ,"list")
                .commit();
    }
    public  void OnData(AppDetails appDetails){
        Log.d("demo",appDetails.toString());

        this.appDetails = appDetails;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Preview() ,"preview")
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void Onviews(ImageView im, TextView tv) {
        tv.setText(appDetails.getApp_Title());
        Picasso.with(this)
                .load(appDetails.getSmall_photo_url())
                .into(im);

    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}

package com.example.indra.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity  implements SignUp.OnFragmentInteractionListener,Login.LoginListner,List.OnDataListener,Preview.OnItemsListener{

    static public Firebase firebaseRoot;
    AppDetails appDetails;
    int star_off = R.drawable.grey;
    int getStar_on = R.drawable.yellow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase("https://sample-e3deb.firebaseio.com");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new Login() ,"login" )
                .commit();

         }

    @Override
    public void cancel() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Login(), "login")
                .commit();
    }

    @Override
    public void onFragmentInteraction(int i) {
        if(i==1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new List(), "login")
                    .commit();
        }else{

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignUp(), "login")
                    .commit();
        }


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
    public void Onviews(ImageView im, TextView tv,ImageView star) {
        tv.setText(appDetails.getApp_Title());
        Picasso.with(this)
                .load(appDetails.getSmall_photo_url())
                .into(im);
        if(appDetails.isFav()){
            star.setImageResource(getStar_on);
        }else {
            star.setImageResource(star_off);
        }


    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            Log.d("demo1","poped");
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}

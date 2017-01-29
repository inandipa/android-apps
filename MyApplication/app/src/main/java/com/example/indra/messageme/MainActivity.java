package com.example.indra.messageme;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public  class MainActivity extends AppCompatActivity implements SignUp.SignUpListner,Login.LoginListner,MsgsList.OnDataListener,Read_msg.OnRead_msg_Listner{

    static public Firebase firebaseRoot;
     static  FirebaseAuth mAuth;
    static public FirebaseUser user;
    Bundle bundl;

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase("https://messageme-9f9de.firebaseio.com");
      mAuth = FirebaseAuth.getInstance();
       mAuth.signOut();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MsgsList(), "inbox")
                    .commit();
            Log.d("demo",user.getUid()+"");
            Toast.makeText(this,user.getUid()+"",Toast.LENGTH_LONG).show();


        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new Login() ,"login" )
                    .commit();
        }


    }

    @Override
    public void AddToAuth() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MsgsList() ,"inbox" )
                .commit();
    }

    @Override
    public void onLogin(int i) {
        if(i==1) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MsgsList(), "inbox")
                    .commit();
        }else{

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new SignUp(), "signup")
                    .commit();
        }
    }

    @Override
    public void OnNewMsg() {

        MessageDetails messageDetails  = new MessageDetails();
        bundl = new Bundle();
        bundl.putSerializable("Data", messageDetails);

        WriteMsg writeMsg = new WriteMsg();
        writeMsg.setArguments(bundl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container,writeMsg);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void OnMsgRead(MessageDetails appDetails) {
        bundl = new Bundle();
        bundl.putSerializable("Data", appDetails);

        Read_msg read_msg = new Read_msg();
        read_msg.setArguments(bundl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container,read_msg);
        ft.addToBackStack(null);
        ft.commit();


    }

    @Override
    public void reload() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MsgsList() ,"inbox" )
                .commit();
    }

    @Override
    public void reply(MessageDetails messageDetails) {

        bundl = new Bundle();
        bundl.putSerializable("Data", messageDetails);

        WriteMsg writeMsg = new WriteMsg();
        writeMsg.setArguments(bundl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container,writeMsg);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    public void delete() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new MsgsList(), "inbox")
                .commit();

    }

   }

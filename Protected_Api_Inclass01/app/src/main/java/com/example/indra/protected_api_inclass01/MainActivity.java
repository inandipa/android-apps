package com.example.indra.protected_api_inclass01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    String name,email,password,url;
    static String token;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            Button signup = (Button) findViewById(R.id.signup);
            final Button login = (Button) findViewById( R.id.login_signup);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });
                    signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText email_et = (EditText) findViewById(R.id.email_input);
                    EditText name_et = (EditText) findViewById(R.id.name_input);
                    EditText password_et = (EditText) findViewById(R.id.password_input);
                    tv = (TextView)findViewById(R.id.warning);

                    email = email_et.getText().toString();
                    name = name_et.getText().toString();
                    password = password_et.getText().toString();
                    if(email.equals("") || name.equals("")  || password.equals("") || !isValidEmail(email)){

                        tv.setText("invalid input");

                    }else {
                        Log.d("demo",email);
                        Log.d("demo",name);
                        Log.d("demo",password);
                        tv.setText("");
                        url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass01_restful_api/signup.php?signup=signup&email=" + email + "&password=" + password + "&name=" + name;

                        new GetJson(MainActivity.this).execute(url);
                    }
                }
            });

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
    public void action_response(int status){
        if(status == -1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Connection problem please try again after some time")
                    .setTitle("Warning");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });


            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(status == 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("User already exist please login")
                    .setTitle("Warning");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    login();
                }
            });


            AlertDialog dialog = builder.create();
            dialog.show();
        }else if(status == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Account created please login")
                    .setTitle("info");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                    login();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public void login(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}

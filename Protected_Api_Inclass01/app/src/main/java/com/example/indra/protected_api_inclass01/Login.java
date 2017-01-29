package com.example.indra.protected_api_inclass01;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    TextView tv;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            Button login = (Button) findViewById(R.id.login);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText email_et = (EditText) findViewById(R.id.login_email);
                    EditText password_et = (EditText) findViewById(R.id.login_pass);
                    tv = (TextView)findViewById(R.id.warning);

                    email = email_et.getText().toString();
                    String password = password_et.getText().toString();

                    if(email.equals("")  || password.equals("") || !isValidEmail(email)){

                        tv.setText("invalid input");

                    }else {
                        Log.d("demo",email);
                        Log.d("demo",password);
                        tv.setText("");
                        String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass01_restful_api/login.php?login=login&email=" + email + "&password=" + password ;

                        new GetToken(Login.this).execute(url);
                    }
                }
            });

        }
        else{
            Toast.makeText(Login.this, "NO Network connection", Toast.LENGTH_SHORT).show();
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
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public  void secure_page( String token ){
        if(token == null || token.equals("null") ){
            tv.setText("invalid credentials");
        }else {
            Log.d("demo", token);
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }
}

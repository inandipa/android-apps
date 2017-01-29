package com.example.indra.protected_api_inclass01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       tv= (TextView)findViewById(R.id.name_from_db);
        String url = "http://inclass01-env.us-west-2.elasticbeanstalk.com/Inclass01_restful_api/validate_user.php?user=" + Login.email + "&token=" + MainActivity.token ;

        new Getdata(Home.this).execute(url);

    }
    public void show(String name ){
        tv.setText(name);
    }
}

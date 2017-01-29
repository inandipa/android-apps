package com.example.indra.intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(getIntent().getExtras()!=null){
            String name = getIntent().getExtras().getString("name");
            double d = (double)getIntent().getExtras().getDouble("age");
            User user = (User)getIntent().getExtras().getSerializable("user");
            Person person = (Person)getIntent().getExtras().getParcelable("Person");
            Toast.makeText(this,name+" , "+d+","+user+","+person.toString(),Toast.LENGTH_LONG).show();
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

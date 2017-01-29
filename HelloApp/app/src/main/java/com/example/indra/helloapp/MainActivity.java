package com.example.indra.helloapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button b = (Button) findViewById(R.id.buttonOk);
        Log.d("demo", "Botton Text is " + b.getText().toString());
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", "Botton Text is " + b.getText().toString());
            }
        });

        findViewById(R.id.buttonCancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("demo","some botton selected");
    }

    public void CancledButton (View v){
        Log.d("demo","Cancel button selected");
    }
}

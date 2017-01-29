package com.example.indra.appsactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import it.sephiroth.android.library.picasso.Picasso;

public class Preview_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_);

        setTitle("Preview Activity");

        AppDetails appDetails = (AppDetails) getIntent().getExtras().getSerializable("Data");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
       TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(appDetails.getApp_Title());
        Picasso.with(this)
                .load(appDetails.getSmall_photo_url())
                .into(imageView);

    }
}

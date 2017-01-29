package com.example.indra.hw03;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Apps_Activity extends AppCompatActivity {

    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_);

        setTitle("Apps Activity");
        final AppDetails[] appDetailses =(AppDetails[]) getIntent().getExtras().get("Data");
       ShowData(appDetailses);

        findViewById(R.id.imageButtonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i>=100){
                    i=i%100;
                }
                ShowData(appDetailses);
            }
        });
        findViewById(R.id.imageButtonPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if(i==-1){
                    i=99;
                }
                ShowData(appDetailses);
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = appDetailses[i].getUri();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }
    public  void ShowData(AppDetails[] appDetailses){
        TextView textView = (TextView)findViewById(R.id.textViewTitle);
        textView.setText(appDetailses[i].getApp_Title());

        textView = (TextView)findViewById(R.id.textViewDeveloper);
        textView.setText(appDetailses[i].getDeveloper_name());

        textView = (TextView)findViewById(R.id.textViewReleaseDate);
        textView.setText(appDetailses[i].getRelease_date());

        textView = (TextView)findViewById(R.id.textViewPrice);
        textView.setText(appDetailses[i].getApp_price());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this)
                .load(appDetailses[i].getSmall_photo_url())
                .into(imageView);
    }
}

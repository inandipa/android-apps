package com.example.indra.intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                Intent intent = new Intent("com.example.indra.intents.intent.action.VIEW");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra("name", "indra kiran reddy");
                intent.putExtra("age", (double) 22.23);
                User user = new User("nik", 21.34);
                intent.putExtra("user", user);
                Person person = new Person("indra","NC",22.34);
                intent.putExtra("Person",person);

                startActivity(intent);
            }
        });

        findViewById(R.id.buttonUncc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:0932487234820"));
                Log.d("demo","call button");
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    return;
                }
                startActivity(intent);
            }
        });
    }
}

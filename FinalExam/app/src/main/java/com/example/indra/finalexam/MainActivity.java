package com.example.indra.finalexam;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public Firebase firebaseRoot;
    ListView listView;
    ArrayList<PersonDetails> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase("https://finalexam-4ba60.firebaseio.com");




    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        run();
    }


    void run(){
        if(iSConnectedOnline()) {
            Log.d("demo1", "success");
            list = new ArrayList<>();
            //MainActivity.firebaseRoot.child("persons").child("-KLONv9v-fk-qN048YWH").child("gifts").setValue("good");


            final AppAdapter adapter = new AppAdapter(this,R.layout.row,list);
            listView = (ListView) findViewById(R.id.listView);
            //listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("demo", (list.get(position)).toString());
                    Intent intent = new Intent(MainActivity.this,Gift_List.class);
                    intent.putExtra("Data",list.get(position));
                    startActivity(intent);
                }
            });



            MainActivity.firebaseRoot.child("persons").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    PersonDetails personDetails =dataSnapshot.getValue(PersonDetails.class);
                    personDetails.setKey(dataSnapshot.getKey());
                    list.add(personDetails);
                    Log.d("demo",personDetails.toString());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            listView.setAdapter(adapter);



        }
        else{
            Toast.makeText(MainActivity.this, "NO Network connection", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_person:
                Toast.makeText(MainActivity.this, " Create new person", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,add_person.class);
                startActivity(intent);
                break;

        }
        return true;
    }

}

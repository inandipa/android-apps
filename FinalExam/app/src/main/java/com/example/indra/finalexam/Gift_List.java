package com.example.indra.finalexam;

import android.content.Intent;
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
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class Gift_List extends AppCompatActivity {

    ArrayList<Gifts> list;
    ListView listView;
    static String name;
    static PersonDetails personDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift__list);


        personDetails = (PersonDetails) getIntent().getExtras().getSerializable("Data");
       list = new ArrayList<>();
        name = personDetails.getName();
        setTitle(personDetails.getName());

        final GiftAdapter adapter = new GiftAdapter(this,R.layout.gift_row,personDetails.getGifts());
        listView = (ListView) findViewById(R.id.listViewGifts);
        //listView.setAdapter(adapter);


        MainActivity.firebaseRoot.child("persons").child(personDetails.getKey()).child("Gifts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Gifts gifts =dataSnapshot.getValue(Gifts.class);
                gifts.setKey(dataSnapshot.getKey());
                list.add(gifts);
                Log.d("demo","called firebasse");
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_gift_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_gift:
                Toast.makeText(Gift_List.this, " Select from list", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Gift_List.this,add_gift.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}

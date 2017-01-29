package com.example.indra.finalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class add_gift extends AppCompatActivity {

    ArrayList<Gifts> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift);

        list = new ArrayList<>();
        setTitle(Gift_List.name);
        final int costRemaining = Gift_List.personDetails.getBudgetGiven() - Gift_List.personDetails.getBudgetSpent();

        final GiftAdapter adapter = new GiftAdapter(this,R.layout.gift_row,list);
        listView = (ListView) findViewById(R.id.listViewGiftsToAdd);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.firebaseRoot.child("persons").child(Gift_List.personDetails.getKey()).child("Gifts").push().setValue(list.get(position));
                int  i =Gift_List.personDetails.getItems();
                i++;
                Gift_List.personDetails.setItems(i);
                 i = Gift_List.personDetails.getBudgetSpent();
                i=i+list.get(position).getPrice();
                Gift_List.personDetails.setBudgetSpent(i);
                ArrayList<Gifts> list1 = Gift_List.personDetails.getGifts();
                list1.add(list.get(position));
                Gift_List.personDetails.setGifts(list1);
                MainActivity.firebaseRoot.child("persons").child(Gift_List.personDetails.getKey()).setValue(Gift_List.personDetails);
                finish();

            }
        });

        MainActivity.firebaseRoot.child("Gifts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Gifts gifts =dataSnapshot.getValue(Gifts.class);
                gifts.setKey(dataSnapshot.getKey());
                if(costRemaining>=gifts.getPrice()) {
                    list.add(gifts);
                    adapter.notifyDataSetChanged();
                }
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
}

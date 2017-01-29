package com.example.indra.listviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //String[] Colors = {"Red,Blure","white","Yello","Green","emrold","brown","any","cream","a","b","qfc","a","qsb"};
    ArrayList<Colors> Colord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Colord = new ArrayList<>();
        Colord.add(new Colors("fe","wdacz"));
        Colord.add(new Colors("feesvd","wdavvcz"));
        Colord.add(new Colors("fe","wdasvsvcz"));
        Colord.add(new Colors("fev","wdsdacz"));
        Colord.add(new Colors("fevs","wdacz"));
        Colord.add(new Colors("fes","wdacz"));
        Colord.add(new Colors("fe","wdacz"));
        Colord.add(new Colors("fvse","wdacz"));
        Colord.add(new Colors("fe","wdacz"));

        ColorAdapter adapter = new ColorAdapter(this,R.layout.row,Colord);
       // ArrayAdapter<Colors> adapter = new ArrayAdapter<Colors>(this,android.R.layout.simple_list_item_1,Colord);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", (Colord.get(position)).toString());
            }
        });
    }
}

package com.example.indra.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseManager dm;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dm.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DatabaseManager(this);
        dm.save(new Note("Subject1","note1"));
        dm.save(new Note("Subject2","note2"));
        dm.save(new Note("Subject3","note3"));
        dm.save(new Note("Subject4","note4"));

        List<Note> notes = dm.getAll();
        Note note1 = dm.get(3);

        Log.d("demo1",notes.toString());
    }
}

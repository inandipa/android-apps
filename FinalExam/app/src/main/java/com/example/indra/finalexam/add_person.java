package com.example.indra.finalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class add_person extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_person:
                Toast.makeText(add_person.this, "person added", Toast.LENGTH_SHORT).show();
                PersonDetails personDetails = new PersonDetails();
                EditText editText = (EditText)findViewById(R.id.editTextName);
                personDetails.setName(editText.getText().toString());
                editText = (EditText)findViewById(R.id.editTextBudget);
                int num = Integer.parseInt(editText.getText().toString());
                Log.d("demo",num+"");
                personDetails.setBudgetGiven(num);
                MainActivity.firebaseRoot.child("persons").push().setValue(personDetails);


               finish();
                break;

        }
        return true;
    }

}

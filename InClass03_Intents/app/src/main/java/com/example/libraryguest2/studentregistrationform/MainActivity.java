package com.example.libraryguest2.studentregistrationform;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String STUDENT_KEY="STUDENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_sbmt=(Button) findViewById(R.id.btn_sbmt);
        btn_sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameObj = (EditText) findViewById(R.id.inp_name);
                String name = nameObj.getText().toString();
                if (name.length() == 0) {
                    Toast.makeText(MainActivity.this, "You need to enter name to proceed", Toast.LENGTH_LONG).show();
                } else {
                    EditText emailObj = (EditText) findViewById((R.id.inp_email));
                    String email = emailObj.getText().toString();
                    int count=0;
                    for(int i=0;i<email.length();i++) {
                        if (email.contains("@") && email.contains(".")) {
                            count = 1;
                        }
                    }
                    if(count==1) {
                        RadioGroup rd_grp = (RadioGroup) findViewById((R.id.rdGrp_prog));

                        RadioButton selected = (RadioButton) findViewById(rd_grp.getCheckedRadioButtonId());
                        String value = selected.getText().toString();
                        Log.d("demo", "Selected:" + value);
                        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                        //intent.putExtra("NAME", name);
                        // intent.putExtra("EMAIL", email);
                        //intent.putExtra("SUBJECT", value);
                        Student student = new Student(name, email, value);
                        intent.putExtra(STUDENT_KEY, student);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Enter correct Email ID", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }
}

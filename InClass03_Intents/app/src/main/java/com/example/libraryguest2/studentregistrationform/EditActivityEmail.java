package com.example.libraryguest2.studentregistrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditActivityEmail extends AppCompatActivity {

    final static String STUDENT_KEY_NEW_SAVE1="forNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_activity_email);

        EditText et = (EditText)findViewById(R.id.txt_email_save);
        final Student student=(Student)getIntent().getExtras().getSerializable(DisplayActivity.STUDENT_KEY_NEW);

        et.setText(student.getEmail());
        Log.d("name of",student.getName());
        findViewById(R.id.btn_save_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.txt_email_save);
                Intent intent=new Intent();
                String nameUp=et.getText().toString();
                Log.d("name of",nameUp);
                student.setEmail(nameUp);
                Log.d("namof",student.getName());
                intent.putExtra(STUDENT_KEY_NEW_SAVE1,student);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}

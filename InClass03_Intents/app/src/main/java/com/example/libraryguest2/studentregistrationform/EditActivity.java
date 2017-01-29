package com.example.libraryguest2.studentregistrationform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    final static String STUDENT_KEY_NEW_SAVE2="forNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final Student student=(Student)getIntent().getExtras().getSerializable(DisplayActivity.STUDENT_KEY_NEW);
        findViewById(R.id.btn_save_lang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RadioGroup rdGrp_edit = (RadioGroup) findViewById((R.id.rdGrp_edit));
                RadioButton selected = (RadioButton) findViewById(rdGrp_edit.getCheckedRadioButtonId());
                String value = selected.getText().toString();
                Log.d("demo","Selected:"+value);
                student.setLanguage(value);
                Intent intent=new Intent();
                //Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                //intent.putExtra("NAME", name);
                // intent.putExtra("EMAIL", email);
                //intent.putExtra("SUBJECT", value);
                intent.putExtra(STUDENT_KEY_NEW_SAVE2,student);
                setResult(RESULT_OK,intent);
                finish();

            }
        });


    }
}

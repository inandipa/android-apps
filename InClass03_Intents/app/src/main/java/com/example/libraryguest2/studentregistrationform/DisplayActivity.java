package com.example.libraryguest2.studentregistrationform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends Activity {

    final static String STUDENT_KEY_NEW="forNAME";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        final Student student=(Student)getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY);
        //String name=getIntent().getExtras().getString("NAME");
        Log.d("test", "test1");
        Log.d("demo",student.toString());
        Log.d("demo",student.getLanguage());

        TextView txt_name=(TextView) findViewById(R.id.textViewNameValue);
        txt_name.setText(student.getName());
        TextView txt_email=(TextView) findViewById(R.id.textViewEmailValue);
        txt_email.setText(student.getEmail());
        TextView txt_lang=(TextView) findViewById((R.id.textViewSubjectValue));
        txt_lang.setText(student.getLanguage());

        findViewById(R.id.imageViewName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.libraryguest2.studentregistrationform.intent.action.VIEWName");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(STUDENT_KEY_NEW,student);
                startActivityForResult(intent,100);




            }
        });

        findViewById(R.id.imageViewEmail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.libraryguest2.studentregistrationform.intent.action.VIEWEmail");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(STUDENT_KEY_NEW,student);
                startActivityForResult(intent,200);

            }
        });

        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.libraryguest2.studentregistrationform.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(STUDENT_KEY_NEW,student);
                startActivityForResult(intent,300);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100)
        {
            Log.d("demo","onactivirtryu");
            Student student=(Student)data.getExtras().getSerializable(EditActivityName.STUDENT_KEY_NEW_SAVE);
            Log.d("demo",student.getName());
            TextView txt_name=(TextView) findViewById(R.id.textViewNameValue);
            txt_name.setText(student.getName());
            TextView txt_email=(TextView) findViewById(R.id.textViewEmailValue);
            txt_email.setText(student.getEmail());
            TextView txt_lang=(TextView) findViewById((R.id.textViewSubjectValue));
            txt_lang.setText(student.getLanguage());



        }
        if(requestCode==200)
        {
            Log.d("demo","onactivirtryu");
            Student student=(Student)data.getExtras().getSerializable(EditActivityEmail.STUDENT_KEY_NEW_SAVE1);
            Log.d("demo",student.getName());
            TextView txt_name=(TextView) findViewById(R.id.textViewNameValue);
            txt_name.setText(student.getName());
            TextView txt_email=(TextView) findViewById(R.id.textViewEmailValue);
            txt_email.setText(student.getEmail());
            TextView txt_lang=(TextView) findViewById((R.id.textViewSubjectValue));
            txt_lang.setText(student.getLanguage());
        }

        if(requestCode==300)
        {
            Log.d("demo","onactivirtryu");
            Student student=(Student)data.getExtras().getSerializable(EditActivity.STUDENT_KEY_NEW_SAVE2);
            Log.d("demo",student.getName());
            TextView txt_name=(TextView) findViewById(R.id.textViewNameValue);
            txt_name.setText(student.getName());
            TextView txt_email=(TextView) findViewById(R.id.textViewEmailValue);
            txt_email.setText(student.getEmail());
            TextView txt_lang=(TextView) findViewById((R.id.textViewSubjectValue));
            txt_lang.setText(student.getLanguage());
        }

    }
}

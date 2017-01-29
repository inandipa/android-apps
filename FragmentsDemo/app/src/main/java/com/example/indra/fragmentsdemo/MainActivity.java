package com.example.indra.fragmentsdemo;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements a.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new a() ,"frag")
                .commit();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new a() ,"frag1")
                .commit();
        RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               a f =  (a)getSupportFragmentManager().findFragmentByTag("frag");
                a f1 =  (a)getSupportFragmentManager().
                        findFragmentByTag("frag1");

                if(checkedId == R.id.radioButton){

                    f.changeColor(Color.GREEN);
                    f1.changeColor(Color.GREEN);
                }else  if(checkedId == R.id.radioButton2){

                    f.changeColor(Color.BLUE);
                    f1.changeColor(Color.BLUE);
                }else  if(checkedId == R.id.radioButton3){
                    f.changeColor(Color.GRAY);

                    f1.changeColor(Color.GRAY);

                }

            }
        });

    }

    @Override
    public void onFragmentInteraction() {
        TextView tv = (TextView)findViewById(R.id.textView);


    }
}

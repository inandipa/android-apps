package com.example.indra.pizzastore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        ArrayList<Integer> selected_toppings =getIntent().getExtras().getIntegerArrayList("toppings");
        boolean delivery = getIntent().getExtras().getBoolean("Delivery");
        Log.d("demo",""+delivery);
        String list="";
        TextView textView;
        Double s= 0.0;
        if(selected_toppings!=null) {
            if (selected_toppings.size() != 0) {
                 textView = (TextView) findViewById(R.id.textViewtoppings_items);
                for (int i = 0; i < selected_toppings.size(); i++) {
                    list = list + MainActivity.toppings[selected_toppings.get(i)] + ",";
                }
                list = list.substring(0, list.length()-1);
                textView.setText(list);
            }

            s = (selected_toppings.size() * 1.5);
            textView = (TextView) findViewById(R.id.textViewtopping_cost);
            String value = String.valueOf(s)+"$";
                    textView.setText( value);

        }
        if(delivery){
            textView = (TextView) findViewById(R.id.textViewdelivery_cost);
            textView.setText("2$");
            s = s + 6.5+2;
        }
        else{
            textView = (TextView) findViewById(R.id.textViewdelivery);
            textView.setVisibility(View.GONE);
            s = s+6.5;
        }
        textView = (TextView) findViewById(R.id.textViewtotal_cost);
        SpannableString content = new SpannableString(s+"$");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(OrderActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }
}

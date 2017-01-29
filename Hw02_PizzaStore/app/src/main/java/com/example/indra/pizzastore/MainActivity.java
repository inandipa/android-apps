package com.example.indra.pizzastore;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity implements ImageView.OnClickListener {

    public static final CharSequence[] toppings = {"Bacon","Cheese","Garlic","Green Pepper","Mushroom","Olives","Onions","Red Pepper"};
    int[] drawables_id ={ R.drawable.bacon,R.drawable.cheese,R.drawable.garlic,R.drawable.green_pepper,R.drawable.mushroom,R.drawable.olives,R.drawable.onion,R.drawable.red_pepper};
    ArrayList<Integer> selected_items = new ArrayList<>();
    String items = "";

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(10);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose a topping");
        builder.setItems(toppings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("demo", (String) toppings[which]);
                if(selected_items.size()<10){
                    selected_items.add(which);
                    show_toppings();
                }
                else{
                    Toast.makeText(MainActivity.this,"Max capacity reached",Toast.LENGTH_LONG).show();
                }
            }
        });
        final AlertDialog alertDialog = builder.create();
        findViewById(R.id.buttonAddtoppings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected_items.size()<10) {
                    alertDialog.show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Max capacity reached",Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.buttonclear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_items.clear();
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxDelivery);
                checkBox.setChecked(false);
                checkBox = (CheckBox) findViewById(R.id.checkBox);
                checkBox.setChecked(false);
                show_toppings();
            }
        });

        findViewById(R.id.buttonCheckout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxDelivery);
                ConvertStringSet(checkBox.isChecked());
                Intent intent = new Intent(MainActivity.this,OrderActivity.class);
                intent.putExtra("toppings",selected_items);
                intent.putExtra("Delivery",checkBox.isChecked());
                startActivity(intent);

            }
        });
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){

                    SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
                    if(sharedPref.getBoolean(getString(R.string.is_saved),false)){
                        selected_items = new ArrayList<>();
                        items = sharedPref.getString(getString(R.string.saved_items), "");
                        Log.d("demo",items);
                        while(items.length()!=0){
                            char c = items.charAt(0);
                            items= items.substring(1);
                            selected_items.add(Character.getNumericValue(c));
                        }
                        show_toppings();
                        if(sharedPref.getBoolean(getString(R.string.isDelivery),false)) {

                            CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxDelivery);
                            checkBox.setChecked(true);
                        }

                    }else{
                        Toast.makeText(MainActivity.this,"Nothing saved",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });

    }

    private void ConvertStringSet(Boolean d) {
        items="";
        for(int i = 0; i<selected_items.size();i++){
            items = items.concat(String.valueOf(selected_items.get(i)));
        }

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_items),items);
        editor.putBoolean(getString(R.string.is_saved),true);
        editor.putBoolean(getString(R.string.isDelivery),d);
        editor.apply();
        Log.d("demo",items);
    }

    public void show_toppings(){

        LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.row1_id);
        LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.row2_id);
        linearLayout1.removeAllViews();
        linearLayout2.removeAllViews();
        progressBar.setProgress(selected_items.size());
        for(int i=0;i<selected_items.size();i++){

            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(drawables_id[selected_items.get(i)]);
            imageView.setClickable(true);

            final float scale = MainActivity.this.getResources().getDisplayMetrics().density;
            int pixels = (int) (50 * scale + 0.5f);
            LinearLayout.LayoutParams imageparams = new LinearLayout.LayoutParams(pixels, ViewGroup.LayoutParams.MATCH_PARENT);
            pixels = (int) (2 * scale + 0.5f);
            imageparams.setMargins(pixels,pixels,pixels,pixels);
            imageView.setLayoutParams(imageparams);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if(i<5){
                imageView.setTag(i);
               linearLayout1.addView(imageView);
                imageView.setOnClickListener(MainActivity.this);
            }
            else {
                imageView.setTag(i);
                linearLayout2.addView(imageView);
                imageView.setOnClickListener(MainActivity.this);
            }

        }
    }

    @Override
    public void onClick(View v) {
        selected_items.remove((int)(v.getTag()));
        show_toppings();
    }

}

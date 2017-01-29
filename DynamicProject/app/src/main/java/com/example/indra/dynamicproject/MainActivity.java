package com.example.indra.dynamicproject;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        RelativeLayout rl = new RelativeLayout(this);
        rl.setLayoutParams( new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(rl);

        TextView tv = new TextView(this);
        tv.setText(R.string.text);
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setId(R.id.view);
        tv.setLayoutParams( new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rl.addView(tv);

        Button btn = new Button(this);
        btn.setText(R.string.text);
        RelativeLayout.LayoutParams rl_parm = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rl_parm.addRule(RelativeLayout.BELOW,tv.getId());
        btn.setLayoutParams(rl_parm);
        rl.addView(btn);

        
    }
}

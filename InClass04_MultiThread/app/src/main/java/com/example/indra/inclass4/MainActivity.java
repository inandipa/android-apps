package com.example.indra.inclass4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    int select;
    Handler handler;
    boolean number;
    boolean uppercase;
     boolean lowercase;
    boolean special;
    ProgressDialog pd;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d("demo",""+uppercase);


        Button btn = (Button)findViewById(R.id.buttonAsync);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox num=(CheckBox)findViewById(R.id.cb_num);
                CheckBox upcase=(CheckBox)findViewById(R.id.cb_upcase);
                CheckBox lcase=(CheckBox)findViewById(R.id.cb_lcase);
                CheckBox sc=(CheckBox)findViewById(R.id.cb_sc);
                Spinner spinner=(Spinner)findViewById(R.id.spinner);
                String selectedValue=spinner.getSelectedItem().toString();
                if(selectedValue.equals("8-12"))
                     select=0;
                else if(selectedValue.equals("13-17"))
                    select=1;
                 else if(selectedValue.equals("18-22"))
                    select=2;
                else{
                    select=3;
                }
                number=num.isChecked();
                uppercase=upcase.isChecked();
                lowercase=lcase.isChecked();
                special=sc.isChecked();
                int num1=0,num2=0,num3=0,num4=0;
                if(number) {
                     num1 = 1;
                }
                if(uppercase) {
                    num2 = 1;
                }
                if(lowercase) {
                    num3 = 1;
                }
                if(special) {
                    num4 = 1;
                }
                if(select == 3 ){
                    Toast.makeText(MainActivity.this,"Select password length",Toast.LENGTH_LONG).show();
                    TextView tv = (TextView)findViewById(R.id.textView);
                    tv.setText("");

                }
                else if(!(number || uppercase || lowercase || special) ){
                    Toast.makeText(MainActivity.this,"Select atleast one check box",Toast.LENGTH_LONG).show();
                    TextView tv = (TextView)findViewById(R.id.textView);
                    tv.setText("");
                }
                else
               new Dowork().execute(select,num1,num2,num3,num4);
            }
        });


        findViewById(R.id.buttonThread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner=(Spinner)findViewById(R.id.spinner);
                String selectedValue=spinner.getSelectedItem().toString();
                if(selectedValue.equals("8-12"))
                    select=0;
                else if(selectedValue.equals("13-17"))
                    select=1;
                else if(selectedValue.equals("18-22"))
                    select=2;
                else{
                    select=3;
                }
                Log.d("demo",String.valueOf(select));
                CheckBox num=(CheckBox)findViewById(R.id.cb_num);
                CheckBox upcase=(CheckBox)findViewById(R.id.cb_upcase);
                CheckBox lcase=(CheckBox)findViewById(R.id.cb_lcase);
                CheckBox sc=(CheckBox)findViewById(R.id.cb_sc);

                number=num.isChecked();
                uppercase=upcase.isChecked();
                lowercase=lcase.isChecked();
                special=sc.isChecked();

                 if(select == 3 ){
                    Toast.makeText(MainActivity.this,"Select password length",Toast.LENGTH_LONG).show();
                     TextView tv = (TextView)findViewById(R.id.textView);
                     tv.setText("");
                }
                else if(!(number || uppercase || lowercase || special) ){
                    Toast.makeText(MainActivity.this,"Select atleast one check box",Toast.LENGTH_LONG).show();
                     TextView tv = (TextView)findViewById(R.id.textView);
                     tv.setText("");
                }
                else {
                    Thread thread = new Thread(new Worker());
                    thread.start();
                }

                Log.d("demo",number+" "+uppercase+" "+lowercase+" "+special);

                handler=new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        if(msg.getData().containsKey("start"))
                        {

                            pd=new ProgressDialog(MainActivity.this);
                            pd.setMessage("Generating Passwords");
                            pd.setCancelable(false);
                            pd.show();

                        }
                        if(msg.getData().containsKey("end"))
                        {
                            TextView txt_rslt=(TextView) findViewById(R.id.textView);
                            txt_rslt.setText(msg.getData().getString("end"));
                            pd.dismiss();
                        }



                        return false;
                    }
                });
            }
        });

    }

   class Dowork extends AsyncTask<Integer,Integer,String>{

       @Override
       protected void onPostExecute(String s) {
           progressDialog.dismiss();
           super.onPostExecute(s);
           TextView tv = (TextView)findViewById(R.id.textView);
           tv.setText(s);
       }

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
           progressDialog =new ProgressDialog(MainActivity.this);
           progressDialog.setMessage("Generating Password");
           progressDialog.setCancelable(false);
           progressDialog.show();

       }

       @Override
       protected String doInBackground(Integer... params) {
          boolean param1,param2,param3,param4;
           param1=false; param2=false; param3 = false; param4 =false;
           if(params[1]==1){
                param1 =true;
           }
           if(params[2]==1){
               param2 =true;
           }
           if(params[3]==1){
               param3 =true;
           }
           if(params[4]==1){
               param4 =true;
           }
           return Util.getPassword(params[0],param1,param2,param3,param4);
       }
   }
    public class Worker implements Runnable {
        @Override
        public void run() {

            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("start", "Thread started");
            message.setData(bundle);
            handler.sendMessage(message);

            String password = Util.getPassword(select, number, uppercase, lowercase, special);
            Log.d("demo", password);

            Message messageF = new Message();
            Bundle bundleF = new Bundle();
            bundleF.putString("end", password);
            messageF.setData(bundleF);
            handler.sendMessage(messageF);


        }
    }
}

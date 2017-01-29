package com.example.indra.threadsdemo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends Activity {


   // ExecutorService threadpool;

    android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Computing");
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        handler = new android.os.Handler(new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                if(msg.what==1){
                    progressDialog.show();
                    Log.d("msg","start");
                }
                else if(msg.what==2){
                    progressDialog.dismiss();
                    Log.d("msg","PROGRESS");
                }
                else if(msg.what==4){
                    progressDialog.setProgress((Integer)msg.obj);

                    Log.d("msg","done");
                }

                return false;
            }
        });



       // threadpool = Executors.newFixedThreadPool(3);
       findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // threadpool.execute(new DoWork());
               Thread thread = new Thread( new DoWork());
               thread.start();
            }
        });

    }

   class DoWork implements Runnable{

       @Override
       public void run() {
           Message msg = new Message();
           msg.what=1;
           handler.sendMessage(msg);
           for(int i=0;i<100;i++){
               for(int j=0;j<100000000;j++){
                j++;
               }
               Log.d("msg","pro"+i);
               msg = new Message();
               msg.what=4;
               msg.obj=i+1;
               handler.sendMessage(msg);

           }
           msg = new Message();
           msg.what=2;
           handler.sendMessage(msg);
       }
   }
}

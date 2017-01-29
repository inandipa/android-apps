package com.example.indra.appsactivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetXml.IData{

    static ArrayList<AppDetails> ApplistAll;
    DatabaseManager dm;
    ListView listView;
    int star_off = R.drawable.grey;
    int getStar_on = R.drawable.yellow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseManager(this);


        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml";

            new GetXml(this).execute(url);

        }
        else{
            Toast.makeText(MainActivity.this, "NO Network connection", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void getData(ArrayList<AppDetails> result) {
      ApplistAll = result;

        List<AppDetails> notes = dm.getAll();
        ArrayList<AppDetails> list = new ArrayList<>();

        list.addAll(notes);
       for(int i=0; i<list.size();i++){
           for (int j=0;j<ApplistAll.size();j++){
               if(list.get(i).getApp_Title().equals(ApplistAll.get(j).getApp_Title())){
                   ApplistAll.get(j).setFav(true);
               }
           }
       }
        ShowDisplay(ApplistAll);

    }


    public void ShowDisplay(final ArrayList<AppDetails> result){
        AppAdapter adapter = new AppAdapter(this,R.layout.row,result);
        listView = (ListView) findViewById(R.id.listView);
        if(listView!=null) {
            listView.setAdapter(adapter);
        }
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", (result.get(position)).toString());
                Intent intent = new Intent(MainActivity.this, Preview_Activity.class);
                intent.putExtra("Data",result.get(position));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView =(ImageView)view.findViewById(R.id.star);

                if(result.get(position).isFav()){
                    imageView.setImageResource(star_off);
                    result.get(position).setFav(false);
                    for(int i = 0;i <ApplistAll.size();i++){
                       if( ApplistAll.get(i).returnAppdetails(result.get(position).App_Title)!=null){
                           ApplistAll.get(i).returnAppdetails(result.get(position).App_Title).setFav(false);
                       }
                    }
                    dm.delete(result.get(position));
                    Log.d("demo1",result.get(position).App_Title);
                    Log.d("demo1",position+"");


                }else{
                    imageView.setImageResource(getStar_on);
                    result.get(position).setFav(true);

                    for(int i = 0;i <ApplistAll.size();i++){
                        if( ApplistAll.get(i).returnAppdetails(result.get(position).App_Title)!=null){
                            ApplistAll.get(i).returnAppdetails(result.get(position).App_Title).setFav(true);
                        }
                    }

                    dm.save(result.get(position));
                    Log.d("demo1",result.get(position).App_Title);
                    Log.d("demo1",position+"");
                }


                Log.d("demo", (result.get(position)).toString());
                return true;
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                Toast.makeText(this, "Showing Favorites", Toast.LENGTH_SHORT).show();
                List<AppDetails> notes = dm.getAll();
                ArrayList<AppDetails> list = new ArrayList<>();

                list.addAll(notes);
                for(int i = 0;i <list.size();i++){
                    list.get(i).setFav(true);
                }
                Log.d("demo1",notes.toString());
               ShowDisplay(list);
                break;
            case R.id.option2:
                Toast.makeText(this, "Showing all apps", Toast.LENGTH_SHORT).show();
                ShowDisplay(ApplistAll);
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        dm.close();
    }



}
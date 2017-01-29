package com.example.indra.fcm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import com.estimote.sdk.Region;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.SystemRequirementsChecker;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static String regions = "region";
    private BeaconManager beaconManager;
    private Region region;
    TextView tv;
    ProductAdapter adapter;
    ListView listView;
    static ArrayList<Data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String DeviceId = FirebaseInstanceId.getInstance().getToken();
        tv = (TextView) findViewById(R.id.textView);
        adapter = new ProductAdapter(this,R.layout.row_layout,list);
        listView = (ListView) findViewById(R.id.listView);
        region = new Region("ranged region",
                UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);

        beaconManager = new BeaconManager(this);
// add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if (!list.isEmpty()) {

                    Beacon nearestBeacon = list.get(0);
                    //  List<String> places = placesNearBeacon(nearestBeacon);
                    // TODO: update the UI here
                    Log.d("Airport", "Nearest places: " + nearestBeacon.getMajor());
                    if(nearestBeacon.getMajor() == 15212 && !regions.equals("grocery")){
                        regions="grocery";
                        String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/becon_notification/api.php?region=grocery&id="+DeviceId;
                        new GetJson(MainActivity.this).execute(url);
                    }else if(nearestBeacon.getMajor() == 48071 && !regions.equals("lifestyle")){
                        regions="lifestyle";
                        String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/becon_notification/api.php?region=lifestyle&id="+DeviceId;
                        new GetJson(MainActivity.this).execute(url);
                    }else if(nearestBeacon.getMajor() == 45153 && !regions.equals("produce")){
                        regions="produce";
                        String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/becon_notification/api.php?region=produce&id="+DeviceId;
                        new GetJson(MainActivity.this).execute(url);

                    }

                    tv.setText(regions);

                }else if(!regions.equals("All discounts available")) {
                    regions="All discounts available";
                    String url = "http://madserver-env.us-west-2.elasticbeanstalk.com/Mad_server/becon_notification/api.php?region=All&id="+DeviceId;
                    new GetJson(MainActivity.this).execute(url);
                }

            }
        });


    }
    public void action_response(ArrayList<Data> data){
        list = data;
        adapter = new ProductAdapter(this,R.layout.row_layout,list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });

        listView.setAdapter(adapter);


    }

    @Override
    protected void onPause() {
        beaconManager.stopRanging(region);

        super.onPause();
    }
}

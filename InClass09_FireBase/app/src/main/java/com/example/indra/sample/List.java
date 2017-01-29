package com.example.indra.sample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class List extends Fragment implements GetXml.IData {

    private OnDataListener mListener;
    static private ArrayList<AppDetails> list;
    private Context context;
    String uid;
    ListView listView;
    int star_off = R.drawable.grey;
    int getStar_on = R.drawable.yellow;
    static private ArrayList<AppDetails> Final25 = null;
    boolean choice =true;


    public List() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/xml";
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                uid= user.getUid();

            }
            MainActivity.firebaseRoot.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    Log.d("demo","called listner");
                    list = new ArrayList<>();
                    Log.e("Count " ,""+snapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        AppDetails appDetails = postSnapshot.getValue(AppDetails.class);
                        list.add(appDetails);
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    Log.e("The read failed: " ,firebaseError.getMessage());
                }
            });


            new GetXml(context, this).execute(url);


        }
        else{
            Toast.makeText(getActivity(), "NO Network connection", Toast.LENGTH_SHORT).show();
        }


    }

    public void display(){
        Log.d("demo","called display");
        for(int j=0;j<Final25.size();j++){
            Final25.get(j).setFav(false);
        }
       for(int i = 0; i<list.size();i++){
           for(int j=0;j<Final25.size();j++){
               if(list.get(i).getId().equals(Final25.get(j).getId())){
                   Final25.get(j).setFav(true);
               }
           }
       }
        final ArrayList<AppDetails> result;
        if(choice){
            result = Final25;
          }else {
            result = list;
        }

        AppAdapter adapter = new AppAdapter(context,R.layout.row,result);
        listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", (result.get(position)).toString());
                mListener.OnData(result.get(position));

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView imageView =(ImageView)view.findViewById(R.id.star);

                if(result.get(position).isFav()){
                    imageView.setImageResource(star_off);
                    result.get(position).setFav(false);
                    //dm.delete(result.get(position));
                    MainActivity.firebaseRoot.child(uid).child(result.get(position).getId()).removeValue();
                }else{
                    imageView.setImageResource(getStar_on);
                    result.get(position).setFav(true);
                MainActivity.firebaseRoot.child(uid).child(result.get(position).getId()).setValue(Final25.get(position));

                }
                return true;
            }
        });

    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mListener = (OnDataListener) context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void getData(final ArrayList<AppDetails> result) {
        Final25 = result;
        display();

    }


    public interface OnDataListener {
        void OnData(AppDetails appDetails);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
         inflater =getActivity().getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option1:
                Toast.makeText(context, "Showing Favorites", Toast.LENGTH_SHORT).show();
                choice= false;
                display();
                break;
            case R.id.option2:
                Toast.makeText(context, "Showing all apps", Toast.LENGTH_SHORT).show();
                choice = true;
                display();
                break;
        }
        return true;
    }


}

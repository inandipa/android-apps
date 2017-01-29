package com.example.indra.appactivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class List extends Fragment implements GetXml.IData {

    private OnDataListener mListener;
    private Context context;


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

        if(iSConnectedOnline()) {
            Log.d("demo", "success");
            String url = "https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml";

            new GetXml(this).execute(url);

        }
        else{
            Toast.makeText(getActivity(), "NO Network connection", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean iSConnectedOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;

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

        for(int i = 0;i<result.size();i++) {
            Log.d("demo", result.get(i).toString());

        }
        AppAdapter adapter = new AppAdapter(context,R.layout.row,result);
        ListView listView = (ListView) getActivity().findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo", (result.get(position)).toString());
                mListener.OnData(result.get(position));

            }
        });


    }


    public interface OnDataListener {
        // TODO: Update argument type and name
        void OnData( AppDetails appDetails);
    }
}

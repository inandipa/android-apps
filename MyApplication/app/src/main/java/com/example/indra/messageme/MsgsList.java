package com.example.indra.messageme;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class MsgsList extends Fragment implements GetMsgs.IData {

    private OnDataListener mListener;
    FirebaseAuth mAuth;
    private Context context;
    String uid;
    ArrayList<MessageDetails> messageDetailses;
    ListView listView;



    public MsgsList() {
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
            Log.d("demo1", "success");
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                uid= user.getUid();

            }

            messageDetailses = new ArrayList<>();
            final AppAdapter adapter = new AppAdapter(context,R.layout.row,messageDetailses);
            listView = (ListView) getView().findViewById(R.id.listView);
            //listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("demo", (messageDetailses.get(position)).toString());
                    mListener.OnMsgRead(messageDetailses.get(position));
                }
            });



            MainActivity.firebaseRoot.child("users").child(uid).child("messages").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MessageDetails messageDetails =dataSnapshot.getValue(MessageDetails.class);
                    messageDetails.setKey(dataSnapshot.getKey());
                    messageDetailses.add(messageDetails);
                    Log.d("demo","called firebasse");
                   // Log.d("demo",messageDetails.toString());
                    //Log.d("demo",dataSnapshot.getKey());
                    adapter.notifyDataSetChanged();


                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
            listView.setAdapter(adapter);

        }
        else{
            Toast.makeText(getActivity(), "NO Network connection", Toast.LENGTH_SHORT).show();
        }


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
    public void GetData(ArrayList<MessageDetails> result) {

    }

    public interface OnDataListener {
        void OnNewMsg();
        void OnMsgRead(MessageDetails appDetails);
        void reload();
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
            case R.id.NewMsg:
                Toast.makeText(context, "create message", Toast.LENGTH_SHORT).show();
                mListener.OnNewMsg();
                break;
            case R.id.sync:
                Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                mListener.reload();
                break;
        }
        return true;
    }


}



/*

        });*/

/*
         FirebaseListAdapter<MessageDetails> adapter = new FirebaseListAdapter<MessageDetails>(getActivity(),MessageDetails.class,R.layout.row,MainActivity.firebaseRoot.child("users").child(SignUp.UserID).child("messages")) {
                @Override
                protected void populateView(View view, final MessageDetails messageDetails, int i) {
                    TextView txt_name = (TextView) view.findViewById(R.id.textViewName);
                    txt_name.setText(messageDetails.getSender());
                    TextView txt_time = (TextView) view.findViewById(R.id.textViewtime);
                    txt_time.setText(messageDetails.getMessage());
                    ImageView img_star =(ImageView) view.findViewById(R.id.imageView);
                    if(messageDetails.isRead()) {
                        img_star.setImageResource( Resources.getSystem().getIdentifier("radiobutton_off_background", "drawable", "android"));
                    }else{
                        img_star.setImageResource(Resources.getSystem().getIdentifier("radiobutton_on_background", "drawable", "android"));
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.OnMsgRead(messageDetails);
                        }
                    });

                }
            };
            listView.setAdapter(adapter);

        */


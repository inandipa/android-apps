package com.example.indra.messageme;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ServerValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;


public class WriteMsg extends Fragment {
       private OnFragmentInteractionListener mListener;
    ArrayList<String> RegisteredUserId;
    ArrayList<User>RegisteredUsers;
    ArrayList<String>RegisteredUsesName;
    String ReceiverID;
    AlertDialog alertDialog;
    String Receiver="";
    MessageDetails messageDetails;
    FirebaseAuth mAuth;

    public WriteMsg() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        messageDetails = (MessageDetails) bundle.getSerializable("Data");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_write_msg, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth =FirebaseAuth.getInstance();

        if(messageDetails!=null){
            TextView tv = (TextView) getView().findViewById(R.id.Name);
            tv.setText(messageDetails.getSender());
            Receiver= messageDetails.getSender();
            ReceiverID =messageDetails.getSenderid();
        }
        RegisteredUsers = new ArrayList<>();
        RegisteredUserId= new ArrayList<>();
        RegisteredUsesName= new ArrayList<>();


        MainActivity.firebaseRoot.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user =dataSnapshot.child("profile").getValue(User.class);
                Log.d("demo",user.toString());
                user.setUid(dataSnapshot.getKey());
                RegisteredUserId.add(user.getUid());
                RegisteredUsers.add(user);
                RegisteredUsesName.add(user.getUserId());
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




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose on from users");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1,RegisteredUsesName);
        arrayAdapter.setNotifyOnChange(true);
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("demo",RegisteredUserId.get(which));
                TextView textView = (TextView) getView().findViewById(R.id.Name);
                textView.setText(RegisteredUsesName.get(which));
                Receiver = RegisteredUsesName.get(which);
                ReceiverID = RegisteredUsers.get(which).getUid();


            }
        });

        alertDialog = builder.create();

        ImageView imageView = (ImageView) getView().findViewById(R.id.contacts);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();

            }
        });

        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) getView().findViewById(R.id.editText);
                String s =editText.getText().toString();
                if(Receiver.equals("")|| s.equals("")){
                    Toast.makeText(getActivity(),"select receiver and enter text",Toast.LENGTH_LONG).show();
                }else{
                    MessageDetails messageDetails = new MessageDetails();
                    messageDetails.setMessage(s);
                    FirebaseUser user =mAuth.getCurrentUser();
                    int index = user.getEmail().lastIndexOf("@");
                    String id = user.getEmail().substring(0,index);
                    messageDetails.setSender(id);
                    messageDetails.setRead(false);
                    messageDetails.setSenderid(user.getUid());
                    MainActivity.firebaseRoot.child("users").child(ReceiverID).child("messages").push().setValue(messageDetails);
                    Toast.makeText(getActivity(),"sent",Toast.LENGTH_LONG).show();
                }

            }
        });




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

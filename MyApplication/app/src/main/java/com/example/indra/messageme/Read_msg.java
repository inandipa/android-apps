package com.example.indra.messageme;

import android.content.Context;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class Read_msg extends Fragment {


    private OnRead_msg_Listner mListener;
    Context context;
    MessageDetails messageDetails;
    FirebaseAuth mAuth;

    public Read_msg() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        messageDetails = (MessageDetails) bundle.getSerializable("Data");
        Log.d("demo11",messageDetails.toString());



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_msg, container, false);
         }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = (TextView) getView().findViewById(R.id.Sender);
        tv.setText(messageDetails.getSender());
        tv = (TextView) getView().findViewById(R.id.Message);
        tv.setText(messageDetails.getMessage());
        messageDetails.setRead(true);
        mAuth = FirebaseAuth.getInstance();
        MainActivity.firebaseRoot.child("users").child(mAuth.getCurrentUser().getUid()).child("messages").child(messageDetails.getKey()).setValue(messageDetails);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRead_msg_Listner) {
            this.context = context;
            mListener = (OnRead_msg_Listner) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnRead_msg_Listner {
        // TODO: Update argument type and name
       void reply(MessageDetails messageDetails);
        void delete ();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater =getActivity().getMenuInflater();
        inflater.inflate(R.menu.read_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Reply:
                Toast.makeText(context, "Reply to "+ messageDetails.getSender(), Toast.LENGTH_SHORT).show();
                mListener.reply(messageDetails);
                break;
            case R.id.Delete:
                Toast.makeText(context, "Deleting....", Toast.LENGTH_SHORT).show();
                MainActivity.firebaseRoot.child("users").child(mAuth.getCurrentUser().getUid()).child("messages").child(messageDetails.getKey()).removeValue();

                mListener.delete();
                break;
        }
        return true;
    }
}

package com.example.indra.appactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Fragment {
        private OnFragmentInteractionListener mListener;
    private String User,pass;

    public Login() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)getView().findViewById(R.id.editTextUser);
                User = editText.getText().toString();
                editText = (EditText)getView().findViewById(R.id.editTextPass);
                pass = editText.getText().toString();

                if(User.equals("admin") && pass.equals("test123")){

                    mListener.onFragmentInteraction();

                }
                else if(User.equals("admin") && !pass.equals("test123")){
                    Toast.makeText(getActivity(),"Invalid Password",Toast.LENGTH_LONG).show();
                }
                else if(!User.equals("admin") && !pass.equals("test123")){
                    Toast.makeText(getActivity(),"Invalid  User and Password",Toast.LENGTH_LONG).show();
                }
                else if(!User.equals("admin") && pass.equals("test123")){
                    Toast.makeText(getActivity(),"Invalid  User ",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }
}

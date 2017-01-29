package com.example.indra.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Fragment {
        private LoginListner mListener;
    private String name,pass;
    private FirebaseAuth mAuth;


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
        mListener = (LoginListner) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        getView().findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText)getView().findViewById(R.id.editTextUser);
                name = editText.getText().toString();
                editText = (EditText)getView().findViewById(R.id.editTextPass);
                pass = editText.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                if(!name.equals("") || !pass.equals("")) {
                    mAuth.signInWithEmailAndPassword(name, pass)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Log.w("demo1", "signInWithEmail", task.getException());
                                        Toast.makeText(getActivity(), "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("demo1", "login Sucessfull");
                                        mListener.onFragmentInteraction(1);

                                    }
                                }
                            });


                }else {
                    Toast.makeText(getActivity(),"Enter Proper Inputs",Toast.LENGTH_LONG).show();
                }
            }

        });


        getView().findViewById(R.id.buttonSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(2);
            }
        });
    }

    public interface LoginListner {
        // TODO: Update argument type and name
        void onFragmentInteraction(int i);
    }
}

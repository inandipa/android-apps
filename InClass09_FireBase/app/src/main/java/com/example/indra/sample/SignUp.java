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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends Fragment {

    private FirebaseAuth mAuth;
    private OnFragmentInteractionListener mListener;
    EditText fullName,email,password;

    public SignUp() {
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
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_register=(Button)getView().findViewById(R.id.btn_signup);
        btn_register.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                fullName=(EditText)getView().findViewById(R.id.editTextUser);
                                                email=(EditText)getView().findViewById(R.id.editTextUserEmail);
                                                password=(EditText)getView().findViewById(R.id.editTextPass);
                                                mAuth = FirebaseAuth.getInstance();

                                                if(!email.getText().toString().equals("") || !password.getText().toString().equals("")) {
                                                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                                    Log.d("demo", "createUserWithEmail:onComplete:" + task.isSuccessful());


                                                                    if (!task.isSuccessful()) {
                                                                        Log.d("demo", task.getException().toString());
                                                                        Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_LONG).show();
                                                                        Log.d("demo", "not done");

                                                                    } else {
                                                                        mListener.cancel();
                                                                    }
                                                                }
                                                            });

                                                }else {
                                                    Toast.makeText(getActivity(),"Enter Proper Inputs",Toast.LENGTH_LONG).show();

                                                }
                                            }


                                        });


        Button btn_cancel = (Button) getView().findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.cancel();
            }
        });


    }



    public interface OnFragmentInteractionListener {
        void cancel();
    }
}

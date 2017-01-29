package com.example.indra.messageme;

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

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends Fragment {

    private FirebaseAuth mAuth;
    private SignUpListner mListener;
    EditText firstName,lastName,UserName,re_password,password;
    public static String UserID;


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
        mListener = (SignUpListner) context;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn_register=(Button)getView().findViewById(R.id.btn_signup);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = (EditText) getView().findViewById(R.id.editTextFirstName);
                lastName = (EditText) getView().findViewById(R.id.editTextLastName);
                UserName = (EditText) getView().findViewById(R.id.editTextUserName);
                password = (EditText) getView().findViewById(R.id.editTextPassword);
                re_password = (EditText) getView().findViewById(R.id.editTextRePassword);
                UserID = UserName.getText().toString();
                final String user1 = UserID;
                UserID = UserID.concat("@msgme.com");
                mAuth = FirebaseAuth.getInstance();
                if(!re_password.getText().toString().equals(password.getText().toString())){
                    Toast.makeText(getActivity(),"Passwords don't match",Toast.LENGTH_LONG).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(UserID,password.getText().toString())
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Log.d("demo", task.getException().toString());
                                        Toast.makeText(getActivity(), task.getException().toString(), Toast.LENGTH_LONG).show();
                                    } else {
                                        mAuth.signInWithEmailAndPassword(UserID,password.getText().toString());

                                        String id =  mAuth.getCurrentUser().getUid();
                                        Log.d("demo",id);
                                        User user = new User();
                                        user.setFirstName(firstName.getText().toString());
                                        user.setLastName(lastName.getText().toString());
                                        user.setUserId(UserName.getText().toString());
                                        user.setPassword(password.getText().toString());
                                        user.setUid(id);
                                        Log.d("demo",user.toString());
                                        MainActivity.firebaseRoot.child("users").child(id).child("profile").setValue(user);
                                        Log.d("demo","stored in db");
                                        mListener.AddToAuth();
                                    }
                                }
                            });
                }
            }
        });
    }

    public interface SignUpListner {
        void AddToAuth();
    }
}

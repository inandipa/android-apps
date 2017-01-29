package com.example.indra.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Preview extends Fragment {

    Context context;
    AppDetails appDetails;
    OnItemsListener  mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mListener = (OnItemsListener) context;

    }

    public Preview() {
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

        View v =inflater.inflate(R.layout.fragment_preview, container, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        ImageView star = (ImageView) v.findViewById(R.id.star);
     /*   textView.setText(appDetails.getApp_Title());
        Picasso.with(context)
                .load(appDetails.getSmall_photo_url())
                .into(imageView);
                */

        mListener.Onviews(imageView,textView,star);
        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    public interface OnItemsListener {
        // TODO: Update argument type and name
        void Onviews(ImageView im, TextView tv,ImageView imageView);
    }
}

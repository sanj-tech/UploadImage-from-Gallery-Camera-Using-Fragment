package com.jsstech.uploadimagefromgalleryandcamerausingfragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CameraFragment extends Fragment {
    Button ok;


    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_camera,container,false);



        // Inflate the layout for this fragment
    return v;    //return inflater.inflate(R.layout.fragment_camera,container,false);
    }
}
package com.jsstech.uploadimagefromgalleryandcamerausingfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // fragment=new GalleryFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl,new GalleryFragment()).commit();

    }
}
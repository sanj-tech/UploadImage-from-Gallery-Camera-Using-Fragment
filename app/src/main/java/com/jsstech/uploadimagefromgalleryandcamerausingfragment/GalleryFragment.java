package com.jsstech.uploadimagefromgalleryandcamerausingfragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Permission;

import static android.app.Activity.RESULT_OK;


public class GalleryFragment extends Fragment {
    Button btGall, btCamera,btok;
    ImageView imageV;
    Uri imageUri;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GALLERY = 2;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_gallery,container,false);
        View v = inflater.inflate(R.layout.fragment_gallery,container,false);
        btGall = v.findViewById(R.id.gallery);
        btCamera=v.findViewById(R.id.Camera);
        imageV = v.findViewById(R.id.imgp);
        btok=v.findViewById(R.id.btok);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fl,new CameraFragment()).commit();
            }
        });


        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picCam();
            }
        });

        btGall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picgal();
            }
        });
        return v;
    }

    private void picgal() {
        Dexter.withContext(requireActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                       startActivityForResult(Intent.createChooser(intent,"select image"),2);

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest,PermissionToken permissionToken) {

                    }
                }).check();
    }

    private void picCam() {
        Dexter.withContext(requireActivity()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intent, 1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest,PermissionToken permissionToken) {

                    }
                }).check();




    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==REQUEST_IMAGE_GALLERY && resultCode==RESULT_OK)
        {
            imageUri=data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(imageUri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageV.setImageBitmap(bitmap);



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }



        }else if (requestCode==REQUEST_IMAGE_CAPTURE &&resultCode==RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageV.setImageBitmap(imageBitmap);


        }


    }
}
package com.example.yedinisan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yedinisan.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.sql.SQLOutput;
import java.util.UUID;

public class Fragment_Profile extends Fragment {
    Button btn_photoChange;
    TextView tv_mail;
    TextView tv_kullaniciadii;
    ImageView iv_profile;
    private static final int PICK_PHOTO_CODE = 1;
    private static final String  FIREBASE_DIRECTORY = "/UserProfilePhotos/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        btn_photoChange = view.findViewById(R.id.btn_profile_degistir);
        tv_mail = view.findViewById(R.id.tv_profile_mail);
        iv_profile = view.findViewById(R.id.profile_image);
        tv_mail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        tv_kullaniciadii = view.findViewById(R.id.tv_profile_kullaniciadi);
        //tv_kullaniciadii.setText(FirebaseAuth.getInstance().getCurrentUser().get
        btn_photoChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, PICK_PHOTO_CODE);
            }
        });
        DownloadImage(FIREBASE_DIRECTORY+ FirebaseAuth.getInstance().getCurrentUser().getUid()+"-pp.jpg");


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_CODE)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                System.out.println(selectedImage);
                UploadImage(selectedImage, FIREBASE_DIRECTORY + FirebaseAuth.getInstance().getCurrentUser().getUid() + "-pp.jpg");
            }


    }


    private void UploadImage(final Uri filepath, String photoname) {
        if (filepath != null) {
            final ProgressDialog p = new ProgressDialog(getActivity());
            p.setTitle("Yükleniyor...Lütfen sabırla bekleyiniz :)  ");
            p.show();

            StorageReference ref = FirebaseStorage.getInstance().getReference(photoname);
            //Uri file = Uri.fromFile(new File(filepath));
            ref.putFile(filepath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        ChangeUserImage(filepath);
                        //return;
                    } else {
                        System.out.println("Bitti: " + task.getException());

                    }
                    p.dismiss();
                }


            });
        }
    }

    private void ChangeUserImage(Uri imageUrl) {
        Glide.with(this).load(imageUrl).into(iv_profile);

    }
    private void DownloadImage(final String filepath) {
        if (filepath != null){
            final ProgressDialog p = new ProgressDialog(getActivity());
            p.setTitle("Yükleniyor... ");
            p.show();

            StorageReference ref = FirebaseStorage.getInstance().getReference(filepath);
            Task<Uri> url = ref.getDownloadUrl();

            url.addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        ChangeUserImage(task.getResult() );
                        System.out.println("url:" + task.getResult());
                    }
                    p.dismiss();
                }

            });

        }


    }


}
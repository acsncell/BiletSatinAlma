package com.example.yedinisan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.yedinisan.R;
import com.example.yedinisan.models.FilmBilet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BiletEkleFragment extends Fragment {

    EditText et_filmad;

    EditText et_filmfiyat;
    Button btn_ekle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biletolustur, container, false);


        et_filmad = view.findViewById(R.id.et_biletekle_filmad);

        et_filmfiyat = view.findViewById(R.id.et_biletekle_filmfiyat);
        btn_ekle = view.findViewById(R.id.btn_biletekle);

        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filmAdi = et_filmad.getText().toString();

                String filmFiyat = et_filmfiyat.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("biletler");

                String biletID = myRef.push().getKey();

                FilmBilet movieTicket =
                        new FilmBilet("https://resim.fullhdfilmizlesene.net/mdsresim_izle/fullhd-harry-potter-ve-olum-yadigarlari-bolum-2-turkce-izle.jpg",
                                filmAdi,filmFiyat );
                myRef.child(biletID).setValue(movieTicket);

            }
        });

        return view;
    }
}
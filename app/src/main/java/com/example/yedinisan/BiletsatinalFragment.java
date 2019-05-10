package com.example.yedinisan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yedinisan.models.FilmBilet;

import java.util.ArrayList;
import java.util.Random;

public class BiletsatinalFragment extends Fragment {
TextView tv_biletAd, tv_biletNo, tv_ucret;
Button satinal ;
ImageView iv_biletimage;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bilet_satinal, container, false);
        tv_biletAd = view.findViewById(R.id.tv_biletadi);
        //tv_biletNo = view.findViewById(R.id.tv_biletno);
        tv_ucret= view.findViewById(R.id.tv_biletucret);
        iv_biletimage= view.findViewById(R.id.iv_filmimage);

        satinal= view.findViewById(R.id.bt_satinal);

        if (getActivity() != null) {
            ArrayList<FilmBilet> biletlerim = ((MainActivity) getActivity()).biletArrayList;
            final ArrayList<FilmBilet> satinalinanlar = ((MainActivity) getActivity()).satinalinan;

           // final FilmBilet bilet = GetRandomBilet(biletlerim);

          //  tv_biletAd.setText(String.valueOf(bilet.getFilmicerik()));
            //tv_biletNo.setText(String.valueOf(bilet.getBiletno()));
        //   tv_ucret.setText(String.valueOf(bilet.getUcreti()));
           //iv_biletimage.setImageResource(bilet.getGorsel());
//            satinal.setOnClickListener(new View.OnClickListener() {
//
//
//                @Override
//                public void onClick(View v) {
//                    satinalinanlar.add(bilet);
//
//
//                }
//            });


        }

        return view;
    }

   FilmBilet GetRandomBilet (ArrayList<FilmBilet>biletlistesi ){

        return biletlistesi.get(new Random().nextInt(biletlistesi.size()));
   }
}
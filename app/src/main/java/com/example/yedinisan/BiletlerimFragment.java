package com.example.yedinisan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yedinisan.adapter.FilmBiletAdapter;
import com.example.yedinisan.models.FilmBilet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BiletlerimFragment extends Fragment {

    private ListView lv_biletlerim;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_biletlerim,container,false);

        lv_biletlerim  = view.findViewById(R.id.lv_filmList);

        final ArrayList<FilmBilet> filmListesi = new ArrayList<>();

        final FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("biletler");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sh: dataSnapshot.getChildren()){
                    FilmBilet filmBilet= sh.getValue(FilmBilet.class);

                    filmListesi.add(filmBilet);
                    System.out.println(filmBilet.getFilmicerik());

                    FilmBiletAdapter adapter= new FilmBiletAdapter(getActivity(),filmListesi,inflater);
                    lv_biletlerim.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        FilmBiletAdapter adapter= new FilmBiletAdapter(getActivity(), satinalinanlar, inflater);
//        lv_biletlerim.setAdapter(adapter);



        return view;


    }
}

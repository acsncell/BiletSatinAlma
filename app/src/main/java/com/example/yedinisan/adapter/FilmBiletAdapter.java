package com.example.yedinisan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yedinisan.R;

import com.example.yedinisan.models.FilmBilet;

import java.util.ArrayList;

public class FilmBiletAdapter extends BaseAdapter {

    Context context;
    ArrayList<FilmBilet>FilmbiletList;
    LayoutInflater inflater;


    public FilmBiletAdapter(Context context, ArrayList<FilmBilet>FilmbiletList, LayoutInflater layoutInflater){

        this.context= context;
        this.FilmbiletList= FilmbiletList;
        this.inflater= layoutInflater;
    }

    @Override
    public int getCount() {
        return FilmbiletList.size();
    }

    @Override
    public Object getItem(int position) {
        return FilmbiletList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.listitem_film,null);
        ImageView i_filmgorsel = v.findViewById(R.id.iv_filmgorsel);
        TextView t_filmadi = v.findViewById(R.id.tv_filmadi);
        TextView t_ucreti = v.findViewById(R.id.tv_ucret);

        FilmBilet filmBilet= FilmbiletList.get(position);

        Glide.with(context).load(filmBilet.getGorsel()).into(i_filmgorsel);
        //i_filmgorsel.setImageResource(filmBilet.getGorsel());
        t_filmadi.setText(filmBilet.getFilmicerik());
        t_ucreti.setText(String.valueOf(filmBilet.getUcreti()));

        return v;
    }







}

package com.example.yedinisan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.yedinisan.models.FilmBilet;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{
    public ArrayList<FilmBilet> biletArrayList= new ArrayList<>();
    public ArrayList<FilmBilet> satinalinan= new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        //toolbar.setTitle("Bilet Satın Alma Uygulaması");
        setSupportActionBar(toolbar);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("biletler");
        //myRef.setValue("heloooooo");

        String bileyId = myRef.push().getKey();




        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
         this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.nav_profile:

                        selectedFragment = new Fragment_Profile();
                        break;
                    case R.id.nav_bilet_ekle:

                        selectedFragment = new BiletEkleFragment();
                        break;

                }


                if(selectedFragment != null){
                    FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fl_container,selectedFragment);
                    transaction.commit();
                    drawer.closeDrawer(Gravity.START);
                }


//                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fl_container, selectedFragment);
//                transaction.commit();
                return true;


            }
        });



        bottomNavigationView = findViewById(R.id.bottomnav_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


//        biletArrayList.add(new FilmBilet(R.drawable.kayipbalik, "kayıp balık nemo", 25,"123"));
//        biletArrayList.add(new FilmBilet(R.drawable.film_2, "karayip korsanları", 30,"234"));
//        biletArrayList.add(new FilmBilet(R.drawable.film_3, "hızlı ve öfkeli", 35,"345"));
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.exa);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment selectedFragment = null;

        switch (menuItem.getItemId()){
            case R.id.v_biletsatinal:
                selectedFragment= new BiletsatinalFragment();
                break;


            case R.id.v_biletlerim:
                selectedFragment= new BiletlerimFragment();
                break;


        }

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_container, selectedFragment);
        transaction.commit();
        return true;
    }
}

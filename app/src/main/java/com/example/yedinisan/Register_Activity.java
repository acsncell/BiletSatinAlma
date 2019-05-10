package com.example.yedinisan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {

    EditText et_adgiris, et_mailgiris, et_sifregiris;
    Button bt_kayit;
    Switch sw_onay;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_adgiris = (EditText) findViewById(R.id.et_ad);
        et_mailgiris = (EditText) findViewById(R.id.et_email);
        et_sifregiris = (EditText) findViewById(R.id.et_sifre);

        bt_kayit = (Button) findViewById(R.id.bt_kayitol);
        sw_onay = (Switch) findViewById(R.id.sw_onay);

        mAuth = FirebaseAuth.getInstance();


        bt_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = et_adgiris.getText().toString();
                final String password = et_sifregiris.getText().toString();
                final String mail = et_mailgiris.getText().toString();




                if (Checform(name, password, mail)) {

                mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener
                        (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           Toast.makeText(Register_Activity.this, "Kayıt işlemi başarıyla gerçekleşti..", Toast.LENGTH_LONG).show();
                           FirebaseUser user = mAuth.getCurrentUser();
                       } else{
                           System.out.println("Kayıt gerçekleşemedi..  : "+ task.getException());
                           Toast.makeText(Register_Activity.this,"Kayıt işlemi gerçekleştirilemedi..",Toast.LENGTH_LONG).show();
                       }
                    }
                });
                    //startActivity(new Intent(Register_Activity.this, LoginActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Boş Geçilemez!!!", Toast.LENGTH_LONG).show();
                    System.out.println("ssssssssssssssssssssssssssssss");
                }


//                if (et_adgiris.getText().toString().equals("")
//                        || et_mailgiris.getText().toString().equals("")
//                        || et_sifregiris.getText().toString().equals("")) {
//                    //todo: toast mesajı eklenecek
//                }
            }
        });


    }

    public Boolean Checform(String... data) {
        for (String str : data) {
            if (str == null || str.trim().equals("")) {
                return false;
            }
        }
        return true;


    }
}




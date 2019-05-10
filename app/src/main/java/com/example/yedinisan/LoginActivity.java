package com.example.yedinisan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText e_kullaniciadi, e_sifre;
    Button b_girisyap;
    TextView tv_kayıt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e_kullaniciadi = (EditText) findViewById(R.id.et_login_kullaniciadi);
        e_sifre = (EditText) findViewById(R.id.et_sifre);
        b_girisyap = (Button) findViewById(R.id.bt_giris);
        tv_kayıt = (TextView) findViewById(R.id.tv_kayıt_olustur);

        //FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        tv_kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this, Register_Activity.class);
                startActivity(intent);
            }
        });



        b_girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String kullaniciadi = e_kullaniciadi.getText().toString();
                final String sifre = e_sifre.getText().toString();
                mAuth.signInWithEmailAndPassword(kullaniciadi,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i= new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(i);
                            finish();
                            //FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            System.out.println("Failed");
                        }
                    }
                });



//                if (kullaniciadi.equals("GYK") && sifre.equals("3422")) {
//                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i);
//
//                }
//                else
//                    if (kullaniciadi.equals("") && sifre.equals("")) {
//                        System.out.println("bbbbbbbbbbbbbbbb");
//                        Toast.makeText(getApplicationContext(), "Kullanıcı adı/şifre boş geçilemez.", Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "Kullanıcı adı veya şifre hatalı, Lütfen tekrar deneyiniz..", Toast.LENGTH_LONG).show();
//                }

            }
        });


    }
}

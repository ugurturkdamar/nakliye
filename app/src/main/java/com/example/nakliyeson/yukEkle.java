package com.example.nakliyeson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Timestamp;
import java.util.HashMap;

public class yukEkle extends AppCompatActivity {

    EditText yukadi;
    EditText yukcesidi;
    EditText parcayuk;
    EditText yon2;
    EditText tel;
    EditText fiyat;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuk_ekle);

        yukadi = findViewById(R.id.editText11);
        yukcesidi = findViewById(R.id.editText12);
        parcayuk = findViewById(R.id.editText13);
        yon2 = findViewById(R.id.editText14);
        tel = findViewById(R.id.editText16);
        fiyat = findViewById(R.id.editText17);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void ekle(View view){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String verenEmail = firebaseUser.getEmail();

        String yukAdi = yukadi.getText().toString();
        String yukCesidi = yukcesidi.getText().toString();
        String parcaYuk = parcayuk.getText().toString();
        String yon = yon2.getText().toString();
        String a = tel.getText().toString();
        String b = fiyat.getText().toString();

        HashMap<String,Object> yukData = new HashMap<>();
        yukData.put("verenemail",verenEmail);
        yukData.put("yukadi",yukAdi);
        yukData.put("yukcesidi",yukCesidi);
        yukData.put("parcayuk",parcaYuk);
        yukData.put("yon",yon);
        yukData.put("tel",a);
        yukData.put("fiyat",b);
        yukData.put("tarih", FieldValue.serverTimestamp());

        firebaseFirestore.collection("yukVeren").add(yukData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Intent intent = new Intent (yukEkle.this, yukler.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(yukEkle.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

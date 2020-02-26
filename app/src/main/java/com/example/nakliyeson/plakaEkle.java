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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class plakaEkle extends AppCompatActivity {

    EditText plakaText;
    EditText editText9;
    EditText editText10;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plaka_ekle);

        plakaText = findViewById(R.id.plakaText);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void ekle(View view){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String tasiyanEmail = firebaseUser.getEmail();

        String plaka = plakaText.getText().toString();
        String adSoyad = editText9.getText().toString();
        String iletisim = editText10.getText().toString();

        HashMap<String,Object> plakaData = new HashMap<>();
        plakaData.put("tasiyanemail",tasiyanEmail);
        plakaData.put("plaka",plaka);
        plakaData.put("adSoyad",adSoyad);
        plakaData.put("iletisim",iletisim);

        firebaseFirestore.collection("yukTasiyan").add(plakaData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Intent intent = new Intent (plakaEkle.this, yukler.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(plakaEkle.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

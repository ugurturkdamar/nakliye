package com.example.nakliyeson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class yukDetay extends AppCompatActivity {

    TextView textView;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference yukRef;

    ArrayList<String> dizi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuk_detay);

        textView = findViewById(R.id.textView);

        dizi = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ArrayList<String> value = extras.getStringArrayList("key");
            //The key argument here must match that used in the other activity

            textView.setText("Tel: "+value);
        }

    }

    public void al(View view){


        db.collection("yukVeren").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot value : task.getResult()){
                        dizi.add(value.getId());
                        System.out.println(dizi);
                    }
                }else{
                    Log.d("TAG","Dökümanı alırken hata oluştu:",task.getException());
                }
            }
        });




    }
}

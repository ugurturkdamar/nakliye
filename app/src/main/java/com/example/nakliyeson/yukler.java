package com.example.nakliyeson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class yukler extends AppCompatActivity implements com.example.nakliyeson.yukRecyclerAdapter.OnYukListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<String> yukAdiFromFB;
    ArrayList<String> yukCesidiFromFB;
    ArrayList<String> parcaYukFromFB;
    ArrayList<String> yonFromFB;
    ArrayList<String> fiyatFromFB;
    ArrayList<String> telFromFB;

    yukRecyclerAdapter yukRecyclerAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tasiyan_options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.plakaEkle){
            Intent intentToEkle = new Intent(getApplicationContext(),plakaEkle.class);
            startActivity(intentToEkle);
        }
        else if(item.getItemId() == R.id.cikis){
            mAuth.signOut();

            Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yukler);

        yukAdiFromFB = new ArrayList<>();
        yukCesidiFromFB = new ArrayList<>();
        parcaYukFromFB = new ArrayList<>();
        yonFromFB = new ArrayList<>();
        fiyatFromFB = new ArrayList<>();
        telFromFB = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFirestore();

        //RecycylerView

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        yukRecyclerAdapter = new yukRecyclerAdapter(yukAdiFromFB,yukCesidiFromFB,parcaYukFromFB,yonFromFB,fiyatFromFB,telFromFB,this);

        recyclerView.setAdapter(yukRecyclerAdapter);
    }

    public void getDataFromFirestore(){
        CollectionReference collectionReference = firebaseFirestore.collection("yukVeren");
        collectionReference.orderBy("tarih", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e!=null){
                    Toast.makeText(yukler.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null){
                    for(DocumentSnapshot snapshot: queryDocumentSnapshots.getDocuments()){

                        Map <String,Object> data = snapshot.getData();

                        String yukAdi=(String) data.get("yukadi");
                        String yukCesidi=(String) data.get("yukcesidi");
                        String yon= (String)data.get("yon");
                        String parcaYuk=(String)data.get("parcayuk");
                        String fiyat=(String) data.get("fiyat");
                        String tel=(String) data.get("tel");

                        yukAdiFromFB.add(yukAdi);
                        yukCesidiFromFB.add(yukCesidi);
                        parcaYukFromFB.add(parcaYuk);
                        yonFromFB.add(yon);
                        fiyatFromFB.add(fiyat);
                        telFromFB.add(tel);

                        yukRecyclerAdapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }


    @Override
    public void onYukClick(int posiiton) {
        telFromFB.get(posiiton);
        Intent intent = new Intent(getApplicationContext(),yukDetay.class);
        intent.putExtra("key",telFromFB);
        startActivity(intent);
    }
}

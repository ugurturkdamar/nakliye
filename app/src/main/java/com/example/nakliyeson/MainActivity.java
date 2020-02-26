package com.example.nakliyeson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tasiyanGiris(View view){
        Intent intent =new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    public void verenGiris(View view){
        Intent intent =new Intent(MainActivity.this,Main3Activity.class);
        startActivity(intent);
    }
}

package com.rdeluca118.dartscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

}

    public void doNewGame(View v){
        startActivity(new Intent(this, GameActivity.class));
    }


    public void doDatabase(View v){
        startActivity(new Intent(this, DataActivity.class));
    }

    public void shutDown(View view) {
    }}

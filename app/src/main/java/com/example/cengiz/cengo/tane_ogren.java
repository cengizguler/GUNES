package com.example.cengiz.cengo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class tane_ogren extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_ogren);
    }
    public void meyvelerleogren(View view) {
        Intent intent = new Intent(this, tane_meyvelerle_ogren.class);
        startActivity(intent);
    }
    public void esyalarlaogren(View view) {
        Intent intent = new Intent(this, tane_esyalarla_ogren.class);
        startActivity(intent);
    }
}

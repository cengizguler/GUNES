package com.example.cengiz.cengo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class tane_esyalarla_ogren extends AppCompatActivity {
    int sayi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_esyalarla_ogren);
    }
    public void esya(View view)  {
        switch (view.getId()) {
            case R.id.esyalarla_ogren_kasik:
                sayi = 0;
                break;
            case R.id.textViewKasik:
                sayi = 0;
                break;
            case R.id.esyalarla_ogren_catal:
                sayi = 1;
                break;
            case R.id.textViewCatal:
                sayi = 1;
                break;
            case R.id.esyalarla_ogren_tabak:
                sayi = 2;
                break;
            case R.id.textViewTabak:
                sayi = 2;
                break;
            case R.id.esyalarla_ogren_bardak:
                sayi = 3;
                break;
            case R.id.textViewBardak:
                sayi = 3;
                break;
            case R.id.esyalarla_ogren_sandalye:
                sayi = 4;
                break;
            case R.id.textViewSandalye:
                sayi = 4;
                break;

            default:
                break;
        }

        Intent intent = new Intent(this, tane_esya.class);
        intent.putExtra("sayi", sayi);
        startActivity(intent);
    }

}


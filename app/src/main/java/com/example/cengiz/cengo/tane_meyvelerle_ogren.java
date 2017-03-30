package com.example.cengiz.cengo;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class tane_meyvelerle_ogren extends AppCompatActivity {

    int sayi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_meyvelerle_ogren);
    }

    public void meyve(View view)  {
        switch (view.getId()) {
            case R.id.meyvelerle_ogren_armut:
                sayi = 0;
                break;
            case R.id.textViewArmut:
                sayi = 0;
                break;
            case R.id.meyvelerle_ogren_elma:
                sayi = 1;
                break;
            case R.id.textViewElma:
                sayi = 1;
                break;
            case R.id.meyvelerle_ogren_Kayısı:
                sayi = 2;
                break;
            case R.id.textViewKayısı:
                sayi = 2;
                break;
            case R.id.meyvelerle_ogren_çilek:
                sayi = 3;
                break;
            case R.id.textViewCilek:
                sayi = 3;
                break;
            case R.id.meyvelerle_ogren_muz:
                sayi = 4;
                break;
            case R.id.textViewMuz:
                sayi = 1;
                break;
            case R.id.meyvelerle_ogren_nar:
                sayi = 5;
                break;
            case R.id.textViewNar:
                sayi = 5;
                break;
            case R.id.meyvelerle_ogren_portakal:
                sayi = 6;
                break;
            case R.id.textViewPortakal:
                sayi = 6;
                break;
            case R.id.meyvelerle_ogren_erik:
                sayi = 7;
                break;
            case R.id.textViewErik:
                sayi = 7;
                break;
            case R.id.meyvelerle_ogren_karpuz:
                sayi = 8;
                break;
            case R.id.textViewKarpuz:
                sayi = 8;
                break;
            case R.id.meyvelerle_ogren_şeftali:
                sayi = 9;
                break;
            case R.id.textViewSeftali:
                sayi = 9;
                break;

            default:
                break;
        }

        Intent intent = new Intent(this, tane_meyve.class);
        intent.putExtra("sayi", sayi);
        startActivity(intent);
    }

}

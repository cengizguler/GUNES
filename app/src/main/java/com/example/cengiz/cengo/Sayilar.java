package com.example.cengiz.cengo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sayilar extends AppCompatActivity implements OnClickListener {

    MediaPlayer bg;
    Button tum;
    Button ogren;
    Button oyna;
    final Context context = this;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            System.gc();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.sayilar);

            bg = MediaPlayer.create(Sayilar.this, R.raw.muzik);
            bg.setLooping(true);
            bg.start();

            tum = (Button) findViewById(R.id.btntum);
            tum.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, tumSayilar.class);
                    startActivity(intent);
                }
            });

            ogren = (Button)findViewById((R.id.btnOgren));
            ogren.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, sayiOgretme.class);
                    startActivity(intent);
                }
            });

            oyna = (Button)findViewById(R.id.btnOyna);
            oyna.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, eslesayi.class);
                    startActivity(intent);
                }
            });

        }


    public void onClick(View v) {

    }
    protected void onPause(){
        super.onPause();
        bg.release();
     //   finish();
    }
    public void onBackPressed() {
        // do something here and don't write super.onBackPressed()
        Intent intent = new Intent(this, first.class);
        startActivity(intent);
        super.onBackPressed();
    }
    }



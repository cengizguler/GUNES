package com.example.cengiz.cengo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Esyalar extends AppCompatActivity {
    Button esyaOgren;
    Button esyaOyna;
    Button esyaTum;
    MediaPlayer bg;

    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esyalar);

        bg = MediaPlayer.create(Esyalar.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

        esyaOgren = (Button) findViewById(R.id.esya_ogren);
        esyaOgren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, EsyalarOgretme.class);
                startActivity(intent);
            }
        });

        esyaOyna = (Button)findViewById(R.id.esya_testet);
        esyaOyna.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(context, EsyaOyun.class);
                    startActivity(intent);
                }
        });

        esyaTum = (Button)findViewById(R.id.tumEsyalar);
        esyaTum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, EsyaTamami.class);
                startActivity(intent);
            }
        });
    }

    protected void onPause(){
        super.onPause();
        bg.release();
        //finish();
    }
}

package com.example.cengiz.cengo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class tane_ekran extends AppCompatActivity implements OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Button buttonOgr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_ekran);


       // bg = MediaPlayer.create(SayiEsleme.this, R.raw.muzik);
       // bg.setLooping(true);
       // bg.start();
        final Context context = this;

        button1 = (Button) findViewById(R.id.taneoyun1);
        button1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, tane_oyun1.class);
                startActivity(intent);
            }
        });
        button2 = (Button) findViewById(R.id.taneoyun2);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, taneoyun2.class);
                startActivity(intent);
            }
        });
        button3 = (Button) findViewById(R.id.taneoyun3);
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, TaneOyun3.class);
                startActivity(intent);
            }
        });
        button4 = (Button) findViewById(R.id.taneogrenme);
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, tane_ogren.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onClick(View v) {

    }




}








package com.example.cengiz.cengo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Hakkimizda extends AppCompatActivity {
    MediaPlayer bg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);

        bg = MediaPlayer.create(Hakkimizda.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();
    }
    protected void onPause(){
        super.onPause();
        bg.release();
      //  finish();
    }
}

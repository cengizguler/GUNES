package com.example.cengiz.cengo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.media.MediaPlayer;
import android.widget.Toast;

public class first extends AppCompatActivity implements OnClickListener {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;

    ViewGroup rootLayout;
    ImageView imgBir;
    int _xDelta;
    int _yDelta;

MediaPlayer bg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        bg = MediaPlayer.create(first.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();



        final Context context = this;
        button1 = (Button) findViewById(R.id.buttonesya);
       // ImageView im = (ImageView)findViewById(R.id.imageView2);

        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, Esyalar.class);
                startActivity(intent);
            }
        });

        button2 = (Button) findViewById(R.id.buttonrenk);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, Renkler.class);
                startActivity(intent);
            }
        });

        button3 = (Button)findViewById(R.id.buttonsayi);
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, Sayilar.class);
                startActivity(intent);
            }
        });

        button4 = (Button)findViewById(R.id.buttonmeyve);
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, Meyveler.class);
                startActivity(intent);
            }
        });

        button5 = (Button)findViewById(R.id.buttonHakkimizda);
        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, Hakkimizda.class);
                startActivity(intent);
            }
        });

        button6 = (Button)findViewById(R.id.buttontaneyeGecis);
        button6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent (context, tane_ekran.class);
                startActivity(intent);
            }
        });
    }


    public void ToastFK(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onClick(View v) {

    }


    protected void onPause(){
        super.onPause();
        bg.release();
        //finish();
    }
}

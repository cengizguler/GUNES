package com.example.cengiz.cengo;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class eslesayi extends AppCompatActivity {

    ImageView i1, i2, dragbutton, smiley;
    RelativeLayout dropbutton;
    TextView tv, success;
    TextView eslestir;
    MediaPlayer dogru,yanlis;
    Button devam;
    int total, fail = 0;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.eslesayi);
        dragbutton = (ImageView) findViewById(R.id.bir);
        dropbutton = (RelativeLayout) findViewById(R.id.bottomlinear);
        //  tv = (TextView)findViewById(R.id.Total) ;
        success = (TextView) findViewById(R.id.Success);
        eslestir = (TextView) findViewById(R.id.eslestir);
        devam = (Button) findViewById(R.id.devam);
        i1 = (ImageView) findViewById(R.id.imageView1);
        i2 = (ImageView) findViewById(R.id.imageView2);
        smiley = (ImageView) findViewById(R.id.happy);
        dogru = MediaPlayer.create(this, R.raw.dogru);
        dogru.setVolume(10.0f, 3.0f);
        yanlis = MediaPlayer.create(this, R.raw.yanlis);
        yanlis.setVolume(10.0f, 3.0f);
        dropbutton.setOnDragListener(new View.OnDragListener() {

            public boolean onDrag(View v, DragEvent event) {

                final int action = event.getAction();
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DROP: {
                        fail = fail + 1;
                        success.setText("BİR DAHA DENE!");
                        yanlis.start();
                        return (true);
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {

                        total = total + 1;
                        int value = total - fail;

                        if (value == 1) {
                            dogru.start();

                            //TimeUnit.SECONDS.sleep(1);
                            devam.setVisibility(View.VISIBLE);
                            eslestir.setVisibility(View.INVISIBLE);
                            success.setText("DOĞRU!");
                            i1.setVisibility(View.INVISIBLE);
                            i2.setVisibility(View.INVISIBLE);
                            dragbutton.setVisibility(View.INVISIBLE);
                            smiley.setImageResource(R.drawable.happy);


                        }
                        //  tv.setText("TOPLAM DENEME: " + total);
                        return (true);

                    }
                    default:
                        break;


                }
                return (true);
            }


        });
        dragbutton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(dragbutton);
                v.startDrag(data, shadow, null, 0);
                return false;
            }
        });


    }

    public void sonraki_sayfa(View view) {


        Intent intent = new Intent(this, eslesayi2.class);

        startActivity(intent);

    }

    public void onceki_sayfa(View view) {
        Intent intent = new Intent(this, Sayilar.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        // do something here and don't write super.onBackPressed()
        finish();
        super.onBackPressed();

    }


}
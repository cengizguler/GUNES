package com.example.cengiz.cengo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class tane_oyun1 extends AppCompatActivity {
    MediaPlayer bg;
    int sira = 0;
    ArrayList<Integer> sorular = new ArrayList<>();
    ArrayList<ImageView> secenek;
    int [] resimler = new int[10];
    private TextToSpeech tts;
    private boolean flag;
    MediaPlayer dogru;
    MediaPlayer yanlis;
    ImageView buyut;
    Handler handler = new Handler();
    Runnable runnable = new Runnable(){
        @Override
        public void run(){
            yardim();
            handler.postDelayed(this, 5000);
        }
    };

    private void init() {
        this.startActivityForResult(new Intent(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA), 1);
    }

    public class Initializer implements TextToSpeech.OnInitListener {
        @Override
        public void onInit(int status) {
            if(status == TextToSpeech.SUCCESS) {
                flag = true;
                Locale locale = new Locale("tr", "TR");
                if(tts.isLanguageAvailable(locale) >= 0) {
                    tts.setLanguage(locale);
                    tts.setPitch(0.8F);
                    tts.setSpeechRate(1.5F);
                    TextView textView = (TextView) findViewById(R.id.sec);
                    MetniSeslendir(textView.getText().toString());
                }
                else {
                    Log.w("tts", "Türkçe paketi kurulu değil");
                }
            }
            if(status == TextToSpeech.ERROR) {
                Log.e("tts", "Hata");
            }
        }
    }

    private void MetniSeslendir(String metin) {
        if(tts != null && flag) {
            tts.speak(metin, TextToSpeech.QUEUE_ADD, null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = new TextToSpeech(this, new Initializer());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        bg = MediaPlayer.create(tane_oyun1.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

        randomize(sorular);
        sira = 0;
        setContentView(R.layout.activity_taneoyun1);
        resimler[0] = R.drawable.elmakare;
        resimler[1] = R.drawable.elmaiki;
        resimler[2] = R.drawable.elmauc;
        resimler[3]= R.drawable.cilekkare;
        resimler[4]= R.drawable.cilekiki;
        resimler[5]=R.drawable.cilekuc;
        resimler[6]=R.drawable.kasikkare;
        resimler[7]=R.drawable.kasikiki;
        resimler[8]=R.drawable.kasikuc;
        resimler[9]=R.drawable.sandalyekare;
        dogru = MediaPlayer.create(this, R.raw.dogru);
        devam();
        init();
    }

    @Override
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    private void randomize(ArrayList<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
    }

    private void imageChange() {

        ArrayList<Integer> secenekler = new ArrayList<>();
        randomize(secenekler);
        ImageView image1 = (ImageView) findViewById(R.id.tane1);
        ImageView image2 = (ImageView) findViewById(R.id.tane2);
        ImageView image3 = (ImageView) findViewById(R.id.tane3);

        image1.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        secenek = new ArrayList<>();
        if(sira > 4) {
            image3.setVisibility(View.VISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            secenek.add(image3);
            Collections.shuffle(secenek);
            try {
                secenek.get(0).setBackgroundResource(resimler[sorular.get(sira)]);
                int i = 0;
                if (secenekler.get(i).equals(sorular.get(sira))) {
                    i++;
                }
                secenek.get(1).setBackgroundResource(resimler[secenekler.get(i)]);
                i++;
                if (secenekler.get(i).equals(sorular.get(sira))) {
                    i++;
                }
                secenek.get(2).setBackgroundResource(resimler[secenekler.get(i)]);
            } catch (IndexOutOfBoundsException i) {}
        }
        else {
            image3.setVisibility(View.INVISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            Collections.shuffle(secenek);
            try {
                secenek.get(0).setBackgroundResource(resimler[sorular.get(sira)]);
                int i = 0;
                if (secenekler.get(i).equals(sorular.get(sira))) {
                    i++;
                }
                secenek.get(1).setBackgroundResource(resimler[secenekler.get(i)]);
            } catch (IndexOutOfBoundsException i) {}
        }
    }

    public void dogruMu(View view) {

        handler.removeCallbacks(runnable);
        try {
            buyut.clearAnimation();
        } catch(Exception e) {}
        if(secenek.get(0).equals(view)) {
            dogru = MediaPlayer.create(this, R.raw.dogru);
            dogru.setVolume(10.0f, 3.0f);
            dogru.start();
            while(dogru.isPlaying() == true) {
            }
            sira++;
            try {
                dogru = MediaPlayer.create(this, R.raw.tebrik);
                dogru.setVolume(10.0f, 3.0f);
                dogru.start();
                animation();
            } catch(IndexOutOfBoundsException i) {};
        }
        else {
            yanlis = MediaPlayer.create(this, R.raw.yanlis);
            yanlis.setVolume(10.0f, 3.0f);
            yanlis.start();
            while(yanlis.isPlaying() == true) {
            }
            view.setVisibility(View.INVISIBLE);
        }
    }

    public void devam() {
        System.gc();
        if(sira == 10) {
            finish();
        }
        while(dogru.isPlaying() == true) {
        }
        handler.removeCallbacks(runnable);
        TextView textView = (TextView) findViewById(R.id.sec);
        String [] text = new String[10];
        text[0] = "BİR elmayı";
        text[1] = "İKİ elmayı";
        text[2] = "ÜÇ elmayı";
        text[3] = "BİR çileği";
        text[4] = "İKİ çileği";
        text[5] = "ÜÇ çileği";
        text[6] = "BİR kaşığı";
        text[7] = "İKİ kaşığı";
        text[8] = "ÜÇ kaşığı";
        text[9] = "BİR sandalyeyi";
        imageChange();
        try {
            textView.setText(text[sorular.get(sira)] + " seç");
        } catch(ArrayIndexOutOfBoundsException i){};
        MetniSeslendir(textView.getText().toString());
        handler.postDelayed(runnable, 5000);
    }

    protected void onPause(){
        super.onPause();
        bg.release();
    }

    private void yardim() {

        ScaleAnimation anim = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1);
        anim.setDuration(300);
        if(secenek.get(0).equals(findViewById(R.id.tane1))) {
            buyut = (ImageView) findViewById(R.id.tane1);
        }
        else if(secenek.get(0).equals(findViewById(R.id.tane2))) {
            buyut = (ImageView) findViewById(R.id.tane2);
        }
        else {
            buyut = (ImageView) findViewById(R.id.tane3);
        }
        buyut.startAnimation(anim);
    }

    private void animation() {

        TranslateAnimation animate;
        ImageView balloonView = (ImageView) findViewById(R.id.imageTebrik);
        animate = new TranslateAnimation(0, 0, 0, -balloonView.getHeight());

        animate.setDuration(4000);
        animate.setRepeatCount(0);
        balloonView.startAnimation(animate);
        animate.setFillAfter(true);

        animate.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(sira >= 10) {
                    finish();
                }
                else  {
                    devam();
                }
            }
        });
    }
}

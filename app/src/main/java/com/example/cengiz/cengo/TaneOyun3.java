package com.example.cengiz.cengo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class TaneOyun3 extends AppCompatActivity {

    int dogruSayisi = 0;
    int sayi = 2;
    int sira = 0;
    ArrayList<Integer> sorular = new ArrayList<>();
    ArrayList<ImageView> secenek;
    int [] esyalar = new int[10];
    int [] meyveler = new int[10];
    boolean random;
    Random r;
    private TextToSpeech tts;
    private boolean flag;
    MediaPlayer dogru;
    MediaPlayer yanlis;
    int yardim = 0;
    ImageView buyut = null;
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
                    TextView textView = (TextView) findViewById(R.id.secme);
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
    protected void onDestroy() {
        if(tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        dogru = MediaPlayer.create(this, R.raw.dogru);
        r = new Random();
        random = r.nextBoolean();
        randomize(sorular);
        sira = 0;
        setContentView(R.layout.activity_tane_oyun3);
        esyalar[0] = R.drawable.catal;
        esyalar[1] = R.drawable.kasik;
        esyalar[2] = R.drawable.tabak;
        esyalar[3] = R.drawable.bardak;
        esyalar[4] = R.drawable.masa;
        esyalar[5] = R.drawable.sandalye;
        esyalar[6] = R.drawable.koltuk;
        esyalar[7] = R.drawable.dolap;
        esyalar[8] = R.drawable.yatak;
        esyalar[9] = R.drawable.yastik;
        meyveler[0] = R.drawable.seftali;
        meyveler[1] = R.drawable.kayisi;
        meyveler[2] = R.drawable.elma;
        meyveler[3] = R.drawable.armut;
        meyveler[4] = R.drawable.karpuz;
        meyveler[5] = R.drawable.cilek;
        meyveler[6] = R.drawable.muz;
        meyveler[7] = R.drawable.erik;
        meyveler[8] = R.drawable.nar;
        meyveler[9] = R.drawable.portakal;
        devam();
        init();
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
        ImageView image1 = (ImageView) findViewById(R.id.secenek1);
        ImageView image2 = (ImageView) findViewById(R.id.secenek2);
        ImageView image3 = (ImageView) findViewById(R.id.secenek3);
        ImageView image4 = (ImageView) findViewById(R.id.secenek4);
        ImageView image5 = (ImageView) findViewById(R.id.secenek5);
        ImageView image6 = (ImageView) findViewById(R.id.secenek6);
        image1.setVisibility(View.VISIBLE);
        image2.setVisibility(View.VISIBLE);
        image3.setVisibility(View.VISIBLE);
        image4.setVisibility(View.VISIBLE);
        secenek = new ArrayList<>();
        if(sira > 2 && sira <= 6) {
            yardim = 3;
            image5.setVisibility(View.VISIBLE);
            image6.setVisibility(View.INVISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            secenek.add(image3);
            secenek.add(image4);
            secenek.add(image5);
            Collections.shuffle(secenek);
            if(sira <= 4) {
                if(random) {
                    secenek.get(0).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(1).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(2).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(3).setBackgroundResource(esyalar[secenekler.get(0)]);
                    secenek.get(4).setBackgroundResource(esyalar[secenekler.get(1)]);
                }
                else {
                    secenek.get(0).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(1).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(2).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(3).setBackgroundResource(meyveler[secenekler.get(0)]);
                    secenek.get(4).setBackgroundResource(meyveler[secenekler.get(1)]);
                }
            }
            else {
                if(random) {
                    secenek.get(0).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(1).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(2).setBackgroundResource(meyveler[sorular.get(sira)]);
                    secenek.get(3).setBackgroundResource(meyveler[secenekler.get(0)]);
                    if(sorular.get(sira).equals(secenekler.get(0))) {
                        yardim++;
                    }
                    secenek.get(4).setBackgroundResource(meyveler[secenekler.get(1)]);
                    if(sorular.get(sira).equals(secenekler.get(1))) {
                        yardim++;
                    }
                }
                else {
                    secenek.get(0).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(1).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(2).setBackgroundResource(esyalar[sorular.get(sira)]);
                    secenek.get(3).setBackgroundResource(esyalar[secenekler.get(0)]);
                    if(sorular.get(sira).equals(secenekler.get(0))) {
                        yardim++;
                    }
                    secenek.get(4).setBackgroundResource(esyalar[secenekler.get(1)]);
                    if(sorular.get(sira).equals(secenekler.get(1))) {
                        yardim++;
                    }
                }
            }

        }
        else if(sira > 6) {
            yardim = 4;
            image5.setVisibility(View.VISIBLE);
            image6.setVisibility(View.VISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            secenek.add(image3);
            secenek.add(image4);
            secenek.add(image5);
            secenek.add(image6);
            Collections.shuffle(secenek);
            if(random) {
                secenek.get(0).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(1).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(2).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(3).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(4).setBackgroundResource(meyveler[secenekler.get(0)]);
                if(sorular.get(sira).equals(secenekler.get(0))) {
                    yardim++;
                }
                secenek.get(5).setBackgroundResource(meyveler[secenekler.get(1)]);
                if(sorular.get(sira).equals(secenekler.get(1))) {
                    yardim++;
                }
            }
            else {
                secenek.get(0).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(1).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(2).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(3).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(4).setBackgroundResource(esyalar[secenekler.get(0)]);
                if(sorular.get(sira).equals(secenekler.get(0))) {
                    yardim++;
                }
                secenek.get(5).setBackgroundResource(esyalar[secenekler.get(1)]);
                if(sorular.get(sira).equals(secenekler.get(1))) {
                    yardim++;
                }
            }
        }
        else {
            yardim = 2;
            image5.setVisibility(View.INVISIBLE);
            image6.setVisibility(View.INVISIBLE);
            secenek.add(image1);
            secenek.add(image2);
            secenek.add(image3);
            secenek.add(image4);
            Collections.shuffle(secenek);
            if(random) {
                secenek.get(0).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(1).setBackgroundResource(meyveler[sorular.get(sira)]);
                secenek.get(2).setBackgroundResource(esyalar[secenekler.get(0)]);
                secenek.get(3).setBackgroundResource(esyalar[secenekler.get(1)]);
            }
            else {
                secenek.get(0).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(1).setBackgroundResource(esyalar[sorular.get(sira)]);
                secenek.get(2).setBackgroundResource(meyveler[secenekler.get(0)]);
                secenek.get(3).setBackgroundResource(meyveler[secenekler.get(1)]);
            }
        }
    }

    public void dogruMu(View view) {
        handler.removeCallbacks(runnable);
        try {
            buyut.clearAnimation();
        } catch(Exception e) {}
        int dogruMu = 0;
        for(int i = 0; i < yardim; i++) {
            if (dogruSayisi <= sayi && secenek.get(i).equals(view)) {
                dogru = MediaPlayer.create(this, R.raw.dogru);
                dogru.setVolume(10.0f, 3.0f);
                dogru.start();
                while (dogru.isPlaying() == true) {
                }
                dogruSayisi++;
                view.setVisibility(View.INVISIBLE);
                if (dogruSayisi == sayi) {
                    secenekleriGorunmezYap();
                    dogruSayisi = 0;
                    sayi = 2;
                    sira++;
                    try {
                        dogru = MediaPlayer.create(this, R.raw.tebrik);
                        dogru.setVolume(10.0f, 3.0f);
                        dogru.start();
                        animation();
                    } catch (IndexOutOfBoundsException ioobe) {
                    }
                }
                dogruMu = 1;
            }
        }
        if(dogruMu == 0) {
            yanlis = MediaPlayer.create(this, R.raw.yanlis);
            yanlis.setVolume(10.0f, 3.0f);
            yanlis.start();
            while (yanlis.isPlaying() == true) {
            }
        }
        handler.postDelayed(runnable, 5000);
    }

    private void secenekleriGorunmezYap() {
        ImageView iv =(ImageView) findViewById(R.id.secenek1);
        iv.setVisibility(View.INVISIBLE);
        ImageView iv2 =(ImageView) findViewById(R.id.secenek2);
        iv2.setVisibility(View.INVISIBLE);
        ImageView iv3 =(ImageView) findViewById(R.id.secenek3);
        iv3.setVisibility(View.INVISIBLE);
        ImageView iv4 =(ImageView) findViewById(R.id.secenek4);
        iv4.setVisibility(View.INVISIBLE);
        ImageView iv5 =(ImageView) findViewById(R.id.secenek5);
        iv5.setVisibility(View.INVISIBLE);
        ImageView iv6 =(ImageView) findViewById(R.id.secenek6);
        iv6.setVisibility(View.INVISIBLE);
    }

    public void devam() {
        while(dogru.isPlaying() == true) {
        }
        dogruSayisi = 0;
        random = r.nextBoolean();
        if (sira >= 10) {
            finish();
        }
        else if(sira > 6) {
            sayi = 4;
        }
        else if(sira >= 3) {
            sayi = 3;
        }
        handler.removeCallbacks(runnable);
        TextView textView = (TextView) findViewById(R.id.secme);
        String [] esya = new String[10];
        esya[0] = "Çatal";
        esya[1] = "Kaşık";
        esya[2] = "Tabak";
        esya[3] = "Bardak";
        esya[4] = "Masa";
        esya[5] = "Sandalye";
        esya[6] = "Koltuk";
        esya[7] = "Dolap";
        esya[8] = "Yatak";
        esya[9] = "Yastık";
        String [] meyve = new String[10];
        meyve[0] = "Şeftali";
        meyve[1] = "Kayısı";
        meyve[2] = "Elma";
        meyve[3] = "Armut";
        meyve[4] = "Karpuz";
        meyve[5] = "Çilek";
        meyve[6] = "Muz";
        meyve[7] = "Erik";
        meyve[8] = "Nar";
        meyve[9] = "Portakal";
        try {
            if(random) {
                textView.setText(sayi + " tane " + meyve[sorular.get(sira)] + " seç");
            }
            else {
                textView.setText(sayi + " tane " + esya[sorular.get(sira)] + " seç");
            }
        } catch(ArrayIndexOutOfBoundsException i){};
        MetniSeslendir(textView.getText().toString());
        imageChange();
        handler.postDelayed(runnable, 5000);
    }

    private void yardim() {

        ScaleAnimation anim = new ScaleAnimation(1, 1.2f, 1, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1);
        anim.setDuration(500);
        boolean b = true;
        if(findViewById(R.id.secenek1).getVisibility() == View.VISIBLE) {
            for(int i = 0; i < yardim; i++) {
                if (secenek.get(i).equals(findViewById(R.id.secenek1))) {
                    buyut = (ImageView) findViewById(R.id.secenek1);
                    b = false;
                }
            }
        }
        if(findViewById(R.id.secenek2).getVisibility() == View.VISIBLE && b) {
            for(int i = 0; i < yardim; i++) {
                if (secenek.get(i).equals(findViewById(R.id.secenek2))) {
                    buyut = (ImageView) findViewById(R.id.secenek2);
                    b = false;
                }
            }
        }
        if(findViewById(R.id.secenek3).getVisibility() == View.VISIBLE && b) {
            for(int i = 0; i < yardim; i++) {
                if (secenek.get(i).equals(findViewById(R.id.secenek3))) {
                    buyut = (ImageView) findViewById(R.id.secenek3);
                    b = false;
                }
            }
        }
        if(findViewById(R.id.secenek4).getVisibility() == View.VISIBLE && b) {
            for(int i = 0; i < yardim; i++) {
                if (secenek.get(i).equals(findViewById(R.id.secenek4))) {
                    buyut = (ImageView) findViewById(R.id.secenek4);
                    b = false;
                }
            }
        }
        if(findViewById(R.id.secenek5).getVisibility() == View.VISIBLE && b) {
            for(int i = 0; i < yardim; i++) {
                if (secenek.get(i).equals(findViewById(R.id.secenek5))) {
                    buyut = (ImageView) findViewById(R.id.secenek5);
                    b = false;
                }
            }
        }
        if(b) {
            buyut = (ImageView) findViewById(R.id.secenek6);
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
                else {
                    devam();
                }
            }
        });
    }
}
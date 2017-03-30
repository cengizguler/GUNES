package com.example.cengiz.cengo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class EsyalarOgretme extends AppCompatActivity {
    MediaPlayer bg;
    int sira = 0;
    private TextToSpeech tts;
    private boolean flag;

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
                    MetniSeslendir("Çatal");
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
            tts = new TextToSpeech(this, new EsyalarOgretme.Initializer());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esya_ogretme);
        bg = MediaPlayer.create(EsyalarOgretme.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

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

    public void sonraki_sayfa(View view) {
        System.gc();
        ImageView image = (ImageView)findViewById(R.id.resim_esya);
        ImageView image2 = (ImageView)findViewById(R.id.imageButtonGeri);
        image2.setVisibility(View.VISIBLE);
        String esya;
        sira++;
        if(sira == 10) {
            finish();
        }
        switch (sira) {
            case 0:
                esya = "Çatal";
                image.setBackgroundResource(R.drawable.catal);
                image2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                esya = "Kaşık";
                image.setBackgroundResource(R.drawable.kasik);
                break;
            case 2:
                esya = "Tabak";
                image.setBackgroundResource(R.drawable.tabak);
                break;
            case 3:
                esya = "Bardak";
                image.setBackgroundResource(R.drawable.bardak);
                image.invalidate();
                break;
            case 4:
                esya = "Masa";
                image.setBackgroundResource(R.drawable.masa);
                break;
            case 5:
                esya = "Sandalye";
                image.setBackgroundResource(R.drawable.sandalye);
                break;
            case 6:
                esya = "Koltuk";
                image.setBackgroundResource(R.drawable.koltuk);
                break;
            case 7:
                esya = "Dolap";
                image.setBackgroundResource(R.drawable.dolap);
                break;
            case 8:
                esya = "Yatak";
                image.setBackgroundResource(R.drawable.yatak);
                break;
            case 9:
                esya = "Yastık";
                image.setBackgroundResource(R.drawable.yastik);
                break;
            default:
                esya = "";
        }
        TextView textView = (TextView) findViewById(R.id.text_esya);
        textView.setText(esya);
        MetniSeslendir(textView.getText().toString());
    }

    public void onceki_sayfa(View view) {
        sira = sira - 2;
        sonraki_sayfa(view);
    }

    protected void onPause(){
        super.onPause();
        bg.release();
       // finish();
    }
}

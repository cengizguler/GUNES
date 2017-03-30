package com.example.cengiz.cengo;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class RenkOgretme extends AppCompatActivity {

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
                    MetniSeslendir("Kırmızı");
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
        setContentView(R.layout.activity_renk_ogretme);
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
        ImageView image = (ImageView)findViewById(R.id.resim_renk);
        ImageView image2 = (ImageView)findViewById(R.id.imageButtonGeri);
        image2.setVisibility(View.VISIBLE);
        String renk;
        sira++;
        if(sira == 10) {
            finish();
        }
        switch (sira) {
            case 0:
                renk = "Kırmızı";
                image.setBackgroundResource(R.drawable.kirmizi);
                image2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                renk = "Mavi";
                image.setBackgroundResource(R.drawable.mavi);
                break;
            case 2:
                renk = "Sarı";
                image.setBackgroundResource(R.drawable.sari);
                break;
            case 3:
                renk = "Yeşil";
                image.setBackgroundResource(R.drawable.yesil);
                break;
            case 4:
                renk = "Pembe";
                image.setBackgroundResource(R.drawable.pembe);
                break;
            case 5:
                renk = "Beyaz";
                image.setBackgroundResource(R.drawable.beyaz);
                break;
            case 6:
                renk = "Turuncu";
                image.setBackgroundResource(R.drawable.turuncu);
                break;
            case 7:
                renk = "Mor";
                image.setBackgroundResource(R.drawable.mor);
                break;
            case 8:
                renk = "Siyah";
                image.setBackgroundResource(R.drawable.siyah);
                break;
            case 9:
                renk = "Kahverengi";
                image.setBackgroundResource(R.drawable.kahverengi);
                break;
            default:
                renk = "";
        }
        TextView textView = (TextView) findViewById(R.id.text_renk);
        textView.setText(renk);
        String oku = renk;
        if(oku.equals("Mavi")) {
            oku = "Maavi";
        }
        else if(oku.equals("Pembe")) {
            oku = "Pemmbe";
        }
        else  if(oku.equals("Kahverengi")) {
            oku = "Kahhverenngi";
        }
        MetniSeslendir(oku);
    }

    public void onceki_sayfa(View view) {
        sira = sira - 2;
        sonraki_sayfa(view);
    }
}



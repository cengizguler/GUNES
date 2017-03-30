package com.example.cengiz.cengo;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class tane_esya extends AppCompatActivity {


    int esyasayi = 0;
    int sira = 0;
    private TextToSpeech tts;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_esya);
        this.esyasayi = getIntent().getIntExtra("sayi", esyasayi);
        sonraki_sayfa(findViewById(R.id.imageView));
        init();
    }

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
                    TextView textView = (TextView) findViewById(R.id.textView);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            tts = new TextToSpeech(this, new Initializer());
        }
    }

    private void MetniSeslendir(String metin) {
        if(tts != null && flag) {
            tts.speak(metin, TextToSpeech.QUEUE_ADD, null);
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


    public void sonraki_sayfa(View view) {

        System.gc();
        ImageView image = (ImageView)findViewById(R.id.imageView);
        ImageView image2 = (ImageView)findViewById(R.id.imageButtonGeri);
        image2.setVisibility(View.VISIBLE);
        String esya = "";
        if(sira == 5) {
            finish();
        }
        if(esyasayi == 0) {
            switch (sira) {
                case 0:
                    esya = "1 tane kaşık";
                    image.setImageResource(R.drawable.kasik);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    esya = "2 tane kaşık";
                    image.setImageResource(R.drawable.ikikasik);
                    break;
                case 2:
                    esya = "3 tane kaşık";
                    image.setImageResource(R.drawable.uckasik);
                    break;
                case 3:
                    esya = "4 tane kaşık";
                    image.setImageResource(R.drawable.dortkasik);
                    break;
                case 4:
                    esya = "5 tane kaşık";
                    image.setImageResource(R.drawable.beskasik);
                    break;

                default:
                    esya = "";
            }
        }
        else if(esyasayi == 1) {
            switch (sira) {
                case 0:
                    esya = "1 tane çatal";
                    image.setImageResource(R.drawable.catal);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    esya = "2 tane çatal";
                    image.setImageResource(R.drawable.ikicatal);
                    break;
                case 2:
                    esya = "3 tane çatal";
                    image.setImageResource(R.drawable.uccatal);
                    break;
                case 3:
                    esya = "4 tane çatal";
                    image.setImageResource(R.drawable.dortcatal);
                    break;
                case 4:
                    esya = "5 tane çatal";
                    image.setImageResource(R.drawable.bescatal);
                    break;

                default:
                    esya = "";
            }
        }
        else if(esyasayi == 2) {
            switch (sira) {
                case 0:
                    esya = "1 tane tabak";
                    image.setImageResource(R.drawable.tabak);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    esya = "2 tane tabak";
                    image.setImageResource(R.drawable.ikitabak);
                    break;
                case 2:
                    esya = "3 tane tabak";
                    image.setImageResource(R.drawable.uctabak);
                    break;
                case 3:
                    esya = "4 tane tabak";
                    image.setImageResource(R.drawable.dorttabak);
                    break;
                case 4:
                    esya = "5 tane tabak";
                    image.setImageResource(R.drawable.bestabak);
                    break;

                default:
                    esya = "";
            }
        }
        else if(esyasayi == 3) {
            switch (sira) {
                case 0:
                    esya = "1 tane bardak";
                    image.setImageResource(R.drawable.bardak);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    esya = "2 tane bardak";
                    image.setImageResource(R.drawable.ikibardak);
                    break;
                case 2:
                    esya = "3 tane bardak";
                    image.setImageResource(R.drawable.ucbardak);
                    break;
                case 3:
                    esya = "4 tane bardak";
                    image.setImageResource(R.drawable.dortbardak);
                    break;
                case 4:
                    esya = "5 tane bardak";
                    image.setImageResource(R.drawable.besbardak);
                    break;

                default:
                    esya = "";
            }
        }

        else {
            switch (sira) {
                case 0:
                    esya = "1 tane sandalye";
                    image.setImageResource(R.drawable.sandalye);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    esya = "2 tane sandalye";
                    image.setImageResource(R.drawable.ikisandalye);
                    break;
                case 2:
                    esya = "3 tane sandalye";
                    image.setImageResource(R.drawable.ucsandalye);
                    break;
                case 3:
                    esya = "4 tane sandalye";
                    image.setImageResource(R.drawable.dortsandalye);
                    break;
                case 4:
                    esya = "5 tane sandalye";
                    image.setImageResource(R.drawable.bessandalye);
                    break;

                default:
                    esya = "";
            }
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(esya);
        MetniSeslendir(textView.getText().toString());
        sira++;
    }

    public void onceki_sayfa(View view) {
        sira = sira - 2;
        sonraki_sayfa(view);
    }
}
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

public class tane_meyve extends AppCompatActivity {


    int meyvesayi = 0;
    int sira = 0;
    private TextToSpeech tts;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tane_meyve);
        this.meyvesayi = getIntent().getIntExtra("sayi", meyvesayi);
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

        ImageView image = (ImageView)findViewById(R.id.imageView);
        ImageView image2 = (ImageView)findViewById(R.id.imageButtonGeri);
        image2.setVisibility(View.VISIBLE);
        String meyve = "";
        if(sira == 5) {
            finish();
        }
        if(meyvesayi == 0) {
            switch (sira) {
                case 0:
                    meyve = "1 tane armut";
                    image.setImageResource(R.drawable.armut);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane armut";
                    image.setImageResource(R.drawable.ikiarmut);
                    break;
                case 2:
                    meyve = "3 tane armut";
                    image.setImageResource(R.drawable.ucarmut);
                    break;
                case 3:
                    meyve = "4 tane armut";
                    image.setImageResource(R.drawable.dortarmut);
                    break;
                case 4:
                    meyve = "5 tane armut";
                    image.setImageResource(R.drawable.besarmut);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 1) {
            switch (sira) {
                case 0:
                    meyve = "1 tane elma";
                    image.setImageResource(R.drawable.elma);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane elma";
                    image.setImageResource(R.drawable.ikielma);
                    break;
                case 2:
                    meyve = "3 tane elma";
                    image.setImageResource(R.drawable.ucelma);
                    break;
                case 3:
                    meyve = "4 tane elma";
                    image.setImageResource(R.drawable.dortelma);
                    break;
                case 4:
                    meyve = "5 tane elma";
                    image.setImageResource(R.drawable.beselma);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 2) {
            switch (sira) {
                case 0:
                    meyve = "1 tane kayısı";
                    image.setImageResource(R.drawable.kayisi);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane kayısı";
                    image.setImageResource(R.drawable.ikikayisi);
                    break;
                case 2:
                    meyve = "3 tane kayısı";
                    image.setImageResource(R.drawable.uckayisi);
                    break;
                case 3:
                    meyve = "4 tane kayısı";
                    image.setImageResource(R.drawable.dortkayisi);
                    break;
                case 4:
                    meyve = "5 tane kayısı";
                    image.setImageResource(R.drawable.beskayisi);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 3) {
            switch (sira) {
                case 0:
                    meyve = "1 tane çilek";
                    image.setImageResource(R.drawable.cilek);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane çilek";
                    image.setImageResource(R.drawable.ikicilek);
                    break;
                case 2:
                    meyve = "3 tane çilek";
                    image.setImageResource(R.drawable.uccilek);
                    break;
                case 3:
                    meyve = "4 tane çilek";
                    image.setImageResource(R.drawable.dortcilek);
                    break;
                case 4:
                    meyve = "5 tane çilek";
                    image.setImageResource(R.drawable.bescilek);
                    break;

                default:
                    meyve = "";
            }
        }

        else if(meyvesayi == 4) {
            switch (sira) {
                case 0:
                    meyve = "1 tane muz";
                    image.setImageResource(R.drawable.muz);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane muz";
                    image.setImageResource(R.drawable.ikimuz);
                    break;
                case 2:
                    meyve = "3 tane muz";
                    image.setImageResource(R.drawable.ucmuz);
                    break;
                case 3:
                    meyve = "4 tane muz";
                    image.setImageResource(R.drawable.dortmuz);
                    break;
                case 4:
                    meyve = "5 tane muz";
                    image.setImageResource(R.drawable.besmuz);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 5) {
            switch (sira) {
                case 0:
                    meyve = "1 tane nar";
                    image.setImageResource(R.drawable.nar);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane nar";
                    image.setImageResource(R.drawable.ikinar);
                    break;
                case 2:
                    meyve = "3 tane nar";
                    image.setImageResource(R.drawable.ucnar);
                    break;
                case 3:
                    meyve = "4 tane nar";
                    image.setImageResource(R.drawable.dortnar);
                    break;
                case 4:
                    meyve = "5 tane nar";
                    image.setImageResource(R.drawable.besnar);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 6) {
            switch (sira) {
                case 0:
                    meyve = "1 tane portakal";
                    image.setImageResource(R.drawable.portakal);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane portakal";
                    image.setImageResource(R.drawable.ikiportakal);
                    break;
                case 2:
                    meyve = "3 tane portakal";
                    image.setImageResource(R.drawable.ucportakal);
                    break;
                case 3:
                    meyve = "4 tane portakal";
                    image.setImageResource(R.drawable.dortportakal);
                    break;
                case 4:
                    meyve = "5 tane portakal";
                    image.setImageResource(R.drawable.besportakal);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 7) {
            switch (sira) {
                case 0:
                    meyve = "1 tane erik";
                    image.setImageResource(R.drawable.erik);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane erik";
                    image.setImageResource(R.drawable.ikierik
                    );
                    break;
                case 2:
                    meyve = "3 tane erik";
                    image.setImageResource(R.drawable.ucerik);
                    break;
                case 3:
                    meyve = "4 tane erik";
                    image.setImageResource(R.drawable.dorterik);
                    break;
                case 4:
                    meyve = "5 tane erik";
                    image.setImageResource(R.drawable.beserik);
                    break;

                default:
                    meyve = "";
            }
        }
        else if(meyvesayi == 8) {
            switch (sira) {
                case 0:
                    meyve = "1 tane karpuz";
                    image.setImageResource(R.drawable.karpuz);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane karpuz";
                    image.setImageResource(R.drawable.ikikarpuz);
                    break;
                case 2:
                    meyve = "3 tane karpuz";
                    image.setImageResource(R.drawable.uckarpuz);
                    break;
                case 3:
                    meyve = "4 tane karpuz";
                    image.setImageResource(R.drawable.dortkarpuz);
                    break;
                case 4:
                    meyve = "5 tane karpuz";
                    image.setImageResource(R.drawable.beskarpuz);
                    break;

                default:
                    meyve = "";
            }
        }
        else {
            switch (sira) {
                case 0:
                    meyve = "1 tane şeftali";
                    image.setImageResource(R.drawable.seftali);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    meyve = "2 tane şeftali";
                    image.setImageResource(R.drawable.ikiseftali);
                    break;
                case 2:
                    meyve = "3 tane şeftali";
                    image.setImageResource(R.drawable.ucseftali);
                    break;
                case 3:
                    meyve = "4 tane şeftali";
                    image.setImageResource(R.drawable.dortseftali);
                    break;
                case 4:
                    meyve = "5 tane şeftali";
                    image.setImageResource(R.drawable.besseftali);
                    break;

                default:
                    meyve = "";
            }
        }
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(meyve);
        MetniSeslendir(textView.getText().toString());
        sira++;
    }

    public void onceki_sayfa(View view) {
        sira = sira - 2;
        sonraki_sayfa(view);
    }
}

package com.example.cengiz.cengo;

        import android.annotation.TargetApi;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Build;
        import android.speech.tts.TextToSpeech;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.HashMap;
        import java.util.Locale;

public class sayiOgretme extends AppCompatActivity {
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
            if (status == TextToSpeech.SUCCESS) {
                flag = true;
                Locale locale = new Locale("tr", "TR");


                if(tts.isLanguageAvailable(locale) >= 0) {
                    tts.setLanguage(locale);
                    tts.setPitch(0.8F);
                    tts.setSpeechRate(1.5F);
                    MetniSeslendir("Sıfır");
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
    @SuppressWarnings("deprecation")
    private void ttsUnder20(String text){
        HashMap<String, String> map = new HashMap<>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "MessageId");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, map);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void ttsGreater21(String text) {
        String utteranceID = this.hashCode() + "";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceID);
    }


        private void MetniSeslendir(String metin) {
            if (tts != null && flag) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ttsGreater21(metin);
                }else{
                    ttsUnder20(metin);
                }

            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                tts = new TextToSpeech(this, new Initializer());
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            System.gc();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sayi_ogretme);

            bg = MediaPlayer.create(sayiOgretme.this, R.raw.muzik);
            bg.setLooping(true);
            bg.start();

            init();
        }

        @Override
        protected void onDestroy() {
            if (tts != null) {
                tts.stop();
                tts.shutdown();
            }
            super.onDestroy();
        }
        public void onceki_sayfa(View view) {
            sira = sira - 2;
            sonraki_sayfa(view);
        }
        public void sonraki_sayfa(View view) {

            System.gc();
            ImageView image = (ImageView) findViewById(R.id.imgSayi);
            ImageView image2 = (ImageView) findViewById(R.id.geri);
            image2.setVisibility(View.VISIBLE);
            String sayi;
            sira++;
            if (sira == 10) {
                finish();
            }
            switch (sira) {
                case 0:
                    sayi = "SIFIR";
                    image.setImageResource(R.drawable.sifir);
                    image2.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    sayi = "BİR";
                    image.setImageResource(R.drawable.bir);
                    break;
                case 2:
                    sayi = "İKİ";
                    image.setImageResource(R.drawable.iki);
                    break;
                case 3:
                    sayi = "ÜÇ";
                    image.setImageResource(R.drawable.uc);
                    break;
                case 4:
                    sayi = "DÖRT";
                    image.setImageResource(R.drawable.dort);
                    break;
                case 5:
                    sayi = "BEŞ";
                    image.setImageResource(R.drawable.bes);
                    break;
                case 6:
                    sayi = "ALTI";
                    image.setImageResource(R.drawable.alti);
                    break;
                case 7:
                    sayi = "YEDİ";
                    image.setImageResource(R.drawable.yedi);
                    break;
                case 8:
                    sayi = "SEKİZ";
                    image.setImageResource(R.drawable.sekiz);
                    break;
                case 9:
                    sayi = "DOKUZ";
                    image.setImageResource(R.drawable.dokuz);
                    break;
                default:
                    sayi = "";
            }
            TextView textView = (TextView) findViewById(R.id.textSayi);
            textView.setText(sayi);
            String metin = textView.getText().toString();
            if(metin.equals("SIFIR")) {
                metin = "sıfır";
            }
            else if(metin.equals("ALTI")) {
                metin = "altı";
            }
            MetniSeslendir(metin);
        }
    protected void onPause(){
        super.onPause();
        bg.release();
      //  finish();
    }
    }

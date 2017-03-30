package com.example.cengiz.cengo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import java.util.Locale;

public class RenkTamami extends AppCompatActivity {
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;
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
        setContentView(R.layout.activity_renk_tamami);
        init();
    }

    public void oku(View view) {
        switch (view.getId()) {
            case R.id.kirmizi:
                final View thumb1View = findViewById(R.id.kirmizi);
                zoomImageFromThumb(thumb1View, R.drawable.kirmizi);
                MetniSeslendir("Kırmızı");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.mavi:
                final View thumb2View = findViewById(R.id.mavi);
                zoomImageFromThumb(thumb2View, R.drawable.mavi);
                MetniSeslendir("Mavi");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.sari:
                final View thumb3View = findViewById(R.id.sari);
                zoomImageFromThumb(thumb3View, R.drawable.sari);
                MetniSeslendir("Sarı");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.yesil:
                final View thumb4View = findViewById(R.id.yesil);
                zoomImageFromThumb(thumb4View, R.drawable.yesil);
                MetniSeslendir("Yeşil");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.pembe:
                final View thumb5View = findViewById(R.id.pembe);
                zoomImageFromThumb(thumb5View, R.drawable.pembe);
                MetniSeslendir("Pembe");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.beyaz:
                final View thumb6View = findViewById(R.id.beyaz);
                zoomImageFromThumb(thumb6View, R.drawable.beyaz);
                MetniSeslendir("Beyaz");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.turuncu:
                final View thumb7View = findViewById(R.id.turuncu);
                zoomImageFromThumb(thumb7View, R.drawable.turuncu);
                MetniSeslendir("Turuncu");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.mor:
                final View thumb8View = findViewById(R.id.mor);
                zoomImageFromThumb(thumb8View, R.drawable.mor);
                MetniSeslendir("Mor");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.siyah:
                final View thumb9View = findViewById(R.id.siyah);
                zoomImageFromThumb(thumb9View, R.drawable.siyah);
                MetniSeslendir("Siyah");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
            case R.id.kahverengi:
                final View thumb10View = findViewById(R.id.kahverengi);
                zoomImageFromThumb(thumb10View, R.drawable.kahverengi);
                MetniSeslendir("Kahverengi");
                mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
                break;
        }
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.activity_renk_tamami).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);


        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {

            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {

            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }


        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);


        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);


        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top,
                        finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;


        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }


                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}

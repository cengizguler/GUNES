package com.example.cengiz.cengo;

        import android.content.Context;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Renkler extends AppCompatActivity implements View.OnClickListener {

    Button renkOgren;
    Button renkOyna;
    Button renkTum;
    MediaPlayer bg;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.gc();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renkler);

        bg = MediaPlayer.create(Renkler.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

        renkOgren = (Button) findViewById(R.id.renk_ogren);
        renkOgren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, RenkOgretme.class);
                startActivity(intent);
            }
        });

        renkOyna = (Button)findViewById(R.id.renk_testet);
        renkOyna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, RenkOyun.class);
                startActivity(intent);
            }
        });

        renkTum = (Button)findViewById(R.id.tumRenkler);
        renkTum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, RenkTamami.class);
                startActivity(intent);
            }
        });
    }



    public void onClick(View v) {

    }
    protected void onPause(){
        super.onPause();
        bg.release();
       // finish();
    }
}

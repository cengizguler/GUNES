package com.example.cengiz.cengo;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MeyveEsleme extends Activity implements View.OnDragListener, OnTouchListener
{
    MediaPlayer bg;
    private static final String TAG = MeyveEsleme.class.getSimpleName();

    private final String IMAGE_FIRST_TAG = "ilk resim";
    private final String IMAGE_SECOND_TAG = "ikinci resim";

    private ImageView dragViewFirst;
    private ImageView dragViewSecond;
    private ImageView dropView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meyveesleme);

        bg = MediaPlayer.create(MeyveEsleme.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();

        dragViewFirst = (ImageView) findViewById(R.id.firstImage);
        dragViewSecond = (ImageView) findViewById(R.id.secondImage);
        dropView = (ImageView) findViewById(R.id.dropImage);

        dragViewFirst.setTag(IMAGE_FIRST_TAG);
        dragViewSecond.setTag(IMAGE_SECOND_TAG);

        dragViewFirst.setOnTouchListener(this);
        dragViewSecond.setOnTouchListener(this);

        dropView.setOnDragListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        String tag = v.getTag().toString();

        View.DragShadowBuilder mShadow;

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                ClipData data = ClipData.newPlainText("some label", tag);

                if (tag.equals(IMAGE_FIRST_TAG))
                {
                    dropView.setImageResource(R.drawable.elma);
                    mShadow = new View.DragShadowBuilder(dragViewFirst);
                }
                else
                {
                    dropView.setImageResource(R.drawable.armut);
                    mShadow = new View.DragShadowBuilder(dragViewSecond);
                }

                v.startDrag(data, mShadow, null, 0);
                break;

            case MotionEvent.ACTION_UP:
                v.performClick();

                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event)
    {
        Log.d(TAG, "onDrag");
        final int action = event.getAction();

        switch (action)
        {
            case DragEvent.ACTION_DRAG_STARTED:

                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                ((ImageView) v).setColorFilter(Color.LTGRAY);
                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:

                ((ImageView) v).setColorFilter(Color.YELLOW);
                v.invalidate();

                return true;

            case DragEvent.ACTION_DROP:

                ClipData dragData = event.getClipData();

                final String tag = dragData.getItemAt(0).getText().toString();

                Toast.makeText(MeyveEsleme.this, "Resim yerleşti " + tag, Toast.LENGTH_SHORT).show();

                ((ImageView) v).clearColorFilter();

                v.invalidate();

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                ((ImageView) v).clearColorFilter();

                v.invalidate();
                if (event.getResult())
                {
                    Toast.makeText(MeyveEsleme.this, "Doğru !", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MeyveEsleme.this, "Olmadı tekrar dene !", Toast.LENGTH_SHORT).show();
                    dropView.setImageBitmap(null);
                }

                return true;

            default:
                break;
        }

        return false;
    }
    protected void onPause(){
        super.onPause();
        bg.release();
        //   finish();
    }
}

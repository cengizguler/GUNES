package com.example.cengiz.cengo;


        import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
        import android.media.Image;
        import android.media.MediaPlayer;
        import android.os.Build;
        import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
        import android.view.View.OnDragListener;
        import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

public class sayiOyunu extends AppCompatActivity implements OnDragListener, View.OnLongClickListener ////// {
{
    private static final String TAG = "junk";
    private ImageView imgBir;
    private ImageView imgiki;
    private ImageView imguc;
    private ImageView imgHedef;
    private ViewGroup rootLayout;
    private int _xDelta;
    private int _yDelta;
    Button tum;
    Button ogren;
    final Context context = this;
    MediaPlayer bg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayi_oyunu);
        bg = MediaPlayer.create(sayiOyunu.this, R.raw.muzik);
        bg.setLooping(true);
        bg.start();


       imgBir =  (ImageView) findViewById(R.id.imageBir);
        imgiki = (ImageView) findViewById(R.id.imageiki);
        imguc = (ImageView) findViewById(R.id.imageuc);
        imgHedef =(ImageView)  findViewById(R.id.hedef1);

        imgBir.setOnLongClickListener(this);
        imgiki.setOnLongClickListener(this);
        imguc.setOnLongClickListener(this);
        imgHedef.setOnDragListener(this);

        findViewById(R.id.firstLayout).setOnDragListener(this);
        findViewById(R.id.secondLayout).setOnDragListener(this);
      /*  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayi_oyunu);
        imgBir = (ImageView) findViewById(R.id.imageBir);
        //imgBir.setOnTouchListener(this);


        imgHedef1 = (ImageView) findViewById(R.id.hedef1);
        imgHedef2 = (ImageView) findViewById(R.id.hedef2);

        imgBir.setOnLongClickListener(longclickListener);
        imgHedef1.setOnDragListener(draglistenre);
        imgHedef2.setOnDragListener(draglistenre);
        imgBir.setLongClickable(true);
        imgBir.setClickable(true);*/
    }//ONCREATE

    public boolean onLongClick (View imageView) {
        ClipData clipData = ClipData.newPlainText("","");
        View.DragShadowBuilder sb = new View.DragShadowBuilder(imageView);
        imageView.startDrag(clipData, sb, imageView, 0);
        imageView.setVisibility(View.INVISIBLE);
        return true;
    }
    public boolean onDrag(View receivingLayoutView, DragEvent dragEvent) {
        View draggedImageView = (View) dragEvent.getLocalState();

        // Handles each of the expected events
        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.i(TAG, "drag action started");

                // Determines if this View can accept the dragged data
                if (dragEvent.getClipDescription()
                        .hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    Log.i(TAG, "Can accept this data");

                    // returns true to indicate that the View can accept the dragged data.
                    return true;

                } else {
                    Log.i(TAG, "Can not accept this data");

                }

                // Returns false. During the current drag and drop operation, this View will
                // not receive events again until ACTION_DRAG_ENDED is sent.
                return false;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i(TAG, "drag action entered");
//                the drag point has entered the bounding box
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.i(TAG, "drag action location");
                /*triggered after ACTION_DRAG_ENTERED
                stops after ACTION_DRAG_EXITED*/
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.i(TAG, "drag action exited");
//                the drag shadow has left the bounding box
                return true;

            case DragEvent.ACTION_DROP:
                  /* the listener receives this action type when
                  drag shadow released over the target view
            the action only sent here if ACTION_DRAG_STARTED returned true
            return true if successfully handled the drop else false*/
                switch (draggedImageView.getId()) {
                    case R.id.imageiki:
                        Log.i(TAG, "ikş");
                        return false;
                    case R.id.imageBir:
                        Log.i(TAG, "bir");
                        ViewGroup draggedImageViewParentLayout
                                = (ViewGroup) draggedImageView.getParent();
                        draggedImageViewParentLayout.removeView(draggedImageView);
                        LinearLayout bottomLinearLayout = (LinearLayout) receivingLayoutView;
                        bottomLinearLayout.addView(draggedImageView);
                        draggedImageView.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.imageuc:
                        Log.i(TAG, "uc");
                        return false;
                    default:
                        Log.i(TAG, "in default");
                        return false;
                }

            case DragEvent.ACTION_DRAG_ENDED:

                Log.i(TAG, "drag action ended");
                Log.i(TAG, "getResult: " + dragEvent.getResult());

//                if the drop was not successful, set the ball to visible
                if (!dragEvent.getResult()) {
                    Log.i(TAG, "setting visible");
                    draggedImageView.setVisibility(View.VISIBLE);
                }

                return true;
            // An unknown action type was received.
            default:
                Log.i(TAG, "Unknown action type received by OnDragListener.");
                break;
        }
        return false;
    }
   /* View.OnLongClickListener longclickListener = new View.OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
           // ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
          // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
             //   v.startDragAndDrop(data, myShadowBuilder, v,0);
            //}
           // else{
                v.startDrag(null, myShadowBuilder, v,0);
            v.setVisibility(View.INVISIBLE);
            //}
            return true;
        }
    };

   // OnDragListener draglistenre = new OnDragListener(){
        public boolean onDrag(View v, DragEvent event){
            int dragEvent = event.getAction();
            switch(dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    final View view = (View)event.getLocalState();

                    if(view.getId()== R.id.imageBir){
                        imgHedef1.setImageResource(R.drawable.tick);
                    }



                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };
/*

   /* float x,y = 0.0f;
    boolean moving = false;

    public boolean onTouch (View arg0, MotionEvent arg1) {
        switch (arg1.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moving = true;
                break;

            case MotionEvent.ACTION_MOVE:
                if (moving) {
                    x = arg1.getRawX() - imgBir.getWidth() / 2;
                    y = arg1.getRawY() - imgBir.getHeight() * 3 / 2;
                    imgBir.setX(x);
                    imgBir.setY(y);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                moving = false;
                break;



        }
        return true;
    }
*/
   protected void onPause(){
       super.onPause();
       bg.release();
       //finish();
   }
}
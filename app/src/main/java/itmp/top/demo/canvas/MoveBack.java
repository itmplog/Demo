package itmp.top.demo.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import itmp.top.demo.R;


public class MoveBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //setContentView(R.layout.activity_move_back);
        setContentView(new MyView(this));
    }

    class MyView extends View {
        final int BACK_HEIGHT = 1920*2;
        private Bitmap back;
        private Bitmap plane;
        final int WIDTH = 1080;
        final int HEIGHT = 1920;
        private int startY = BACK_HEIGHT - HEIGHT;

        public MyView(Context context){
            super(context);

            back = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
            plane = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);

            final Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);
                    if(startY <= 0){
                        startY = BACK_HEIGHT - HEIGHT;
                    } else {
                        startY -= 30;
                    }
                    invalidate();
                }
            };

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0x123);
                }
            }, 0, 100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            Bitmap bitmap2 = Bitmap.createBitmap(back, 0, startY, WIDTH, HEIGHT);
            canvas.drawBitmap(bitmap2, 0, 0, null);
            canvas.drawBitmap(plane, 160, 380, null);
        }
    }
}

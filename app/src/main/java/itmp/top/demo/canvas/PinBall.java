package itmp.top.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//import itmp.top.demo.R;

public class PinBall extends AppCompatActivity {

    private int tableWidth = 0;
    private int tableHeight = 0;
    private int racketY = 0;
    private final int RACKET_HEIGHT = 25;
    private final int RACKET_WIDTH = 255;
    private final int BALL_SIZE = 40;
    private int ySpeed = 25;
    Random random = new Random();
    private double xyRate = random.nextDouble() - 0.5;
    private int xSpeed = (int)(ySpeed * xyRate * 2);

    private int ballX = random.nextInt(200) + 20;
    private int ballY = random.nextInt(10) + 20;

    private int racketX = random.nextInt(200);
    private boolean isLose = false;

    private float tx = 0;
    private float rect = 0;

    protected void onCreate(Bundle savedInstanceState) {
        // 去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pin_ball);

        final GameView gameView = new GameView(this);
        setContentView(gameView);

        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        tableHeight = displayMetrics.heightPixels;
        //tableHeight = 1920;
        tableHeight = displayMetrics.heightPixels;
        tableWidth = displayMetrics.widthPixels;

        racketY = tableHeight - 80;
        Log.v("rect", tableHeight + "");
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                //super.handleMessage(msg);
                if(msg.what == 0x123){
                    gameView.invalidate();
                }
            }
        };


        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //return false;

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tx = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        rect = event.getX() - tx;
                        Log.v("rect", "Down: " + tx + "\tUp: " + event.getX() + "\t rect: " + rect);

                        if(rect > 0 && rect >= 200){
                            racketX += 100;
                        }
                        if(rect < 0 && rect <= -200){
                            racketX -= 100;
                        }
                }
                return true;
            }
        });

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE) {
                    xSpeed = -xSpeed;
                }
                if (ballY >= racketY - BALL_SIZE &&
                        (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
                    timer.cancel();
                    isLose = true;
                } else if (ballY <= 0 || (
                        ballY >= racketY - BALL_SIZE
                                && ballX > racketX && ballX <= racketX + RACKET_WIDTH
                )) {
                    ySpeed = -ySpeed;
                }
                ballY += ySpeed;
                ballX += xSpeed;
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 100);

    }

    public class GameView extends View {
        Paint paint = new Paint();
        public GameView(Context context){
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            if (isLose) {
                paint.setTextSize(40);

                canvas.drawText("游戏已结束", 50, 200, paint);

            } else {
                paint.setColor(Color.rgb(222, 11, 22));
                canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);

                paint.setColor(Color.rgb(80, 80, 200));
                canvas.drawRect(racketX, racketY, racketX + RACKET_WIDTH, racketY + RACKET_HEIGHT, paint);
            }
        }
    }

    @Override
    public void setTheme(int resid) {
        //super.setTheme(resid);
        super.setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat_NoActionBar);
    }
}


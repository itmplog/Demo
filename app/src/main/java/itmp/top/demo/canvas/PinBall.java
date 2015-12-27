package itmp.top.demo.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import itmp.top.demo.R;

public class PinBall extends AppCompatActivity {

    private int tableWidth = 0;
    private int tableHeight = 0;
    private int racketY = 0;
    private final int RACKET_HEIGHT = 20;
    private final int RACKET_WIDTH = 70;
    private final int BALL_SIZE = 12;
    private int ySpeed = 10;
    Random random = new Random();
    private double xyRate = random.nextDouble() - 0.5;
    private int xSpeed = (int)(ySpeed * xyRate * 2);

    private int ballX = random.nextInt(200) + 20;
    private int ballY = random.nextInt(10) + 20;

    private int racketX = random.nextInt(200);
    private boolean isLose = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pin_ball);

    }
}

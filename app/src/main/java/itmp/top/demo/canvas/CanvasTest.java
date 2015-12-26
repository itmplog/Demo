package itmp.top.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import itmp.top.demo.R;

public class CanvasTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_canvas_test);
        setContentView(new CanvasView(this));
    }

    public class CanvasView extends View{
        float phase;
        PathEffect[] effects = new PathEffect[7];
        int[] colors;
        private Paint paint;
        Path path = null;

        public CanvasView(Context context){
            super(context);
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);

            path = new Path();
            path.moveTo(0, 0);
            for(int i = 1; i <= 15; i++){
                path.lineTo((float)Math.random()*1080, (float)Math.random() * 1500); // 0.0 - 60.0
            }

            colors = new int[]{Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN,
                                Color.MAGENTA, Color.RED, Color.YELLOW};
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            effects[0] = null;
            effects[1] = new CornerPathEffect(10);
            effects[2] = new DiscretePathEffect(3.0f, 5.0f);
            effects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);
            Path p = new Path();
            p.addRect(0, 0, 8, 8, Path.Direction.CCW);

            effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);

            effects[5] = new ComposePathEffect(effects[2], effects[4]);
            effects[6] = new SumPathEffect(effects[4], effects[3]);
            canvas.translate(8, 8);

            for(int i = 0; i < effects.length; i++){
                paint.setPathEffect(effects[i]);
                paint.setColor(colors[i]);
                canvas.drawPath(path, paint);
                canvas.translate(0, 40);
            }
            phase += 1;
            invalidate();
        }
    }
}

package itmp.top.demo.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import itmp.top.demo.R;

public class MatrixView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matrix_view);
        setContentView(new MatrixViewTest(this));
    }

    public class MatrixViewTest extends View {
        private Bitmap bitmap = null;
        private Matrix matrix = new Matrix();
        private float sx = 0.0f;
        private int width = 0, height = 0;
        private float scale = 1.0f;
        private boolean isScale = false;

        private float down = 0;
        private float down1 = 0;
        private  float up = 0;
        private float up1 = 0;


        public MatrixViewTest(Context context){
            super(context);
            bitmap = ((BitmapDrawable)context.getResources().getDrawable(android.R.drawable.sym_def_app_icon)).getBitmap();
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            this.setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            matrix.reset();
            if(!isScale){
                matrix.setSkew(sx, 0);
            } else {
                matrix.setScale(scale, scale);
            }

            Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
            canvas.drawBitmap(bitmap1, matrix, null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //return super.onTouchEvent(event);
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    down = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    up = event.getY();
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    down1 = event.getY();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    up1 = event.getY();

                    if(up > down && up1 < down1){
                        sx += 0.1f;
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}

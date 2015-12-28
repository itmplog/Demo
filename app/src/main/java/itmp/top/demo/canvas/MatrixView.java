package itmp.top.demo.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import itmp.top.demo.R;

public class MatrixView extends AppCompatActivity {

    private MatrixViewTest matrixViewTest = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_matrix_view);

        matrixViewTest = new MatrixViewTest(this);
        setContentView(matrixViewTest);
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

        private Matrix savedMatrix = new Matrix();
        private float[] start = null;

        public MatrixViewTest(Context context){
            super(context);
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test);
            //((BitmapDrawable)context.getResources().getDrawable(android.R.drawable.sym_def_app_icon)).getBitmap();
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
                    matrix.set(matrixViewTest.getMatrix());
                    savedMatrix.set(matrix);
                    start[0] = event.getX();
                    start[1] = event.getY();
                    //Log.d(TAG, "mode=DRAG");
                    //mode = DRAG;
                    //Log.d(TAG, "mode=NONE");
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    //oldDist = spacing(event);
                    //Log.d(TAG, "oldDist=" + oldDist);
                    //if (oldDist > 10f) {
                        savedMatrix.set(matrix);
                        //midPoint(mid, event);
                        //mode = ZOOM;
                        //Log.d(TAG, "mode=ZOOM");
                    // ACTION_DOWN, ACTION_MOVE, ACTION_UP, or ACTION_CANCEL.
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    //mode = NONE;
                    break;
                case MotionEvent.ACTION_MOVE:
                    //if (mode == DRAG) {
                        // ...
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - start[0], event.getY()
                                - start[1]);
                   // } else if (mode == ZOOM) {
                        //float newDist = spacing(event);
                        //Log.d(TAG, "newDist=" + newDist);
                        //if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            float scale = 0.1f;
                            matrix.postScale(scale, scale,start[0], start[1]);
                        //}
                    }

                /*
                case MotionEvent.ACTION_DOWN:
                    down = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    down1 = event.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    up = event.getY();
                case MotionEvent.ACTION_POINTER_UP:
                    up1 = event.getY();

                    Log.v("matrix", "up down: " + up + " " + down + "up1 down1: " + up1 +  " " + down1);
                    if(up > down && up1 < down1){
                        sx += 0.1f;
                    }
                    break;
                default:
                    break;

                    */

            return true;
        }
    }
}

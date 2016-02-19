package itmp.top.demo.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by hz on 2016/2/19.
 */
public class MyAnimation extends Animation {
    private float centerX;
    private float centerY;

    private int duration;
    private Camera camera = new Camera();

    public MyAnimation(float x, float y, int duration){
        this.centerX = x;
        this.centerY = y;
        this.duration = duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(duration);

        // 设置动画结束后 效果保留
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //super.applyTransformation(interpolatedTime, t);
        camera.save();

        camera.translate(100.0f - 100.0f * interpolatedTime,
                150.0f * interpolatedTime - 150,
                400.0f - 400.0f * interpolatedTime);

        //camera.rotateX(360 * (interpolatedTime));
        //camera.rotateY(360 * interpolatedTime);

        Matrix matrix = t.getMatrix();
        camera.getMatrix(matrix);
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
        camera.restore();
    }
}

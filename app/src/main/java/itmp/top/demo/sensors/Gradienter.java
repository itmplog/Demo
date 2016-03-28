package itmp.top.demo.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import itmp.top.demo.R;

public class Gradienter extends AppCompatActivity {

    private CustomView view;
    final int MAX_ANGLE = 30;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradienter);
        view = (CustomView)findViewById(R.id.custom);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onPause();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(sensorEventListener);
        super.onStop();
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            final float[] values = event.values;
            switch (event.sensor.getType()){
                case Sensor.TYPE_ORIENTATION:
                    float yAngle = values[1];
                    // 获取与Z轴的夹角
                    float zAngle = values[2];
                    Log.v("xy", values[1] + "" + values[2]);
                    // 气泡位于中间时（水平仪完全水平），气泡的X、Y坐标
                    int x = (view.back.getWidth() - view.bubble.getWidth()) / 2;
                    int y = (view.back.getHeight() - view.bubble.getHeight()) / 2;
                    // 如果与Z轴的倾斜角还在最大角度之内
                    if (Math.abs(zAngle) <= MAX_ANGLE)
                    {
                        // 根据与Z轴的倾斜角度计算X坐标的变化值
                        // （倾斜角度越大，X坐标变化越大）
                        int deltaX = (int) ((view.back.getWidth() - view.bubble
                                .getWidth()) / 2 * zAngle / MAX_ANGLE);
                        x += deltaX;
                    }
                    // 如果与Z轴的倾斜角已经大于MAX_ANGLE，气泡应到最左边
                    else if (zAngle > MAX_ANGLE)
                    {
                        x = 0;
                    }
                    // 如果与Z轴的倾斜角已经小于负的MAX_ANGLE，气泡应到最右边
                    else
                    {
                        x = view.back.getWidth() - view.bubble.getWidth();
                    }
                    // 如果与Y轴的倾斜角还在最大角度之内
                    if (Math.abs(yAngle) <= MAX_ANGLE)
                    {
                        // 根据与Y轴的倾斜角度计算Y坐标的变化值
                        // （倾斜角度越大，Y坐标变化越大）
                        int deltaY = (int) ((view.back.getHeight() - view.bubble
                                .getHeight()) / 2 * yAngle / MAX_ANGLE);
                        y += deltaY;
                    }
                    // 如果与Y轴的倾斜角已经大于MAX_ANGLE，气泡应到最下边
                    else if (yAngle > MAX_ANGLE)
                    {
                        y = view.back.getHeight() - view.bubble.getHeight();
                    }
                    // 如果与Y轴的倾斜角已经小于负的MAX_ANGLE，气泡应到最右边
                    else
                    {
                        y = 0;
                    }
                    // 如果计算出来的X、Y坐标还位于水平仪的仪表盘内，更新水平仪的气泡坐标
                    if (isContain(x, y))
                    {
                        view.bubbleX = x;
                        view.bubbleY = y;
                    }
                    // 通知系统重回MyView组件

                    //Log.v("xy", x + " " + y);
                    view.postInvalidate();
                    break;
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    // 计算x、y点的气泡是否处于水平仪的仪表盘内
    private boolean isContain(int x, int y)
    {
        // 计算气泡的圆心坐标X、Y
        int bubbleCx = x + view.bubble.getWidth() / 2;
        int bubbleCy = y + view.bubble.getWidth() / 2;
        // 计算水平仪仪表盘的圆心坐标X、Y
        int backCx = view.back.getWidth() / 2;
        int backCy = view.back.getWidth() / 2;
        // 计算气泡的圆心与水平仪仪表盘的圆心之间的距离
        double distance = Math.sqrt((bubbleCx - backCx) * (bubbleCx - backCx)
                + (bubbleCy - backCy) * (bubbleCy - backCy));
        // 若两个圆心的距离小于它们的半径差，即可认为处于该点的气泡依然位于仪表盘内
        if (distance < (view.back.getWidth() - view.bubble.getWidth()) / 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

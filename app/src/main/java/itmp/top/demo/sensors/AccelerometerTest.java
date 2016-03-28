package itmp.top.demo.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import itmp.top.demo.R;

public class AccelerometerTest extends AppCompatActivity implements SensorEventListener{

    private TextView textView;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);

        textView = (TextView)findViewById(R.id.textView);
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        StringBuilder  stringBuilder = new StringBuilder();
        stringBuilder.append("X 方向上的加速度: ");
        stringBuilder.append(values[0]);
        stringBuilder.append("\nY方向上的加速度: ");
        stringBuilder.append(values[1]);
        stringBuilder.append("\nZ方向上的加速度: ");
        stringBuilder.append(values[2]);
        textView.setText(stringBuilder.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

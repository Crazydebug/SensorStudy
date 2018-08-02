package com.example.administrator.sensorstudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *2018/8/2 16/02/50
 * Direction：对压力传感器数据进行显示
 * 返回值为当前的压强
 *1.显示当前压力是多少
 *@user：DemoVei
 */

public class PressureSensorActivity extends AppCompatActivity {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private MySensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_sensor);

        textView = findViewById(R.id.text_view);

        //获取管理器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //注册压力监听器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        listener = new MySensorEventListener();
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            String reslut = "当前压力为：" + values[0];
            textView.setText(reslut);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(listener);
        listener = null;
    }
}

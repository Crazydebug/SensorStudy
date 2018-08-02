package com.example.administrator.sensorstudy;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *2018/8/2 15/23/50
 * Direction：对方向传感器数据进行显示
 * 代码中event的value值0=North, 90=East, 180=South, 270=West
 *1.显示角度为多少 0=North, 90=East, 180=South, 270=West
 *@user：DemoVei
 */

public class DirectionSensorActivity extends AppCompatActivity {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private sensorlistener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction_sensor);
        textView = findViewById(R.id.text_view);

        //获取管理器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //获取方向感应器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        listener = new sensorlistener();
        sensorManager.registerListener(listener, sensor,sensorManager.SENSOR_DELAY_UI);
    }

    private class  sensorlistener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //0=North, 90=East, 180=South, 270=West
            float value = sensorEvent.values[0];

            String result = "角度为：" + value;

            textView.setText(result);
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
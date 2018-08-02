package com.example.administrator.sensorstudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 *2018/8/2 16/11/50
 * Direction：对陀螺仪传感器数据进行显示
 * 返回值为x,y,z轴上得磁场强度
 *@user：DemoVei
 */

public class GyroscopeSensorActivity extends AppCompatActivity {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private MySensorEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_sensor);

        textView = findViewById(R.id.text_view);

        //获取管理器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //注册陀螺仪
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        listener = new MySensorEventListener();
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final class MySensorEventListener implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            String reslut =  "x方向上得磁场强度为：" + values[0] + "\n"
                            +"y方向上得磁场强度为：" + values[1] + "\n"
                            +"z方向上得磁场强度为：" + values[2];
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
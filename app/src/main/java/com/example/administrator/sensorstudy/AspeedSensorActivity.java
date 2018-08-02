package com.example.administrator.sensorstudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 2018/8/1 23/30/15
 * Direction：对加速度传感器数据进行显示
 * 代码中event的value值分别代表x，y，z轴的值
 *1.显示加速度传感器的x，y，z轴的数据
 *为了测量设备的实际加速度，必须消除重力的影响。
 *这可以用过高通滤波器来实现。相反的，低通滤波器和一用来分离重力。
 *2.使用加速度滤波器//用来忽略重力的影响
 *显示x,y,z轴的数据
 *@user：DemoVei
 */

public class AspeedSensorActivity extends AppCompatActivity {

    private TextView textView;
    private SensorManager sensorManager;
    private Sensor sensor;
    private MySensorEventListener listener;
    private float[] gravity = new float[3];
    private float[] linear_acceleration = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_as);


        textView = findViewById(R.id.text_view);

        //获取管理器
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //获取加速度感应器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        MySensorEventListener listener = new MySensorEventListener();
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private final class MySensorEventListener implements SensorEventListener {
        //传感器实时变化值
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                /*
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                String result = "X= " + x + "\n"
                              + "Y= " + y + "\n"
                              + "Z= " + z;
                textView.setText(result);
                */

                /*
                    为了测量设备的实际加速度，必须消除重力的影响。
                    这可以用过高通滤波器来实现。相反的，低通滤波器和一用来分离重力。
                 */
                final float alpha = 0.8f;

                gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
                gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
                gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

                linear_acceleration[0] = event.values[0] - gravity[0];
                linear_acceleration[1] = event.values[1] - gravity[1];
                linear_acceleration[2] = event.values[2] - gravity[2];

                String result = "X= " + linear_acceleration[0] + "\n"
                        + "Y= " + linear_acceleration[1] + "\n"
                        + "Z= " + linear_acceleration[2];
                textView.setText(result);
            }
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
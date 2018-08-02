package com.example.administrator.sensorstudy;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 *2018/8/1 16/45/26
 * Direction：对光线传感器数据进行显示
 * 1.打开软件判断此时Android手机是否为自适应屏幕亮度
 *     如果手机开启的是自适应亮度则图标是亮的否则是暗的
 * 2.打开软件时显示此时的年/月/日  时/分/秒
 * 3.显示当前光线传感器的光线强度数据
 *@user：DemoVei
 */

public class LightSensorActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,SensorEventListener {

    private CheckBox ck_bright;
    private TextView tv_light;
    private SensorManager mSensroMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        ck_bright = findViewById(R.id.ck_bright);
        if (SwitchUtil.getAutoBrightStatus(this) == true) {
            ck_bright.setChecked(true);
        }
        ck_bright.setOnCheckedChangeListener(this);
        tv_light = findViewById(R.id.tv_light);
        //调用系统传感器
        mSensroMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensroMgr.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensroMgr.registerListener(this, mSensroMgr.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            float light_strength = sensorEvent.values[0];
            tv_light.setText(Utils.getNowDateTimeFormat() + "\n当前光线强度为" + light_strength);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        //当传感器精度改变时回调该方法，一般无需处理
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.ck_bright) {
            SwitchUtil.setAutoBrightStatus(this, isChecked);
        }
    }
}
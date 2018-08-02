package com.example.administrator.sensorstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * 2018/8/2 15/14/23
 * Direction：使用了光线传感器、加速度传感器、方向传感器
 *@user：DemoVei
 */

public class MainActivity extends AppCompatActivity{

    private Button btn_light;       //光线传感器
    private Button btn_aspeed;      //加速度传感器
    private Button btn_direction;   //方向传感器
    private Button btn_pressure;    //压力传感器
    private Button btn_gyroscope;   //陀螺仪传感器
    private Button btn_temperature;   //温度传感器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_light = findViewById(R.id.btn_light);           //光线传感器
        btn_aspeed = findViewById(R.id.btn_aspeed);         //加速度传感器
        btn_direction = findViewById(R.id.btn_direction);   //方向传感器
        btn_pressure = findViewById(R.id.btn_pressure);   //压力传感器
        btn_gyroscope = findViewById(R.id.btn_gyroscope);   //陀螺仪传感器
        btn_temperature = findViewById(R.id.btn_temperature);   //温度传感器

        //光线传感器
        btn_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(MainActivity.this,LightSensorActivity.class);
                startActivity(intent_1);
            }
        });

        //加速度传感器
        btn_aspeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_2 = new Intent(MainActivity.this,AspeedSensorActivity.class);
                startActivity(intent_2);
            }
        });

        //方向传感器
        btn_direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_3 = new Intent(MainActivity.this,DirectionSensorActivity.class);
                startActivity(intent_3);
            }
        });

        //压力传感器
        btn_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_4 = new Intent(MainActivity.this,PressureSensorActivity.class);
                startActivity(intent_4);
            }
        });

        //陀螺仪传感器
        btn_gyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_5 = new Intent(MainActivity.this,GyroscopeSensorActivity.class);
                startActivity(intent_5);
            }
        });

        //温度传感器
        btn_temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_6 = new Intent(MainActivity.this,TemperatureSensorActivity.class);
                startActivity(intent_6);
            }
        });


    }

}

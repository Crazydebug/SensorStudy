package com.example.administrator.sensorstudy;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/8/1.
 */

public class Utils {

    //展示时间及格式设置

    public static String getNowDateTimeFormat() {
        String format = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat s_format = new SimpleDateFormat(format);
        Date d_date = new Date();
        String s_date = "";
        s_date = s_format.format(d_date);
        return s_date;
    }
}

package com.example.administrator.sensorstudy;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by Administrator on 2018/8/1.
 */

public class SwitchUtil {

    private static final String TAG = "SwitchUtil";

    // 获取亮度自动调节的状态
    public static boolean getAutoBrightStatus(Context ctx) {
        /**
         * 获得当前屏幕亮度的模式
         * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
         * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
         */
        int screenMode = Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        try {
            screenMode = Settings.System.getInt(ctx.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Exception e) {
            Log.d(TAG, "getAutoBrightStatus error: " + e.getMessage());
        }
        return screenMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC ? true : false;
    }

    // 设置亮度自动调节的开关
    public static void setAutoBrightStatus(Context ctx, boolean enabled) {
        int screenMode = (enabled == true) ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
                : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL;
        Settings.System.putInt(ctx.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, screenMode);
    }


}

package com.kunfei.bookshelf.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.kunfei.bookshelf.MApplication;

import java.lang.reflect.Method;

@SuppressLint("PrivateApi")
public class Jdread1 {
    // 通过反射获取 jdread1 背光亮度
    public static int getBrightness(){
        Context context = MApplication.getInstance().getApplicationContext();
        try {
            Class<?> classController = Class.forName("android.onyx.hardware.DeviceController");
            Method getLight = classController.getMethod("getFrontLightValue", Context.class);
            return (int)getLight.invoke(null, context);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 通过反射设置 jdread1 背光亮度
    public static void setBrightness(int value){
        value = value % 65;
        Context context = MApplication.getInstance().getApplicationContext();
        try {
            Class<?> classController = Class.forName("android.onyx.hardware.DeviceController");
            Method setLight = classController.getMethod("setFrontLightValue", Context.class, int.class);
            setLight.invoke(null, context, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

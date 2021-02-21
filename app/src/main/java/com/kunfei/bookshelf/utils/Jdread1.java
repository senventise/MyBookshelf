package com.kunfei.bookshelf.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.kunfei.bookshelf.MApplication;

import java.lang.reflect.Method;

@SuppressLint({"PrivateApi", "LogNotTimber"})
public class Jdread1 {

    // 获取 jdread1 背光亮度
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

    // 设置 jdread1 背光亮度
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

    // 快速模式
    public static void applyApplicationFastMode(boolean b){
        try {
            Class<?> classController = Class.forName("android.onyx.ViewUpdateHelper");
            Method applyApplicationFastMode = classController.getMethod(
                    "applyApplicationFastMode",
                    String.class, boolean.class, boolean.class
            );
            applyApplicationFastMode.invoke(null, "EpdDeviceManager", b, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void clearApplicationFastMode(){
        try {
            Class<?> classController = Class.forName("android.onyx.ViewUpdateHelper");
            Method clearApplicationFastMode = classController.getMethod("clearApplicationFastMode");
            clearApplicationFastMode.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 处于快速模式
    public static boolean isInAppA2Mode(){
        try {
            Class<?> classController = Class.forName("android.onyx.ViewUpdateHelper");
            Method isInAppA2Mode = classController.getMethod("isInAppA2Mode");
            return (boolean)isInAppA2Mode.invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 进入普通模式
    public static void enterNormalMode(){
        try {
            clearApplicationFastMode();
            applyApplicationFastMode(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

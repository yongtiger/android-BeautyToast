package cc.brainbook.android.beautytoast.sample.application;

import android.app.Application;

import cc.brainbook.android.beautytoast.BeautyToast;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BeautyToast.Config.getInstance()
//                .setAnimate()
//                .setAnimationDuration()
//                .setAnimationGravity()
//                .setDuration()
//                .setTextColor()
//                .setErrorColor()
//                .setInfoColor()
//                .setSuccessColor()
//                .setWarningColor()
//                .setLayoutGravity()
//                .setLongDurationMillis()
//                .setRadius()
//                .setRelativeGravity()
//                .setSameLength()
//                .setShortDurationMillis()
//                .setShowIcon()
//                .setTextSize()
                .apply(this);
    }
}

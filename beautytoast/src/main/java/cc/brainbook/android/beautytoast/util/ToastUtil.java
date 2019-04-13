package cc.brainbook.android.beautytoast.util;

import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cc.brainbook.android.beautytoast.ToastBase;

public class ToastUtil {

    ///target
    public static final int GRAVITY_TO_LEFT_OF_TARGET = 0;
    public static final int GRAVITY_ABOVE_TARGET = 1;
    public static final int GRAVITY_TO_RIGHT_OF_TARGET = 2;
    public static final int GRAVITY_BELOW_TARGET = 3;

    /**
     * 在任意指定View旁边显示Toast
     *
     * 注意：必须在View执行完布局之后！否则targetLocation为0，导致显示Toast只能在左上角！
     *
     * @param context
     * @param toast
     * @param target
     * @param targetGravity
     * @param isLayoutFullScreen
     */
//    public static void setGravityByTarget(Context context, ToastBase toast, boolean isLayoutFullScreen,
//                                          View target, int targetGravity, int offsetX, int offsetY) {
//        if (toast == null || target == null) {
//            return;
//        }
//
//        final View toastView = toast.getView();
//        toastView.measure(0,0);  ///[FIX#使用带icon的BeautyToast时显示错位！]
//
//        final int toastWidth = toastView.getMeasuredWidth();
//        final int toastHeight = toastView.getMeasuredHeight();
//        final int targetWidth = target.getMeasuredWidth();
//        final int targetHeight = target.getMeasuredHeight();
//
//        ///获得target的坐标
//        final int[] targetLocation = new int[2];
//        target.getLocationInWindow(targetLocation);
////        target.getLocationOnScreen(targetLocation);
//
//        int xOffset = targetLocation[0];
//        int yOffset = targetLocation[1];
//
//        switch (targetGravity) {
//            case GRAVITY_ABOVE_TARGET:
//                xOffset += (targetWidth - toastWidth) / 2;
//                yOffset -= toastHeight;
//
//                break;
//            case GRAVITY_TO_RIGHT_OF_TARGET:
//                xOffset += targetWidth;
//                yOffset += (targetHeight - toastHeight) / 2;
//
//                break;
//            case GRAVITY_BELOW_TARGET:
//                xOffset += (targetWidth - toastWidth) / 2;
//                yOffset += targetHeight;
//
//                break;
//            default:
//                xOffset -= toastWidth;
//                yOffset += (targetHeight - toastHeight) / 2;
//        }
//
//        ///Toast非全屏显示时，需要调整setGravity()的yOffset
//        ///Toast默认Gravity的坐标系不包含状态栏（即非全屏)，与BeautyToast中Target产生高度上的错位！
//        if (!isLayoutFullScreen) {
//            final int statusBarHeight = getStatusBarHeight(context);
//            if (statusBarHeight != -1) {
//                yOffset -= statusBarHeight;
//            }
//        }
//
//        ///////?????????[BUG#Toast.setGravity()在Toast显示中失效！]
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, xOffset + offsetX, yOffset + offsetY);
//    }
    public static void setGravityByTarget(Context context, ToastBase toast, boolean isLayoutFullScreen,
                                          View target, int targetGravity, int offsetX, int offsetY) {
        if (toast == null || target == null) {
            return;
        }

        ///获得屏幕宽高
        final int screenWidth= context.getResources().getDisplayMetrics().widthPixels;
        final int screenHeight= context.getResources().getDisplayMetrics().heightPixels;

        final View toastView = toast.getView();
        toastView.measure(0,0);  ///[FIX#使用带icon的BeautyToast时显示错位！]

        final int toastHalfWidth = toastView.getMeasuredWidth() / 2;
        final int toastHalfHeight = toastView.getMeasuredHeight() / 2;
        final int targetHalfWidth = target.getMeasuredWidth() / 2;
        final int targetHalfHeight = target.getMeasuredHeight() / 2;

        ///获得target的坐标
        final int[] targetLocation = new int[2];
        target.getLocationInWindow(targetLocation);
//        target.getLocationOnScreen(targetLocation);

        final int targetCenterX = targetLocation[0] + targetHalfWidth;
        final int targetCenterY = targetLocation[1] + targetHalfHeight;


        int xOffset = targetCenterX - screenWidth / 2;
        int yOffset = targetCenterY - screenHeight / 2;

        switch (targetGravity) {
            case GRAVITY_ABOVE_TARGET:
                yOffset -= toastHalfHeight + targetHalfHeight;

                break;
            case GRAVITY_TO_RIGHT_OF_TARGET:
                xOffset += toastHalfWidth + targetHalfWidth;

                break;
            case GRAVITY_BELOW_TARGET:
                yOffset += toastHalfHeight + targetHalfHeight;

                break;
            default:
                xOffset -= toastHalfWidth + targetHalfWidth;
        }

        ///Toast非全屏显示时，需要调整setGravity()的yOffset
        ///Toast默认Gravity的坐标系不包含状态栏（即非全屏)，与BeautyToast中Target产生高度上的错位！
        if (!isLayoutFullScreen) {
            final int statusBarHeight = getStatusBarHeight(context);
            if (statusBarHeight != -1) {
                yOffset -= statusBarHeight / 2;
            }
        }

        ///////?????????[BUG#Toast.setGravity()在Toast显示中失效！]
        toast.setGravity(Gravity.CENTER, xOffset + offsetX, yOffset + offsetY);
    }

    /**
     * 获取系统状态栏高度
     *
     * 注意：即使全屏模式，系统状态栏高度也存在！系统资源属性是固定的，无论是否隐藏或者全屏显示
     *
     * https://blog.csdn.net/a_running_wolf/article/details/50477965
     * @param context
     * @return  -1：代表未获取成功
     */
    public static int getStatusBarHeight(Context context) {

        int statusBarHeight = -1;

        //获取status_bar_height资源的ID
        final int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }

        return statusBarHeight;
    }

    ///[FIX BUG#关闭通知权限后Toast无法显示]
    /**
     * 消息通知是否开启
     * @return
     */
    public static boolean isNotificationEnabled(Context context) {
        final NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
        return areNotificationsEnabled;
    }
    /**
     * 显示系统Toast
     */
    private static Object iNotificationManagerObj;
    public static void showSystemToast(Toast toast){
        try{
            final Method getServiceMethod = Toast.class.getDeclaredMethod("getService");
            getServiceMethod.setAccessible(true);
            //hook INotificationManager
            if(iNotificationManagerObj == null){
                iNotificationManagerObj = getServiceMethod.invoke(null);

                final Class iNotificationManagerCls = Class.forName("android.app.INotificationManager");
                final Object iNotificationManagerProxy = Proxy.newProxyInstance(toast.getClass().getClassLoader(),
                        new Class[]{iNotificationManagerCls}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //强制使用系统Toast
                        if("enqueueToast".equals(method.getName())
                                || "enqueueToastEx".equals(method.getName())){  //华为p20 pro上为enqueueToastEx
                            args[0] = "android";
                        }
                        return method.invoke(iNotificationManagerObj, args);
                    }
                });
                final Field sServiceFiled = Toast.class.getDeclaredField("sService");
                sServiceFiled.setAccessible(true);
                sServiceFiled.set(null, iNotificationManagerProxy);
            }

            toast.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

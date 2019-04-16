package cc.brainbook.android.beautytoast.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import cc.brainbook.android.beautytoast.ToastBase;
import cc.brainbook.android.beautytoast.ToastyBase;

public class ToastUtil {

    ///target
    public static final int GRAVITY_TO_LEFT_OF_TARGET = 0;
    public static final int GRAVITY_ABOVE_TARGET = 1;
    public static final int GRAVITY_TO_RIGHT_OF_TARGET = 2;
    public static final int GRAVITY_BELOW_TARGET = 3;

    /**
     * 在任意指定View旁边显示Toast
     *
     * 注意：必须在View执行完布局之后！
     *
     * @param context
     * @param toast
     * @param isLayoutFullScreen
     * @param target
     * @param targetGravity
     * @param targetOffsetX
     * @param targetOffsetY
     */
    public static void setGravityByTarget(Context context, ToastBase toast, boolean isLayoutFullScreen,
                                          View target, int targetGravity, int targetOffsetX, int targetOffsetY) {
        if (toast == null || target == null) {
            return;
        }

        final int[] toastLocation = calToastLocation(context, toast.getView(), target, targetGravity, isLayoutFullScreen, targetOffsetX, targetOffsetY);

        ///////?????????[BUG#Toast.setGravity()在Toast显示过程中，无法移动其位置！]
        toast.setGravity(Gravity.CENTER, toastLocation[0], toastLocation[1]);
    }
    public static void setGravityByTarget(Context context, ToastyBase toast, boolean isLayoutFullScreen,
                                          View target, int targetGravity, int targetOffsetX, int targetOffsetY) {
        if (toast == null || target == null) {
            return;
        }

        final int[] toastLocation = calToastLocation(context, toast.getView(), target, targetGravity, isLayoutFullScreen, targetOffsetX, targetOffsetY);

        toast.setGravity(Gravity.CENTER, toastLocation[0], toastLocation[1]);
    }
    private static int[] calToastLocation(Context context, View toastView, View target, int targetGravity, boolean isLayoutFullScreen,
                            int targetOffsetX, int targetOffsetY) {
        toastView.measure(0,0);  ///[FIX#使用带icon的BeautyToast时显示错位！]

        final int toastHalfWidth = toastView.getMeasuredWidth() / 2;
        final int toastHalfHeight = toastView.getMeasuredHeight() / 2;

        final int targetHalfWidth = target.getMeasuredWidth() / 2;
        final int targetHalfHeight = target.getMeasuredHeight() / 2;

        ///获得target的坐标
        final int[] targetLocation = new int[2];
        target.getLocationInWindow(targetLocation);
        ///getLocationOnScreen() and getLocationInWindow() normally return the same values.
        // This is because the window is normally the same size as the screen.
        // However, sometimes the window is smaller than the screen. For example, in a Dialog or a custom system keyboard
        ///https://stackoverflow.com/questions/17672891/getlocationonscreen-vs-getlocationinwindow
//        target.getLocationOnScreen(targetLocation);

        final int targetCenterX = targetLocation[0] + targetHalfWidth;
        final int targetCenterY = targetLocation[1] + targetHalfHeight;

        ///获得屏幕宽高
        final int screenWidth= context.getResources().getDisplayMetrics().widthPixels;
        final int screenHeight= context.getResources().getDisplayMetrics().heightPixels;

        final int[] toastLocation = new int[2];
        toastLocation[0] = targetCenterX - screenWidth / 2;
        toastLocation[1] = targetCenterY - screenHeight / 2;

        switch (targetGravity) {
            case GRAVITY_ABOVE_TARGET:
                toastLocation[1] -= toastHalfHeight + targetHalfHeight;

                break;
            case GRAVITY_TO_RIGHT_OF_TARGET:
                toastLocation[0] += toastHalfWidth + targetHalfWidth;

                break;
            case GRAVITY_BELOW_TARGET:
                toastLocation[1] += toastHalfHeight + targetHalfHeight;

                break;
            default:
                toastLocation[0] -= toastHalfWidth + targetHalfWidth;
        }

        ///Toast非全屏显示时，需要调整setGravity()的toastLocation[1]
        ///Toast默认Gravity的坐标系不包含状态栏（即非全屏)，与BeautyToast中Target产生高度上的错位！
        if (!isLayoutFullScreen) {
            final int statusBarHeight = getStatusBarHeight(context);
            if (statusBarHeight != -1) {
                toastLocation[1] -= statusBarHeight / 2;
            }
        }

        toastLocation[0] += targetOffsetX;
        toastLocation[1] += targetOffsetY;

        return toastLocation;
    }

    /**
     * 获取系统Toast的Y坐标偏移量
     *
     * @param context
     * @return
     */
    public static int getToastOffsetY(Context context) {
        int i = Resources.getSystem().getIdentifier("toast_y_offset", "dimen", "android");
        return context.getResources().getDimensionPixelSize(i);
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

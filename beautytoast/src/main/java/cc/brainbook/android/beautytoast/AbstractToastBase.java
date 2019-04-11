package cc.brainbook.android.beautytoast;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Predicate;

import static cc.brainbook.android.beautytoast.BuildConfig.DEBUG;

public abstract class AbstractToastBase {
    private static final String TAG = "TAG";

    /**
     * Toast的缺省时长（毫秒）
     */
    public static final int LENGTH_SHORT = 3000;    ///毫秒
    public static final int LENGTH_LONG = 4500;    ///毫秒

    /**
     * MainThreadHandler
     */
    private static final int SHOW = 0;
    private static final int CANCEL = 1;
    private static Handler sMainThreadHandler = new MainThreadHandler();

    /**
     * Toast队列
     */
    ///Note: LinkedList is implemented as a double linked list. Its performance on add and remove is better than Arraylist, but worse on get and set methods.
    ///https://dzone.com/articles/arraylist-vs-linkedlist-vs
    ///https://blog.csdn.net/u012926924/article/details/47955035
    private static final LinkedList<AbstractToastBase> TOAST_LIST = new LinkedList<>();

    /**
     * 正在显示的Toast
     *
     * 注意：当Context（如Activity/Fragment等）销毁时必须调用AbstractToastBase.clear(Context)方法清除，避免内存泄漏！
     */
    private static AbstractToastBase sCurrentShowingToast;

    /**
     * 是否notify的标志（用volatile修饰符！）
     */
    private volatile static boolean sIsNotifiable = false;

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) synchronized (TOAST_LIST) {
                    if (DEBUG) Log.d(TAG, "static Thread#Runnable#run()# TOAST_LIST.size(): " + TOAST_LIST.size());

                    if (!TOAST_LIST.isEmpty()) {
                        if (DEBUG) Log.d(TAG, "static Thread#Runnable#run()# sMainThreadHandler.sendMessage(SHOW), sIsNotifiable = false");

                        ///如果不存在正在显示的Toast，则进行显示Toast处理
                        if (sCurrentShowingToast == null) {
                            sCurrentShowingToast = TOAST_LIST.remove();

                            ///发送消息：显示Toast
                            sMainThreadHandler.obtainMessage(SHOW, sCurrentShowingToast).sendToTarget();

                            sIsNotifiable = false;
                        }
                    } else {
                        if (DEBUG) Log.d(TAG, "static Thread#Runnable#run()# TOAST_LIST.size(): 0, sIsNotifiable = true");
                        sIsNotifiable = true;
                    }

                    try {
                        if (DEBUG) Log.d(TAG, "static Thread#Runnable#run()# TOAST_LIST.wait(): ............. ");
                        TOAST_LIST.wait();
                        if (DEBUG) Log.d(TAG, "static Thread#Runnable#run()# ................................ notified!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static class MainThreadHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            final AbstractToastBase abstractToastBase = (AbstractToastBase) msg.obj;
            switch (msg.what) {
                case SHOW: {
                    abstractToastBase.handleShow();

                    break;
                }
                case CANCEL: {
                    abstractToastBase.handleCancel();

                    synchronized (TOAST_LIST) {
                        ///清空正在显示的Toast
                        sCurrentShowingToast = null;

                        TOAST_LIST.notifyAll();
                    }
                    break;
                }
            }
        }
    }

    /**
     * 移除对应Context类名中的Toast
     *
     * @param className      Context 类名
     * @param category       Category Toast的类别
     * @param isRemoveAll    是否移除对应Context类名中的所有Toast
     *                       如果是，则忽略类别
     *                       否则将移除对应Context类名中指定Toast的类别的所有Toast
     */
    private static void remove(final String className, final String category, final boolean isRemoveAll) {
        synchronized (TOAST_LIST) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                TOAST_LIST.removeIf(new Predicate<AbstractToastBase>() {
                    @Override
                    public boolean test(AbstractToastBase abstractToastBase) {
                        return className.equals(abstractToastBase.mClassName) &&
                                (isRemoveAll || category.equals(abstractToastBase.mCategory));
                    }
                });
            } else {
                final HashSet<AbstractToastBase> removeSet = new HashSet<>();
                for (AbstractToastBase abstractToastBase : TOAST_LIST) {
                    if (className.equals(abstractToastBase.mClassName) &&
                            (isRemoveAll || category.equals(abstractToastBase.mCategory))) {
                        removeSet.add(abstractToastBase);
                    }
                }
                if (removeSet.size() > 0) {
                    TOAST_LIST.removeAll(removeSet);
                }
            }
        }
    }
    private static void remove(Context context) {
        remove(context.getClass().toString(), "", true);
    }
    private static void remove(Context context, String category) {
        remove(context.getClass().toString(), category, false);
    }
    private static void remove(String className, String category) {
        remove(className, category, false);
    }

    /**
     * 清除指定Context的Toast
     *
     * 先从Toast队列中删除指定Context的Toast，然后
     * 如果与正在显示的Toast有相同的类名，则调用handleCancel()处理，最后清空正在显示的Toast
     *
     * 注意：当Context（如Activity/Fragment等）销毁时必须调用此方法，避免内存泄漏！
     *
     * @param context
     */
    public static void clear(Context context) {
        ///从Toast队列中删除指定Context的Toast
        remove(context);

        if (sCurrentShowingToast != null && context.getClass().toString().equals(sCurrentShowingToast.mClassName)) {
            ///发送消息：取消Toast
            sMainThreadHandler.obtainMessage(CANCEL, sCurrentShowingToast).sendToTarget();
        }

    }

    /**
     * 上下文Context
     */
    protected Context mContext;

    /**
     * Toast的类名（包含包名在内的完整类名）
     */
    private String mClassName;

    protected AbstractToastBase(Context context) {
        mContext = context;
        mClassName = context.getClass().toString();
    }

    /**
     * Toast的类别
     */
    private String mCategory = "";
    public AbstractToastBase setCategory(String category) {
        if (category == null) {
            category = "";
        }
        mCategory = category;

        return this;
    }

    /**
     * 显示Toast
     */
    public void show() {
        if (DEBUG) Log.d(TAG, "show()# TOAST_LIST.size : " + TOAST_LIST.size() + ", sIsNotifiable: " + sIsNotifiable);

        ///如果与正在显示的Toast有相同的类名和类别，则return
        if (sCurrentShowingToast != null && mClassName.equals(sCurrentShowingToast.mClassName)
                && mCategory.equals(sCurrentShowingToast.mCategory)) {
            return;
        }

        synchronized (TOAST_LIST) {
            ///移除对应Context类名中的Toast
            remove(mClassName, mCategory);

            ///添加到TOAST_LIST进行显示排队处理
            TOAST_LIST.add(this);
            if (sIsNotifiable) {
                TOAST_LIST.notifyAll();
            }
        }
    }

    /**
     * 取消Toast
     */
    public void cancel() {
        if (DEBUG) Log.d(TAG, "cancel()# ");

        ///发送消息：取消Toast
        sMainThreadHandler.obtainMessage(CANCEL, this).sendToTarget();
    }

    /**
     * 抽象方法：handleShow()
     */
    protected abstract void handleShow();

    /**
     * 抽象方法：handleCancel()
     */
    protected abstract void handleCancel();

}

package cc.brainbook.android.beautytoast;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import cc.brainbook.android.beautytoast.util.ToastUtil;

import static cc.brainbook.android.beautytoast.BuildConfig.DEBUG;

/**
 * 注意：Android M 6.0 (API level 23)以上需要动态设置权限
 */
public class ToastyBase extends AbstractToastBase {
    private static final String TAG = "TAG";

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context
     * @param text
     * @param duration
     * @return
     */
    public static ToastyBase makeText(Context context, CharSequence text, long duration) {
        final ToastyBase toastyBase = new ToastyBase(context);

        ///设置View
        toastyBase.setView(toastyBase.getDefaultView());
        ///设置TextView
        toastyBase.setTextView(toastyBase.getDefaultTextView());

        toastyBase.setText(text);

        toastyBase.setDuration(duration);

        return toastyBase;
    }

    public ToastyBase(Context context) {
        super(context);

        ///获得Toasty缺省的LayoutParams
        mToastyLayoutParams = getDefaultToastyLayoutParams();
    }

    ///ToastyLayoutParams
    private WindowManager.LayoutParams mToastyLayoutParams;
    private WindowManager.LayoutParams getDefaultToastyLayoutParams() {
        final WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        ///[ToastyBase#WindowManager.LayoutParams.TYPE_SYSTEM_ALERT替换为TYPE_APPLICATION_OVERLAY]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ///Android O 8.0 (API level 26)以上版本使用TYPE_APPLICATION_OVERLAY
            ///https://blog.csdn.net/qq_23374873/article/details/80718948
            lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.format = PixelFormat.TRANSPARENT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM; ///0x00000051
        lp.y = ToastUtil.getToastOffsetY(mContext);

        return lp;
    }

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (DEBUG) Log.d(TAG, "ToastBase# handleShow()# CountDownTimer.onFinish()# ");

            ///取消Toasty
            ToastyBase.this.cancel();
        }
    };
    @Override
    protected void handleShow() {
        if (DEBUG) Log.d(TAG, "ToastBase# handleShow()# ");

        ///显示Toasty
        final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(mView, mToastyLayoutParams);

        ///延时
        mHandler.postDelayed(mRunnable, calMillisInFuture(mDuration));
    }

    /**
     * 计算CountDownTimer的总时长
     *
     * 注意：覆写此方法，扣除出场动画的时间
     *
     * @param duration
     * @return
     */
    protected long calMillisInFuture(long duration) {
        return duration;
    }

    @Override
    protected void handleCancel() {
        if (DEBUG) Log.d(TAG, "ToastBase# handleCancel()# ");

        if (mView != null) {
            ///取消显示Toasty
            final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.removeViewImmediate(mView);   ///[FIX#ToastyBase窗体泄漏]改用windowManager.removeViewImmediate(mView)
        }

        mHandler.removeCallbacks(mRunnable);
    }


    /* ---------------- 动态方法：参照Toast源码 ---------------- */
    ///text
    public ToastyBase setText(CharSequence text) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setText(text);

        return this;
    }
    public ToastyBase setText(@StringRes int resId) {
        setText(mContext.getText(resId));

        return this;
    }

    ///duration
    protected long mDuration = LENGTH_SHORT;
    public ToastyBase setDuration(long duration) {
        mDuration = duration;

        return this;
    }

    ///view
    private View mView;
    public View getView() {
        return mView;
    }
    public View getDefaultView() {
        final LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return layoutInflater.inflate(R.layout.layout_transient_notification_api_27, null);
    }
    public ToastyBase setView(View view) {
        mView = view;
        return this;
    }

    ///text view
    protected TextView mTextView;
    public TextView getTextView() {
        return mTextView;
    }
    public TextView getDefaultTextView() {
        final View view = getView();
        return (TextView) view.findViewById(R.id.message);
    }
    public ToastyBase setTextView(TextView textView) {
        mTextView = textView;

        return this;
    }

///？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
//    ///gravity, xOffset, yOffset
//    public int getGravity() {
//        return mToast.getGravity();
//    }
//    public int getXOffset() {
//        return mToast.getYOffset();
//    }
//    public int getYOffset() {
//        return mToast.getYOffset();
//    }
//    public ToastyBase setGravity(int gravity, int xOffset, int yOffset) {
//        mToast.setGravity(gravity, xOffset, yOffset);
//
//        return this;
//    }
//
//    ///margin
//    public float getHorizontalMargin() {
//        return mToast.getHorizontalMargin();
//    }
//    public float getVerticalMargin() {
//        return mToast.getVerticalMargin();
//    }
//    public ToastyBase setMargin(float horizontalMargin, float verticalMargin) {
//        mToast.setMargin(horizontalMargin, verticalMargin);
//
//        return this;
//    }
    /* ---------------- 动态方法：参照Toast源码 ---------------- */


    /* ---------------- 动态方法 ---------------- */
    ///text color
    public ToastyBase setTextColor(@ColorInt int textColor) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextColor(textColor);

        return this;
    }

    ///text size
    public ToastyBase setTextSize(float textSize) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextSize(textSize);

        return this;
    }
    public ToastyBase setTextSize(int unit, float textSize) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextSize(unit, textSize);

        return this;
    }

    ///background
    public ToastyBase setBackground(Drawable drawableRes) {
        final View view = getView();

        ///[FIX BUG#API 17改为兼容API 15]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawableRes);
        } else {
            view.setBackgroundDrawable(drawableRes);
        }

        return this;
    }
    public ToastyBase setBackgroundColor(@ColorInt int backgroundColor) {
        final View view = getView();
        view.setBackgroundColor(backgroundColor);

        return this;
    }
    /* ---------------- 动态方法 ---------------- */

}

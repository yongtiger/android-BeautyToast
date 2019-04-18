package cc.brainbook.android.beautytoast;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.RequiresApi;
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
public class ToastyBase extends AbstractBase {
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
            ///取消Toasty
            ToastyBase.this.cancel();
        }
    };

    @Override
    protected void handleShow() {
        if (DEBUG) Log.d(TAG, "handleShow()# ");

        ///调整View
        adjustView();

        ///显示Toasty
        final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(mView, mToastyLayoutParams);

        ///延时
        mHandler.postDelayed(mRunnable, mDuration);
    }

    @Override
    protected void handleCancel() {
        if (DEBUG) Log.d(TAG, "handleCancel()# ");

        if (mView != null) {
            ///取消显示Toasty
            final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.removeViewImmediate(mView);   ///[FIX#ToastyBase窗体泄漏]改用windowManager.removeViewImmediate(mView)
        }

        mHandler.removeCallbacks(mRunnable);
    }

    /**
     * 调整View
     */
    protected void adjustView() {}


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
    private long mDuration = LENGTH_SHORT;
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
    private TextView mTextView;
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

    ///gravity, xOffset, yOffset
    public int getGravity() {
        return mToastyLayoutParams.gravity;
    }
    public int getXOffset() {
        return mToastyLayoutParams.x;
    }
    public int getYOffset() {
        return mToastyLayoutParams.y;
    }
    public ToastyBase setGravity(int gravity, int xOffset, int yOffset) {
        mToastyLayoutParams.gravity = gravity;
        mToastyLayoutParams.x = xOffset;
        mToastyLayoutParams.y = yOffset;

        return this;
    }

    ///margin
    public float getHorizontalMargin() {
        return mToastyLayoutParams.horizontalMargin;
    }
    public float getVerticalMargin() {
        return mToastyLayoutParams.verticalMargin;
    }
    public ToastyBase setMargin(float horizontalMargin, float verticalMargin) {
        mToastyLayoutParams.horizontalMargin = horizontalMargin;
        mToastyLayoutParams.verticalMargin = verticalMargin;

        return this;
    }
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


    ///LayoutFullScreen
    protected boolean isLayoutFullScreen = false;
    public boolean isLayoutFullScreen() {
        return isLayoutFullScreen;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    /**
     * 设置Toast是否为全屏布局
     *
     * Toast默认Gravity的坐标系不包含状态栏（即非全屏，与BeautyToast中Target产生高度上的错位！）
     * 设置为全屏模式后，Gravity的坐标系为全屏，包含了状态栏（与BeautyToast中Target的坐标系保持一致）
     *
     * 注意：只针对Gravity.TOP！且必须API 16+！
     */
    public ToastyBase isGravityFullScreen(boolean isGravityFullScreen) {
        this.isLayoutFullScreen = isGravityFullScreen;

        final View view = getView();
        if (isGravityFullScreen) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);  ///全局布局
        } else {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);    ///恢复正常
        }

        return this;
    }

    /**
     * 更新Toasty
     *
     * 当mToastyLayoutParams改变后（主要指Gravity），更新Toasty
     */
    public void update() {
        if (mView == null) {
            throw new RuntimeException("mView cannot be null");
        }

        if (mView.isShown()) {
            ///更新Toasty
            final WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.updateViewLayout(getView(), mToastyLayoutParams);
        } else {
            throw new RuntimeException("mView has not been laid out");
        }
    }

}

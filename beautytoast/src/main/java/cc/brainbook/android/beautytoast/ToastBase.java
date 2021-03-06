package cc.brainbook.android.beautytoast;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cc.brainbook.android.beautytoast.util.CountDownTimer;
import cc.brainbook.android.beautytoast.util.ToastUtil;

import static cc.brainbook.android.beautytoast.BuildConfig.DEBUG;

public class ToastBase extends AbstractBase {
    private static final String TAG = "TAG";

    private Toast mToast;
    private CountDownTimer mCountDownTimer;

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context
     * @param text
     * @param duration
     * @return
     */
    @NonNull
    public static ToastBase makeText(@NonNull Context context, CharSequence text, long duration) {
        final ToastBase toastBase = new ToastBase(context);

        ///设置View
        toastBase.setView(toastBase.getDefaultView());
        ///设置TextView
        toastBase.setTextView(toastBase.getDefaultTextView());

        toastBase.setText(text);

        toastBase.setDuration(duration);

        return toastBase;
    }

    /**
     * Make a standard toast that just contains a text view with the text from a resource.
     *
     * @param context  The context to use.  Usually your {@link android.app.Application}
     *                 or {@link android.app.Activity} object.
     * @param resId    The resource id of the string resource to use.  Can be formatted text.
     * @param duration How long to display the message.
     *
     * @throws Resources.NotFoundException if the resource can't be found.
     */
    @NonNull
    public static ToastBase makeText(@NonNull Context context, @StringRes int resId, long duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    public ToastBase(Context context) {
        super(context);

        mToast = new Toast(context);
    }

    @Override
    protected void handleShow() {
        if (DEBUG) Log.d(TAG, "handleShow()# ");

        ///调整View
        adjustView();

        ///[UPGRADE#Duration可随意选择]用CountDownTimer重复显示Toast实现自定义Toast显示时长
        ///[FIX#设置Toast为LENGTH_LONG，避免CountDownTimer的onTick()到期时仍会显示Toast淡出动画]
        ///[FIX#Duration可随意选择:API25+失效]只能等上一个Toast快结束时（约974毫秒）再次show！
        long countDownInterval;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ///注意：实测大于974肯定失效！///???????????[BUG#只能等上一个Toast快结束时（约974毫秒）再次show！有短暂闪烁！]
            countDownInterval = LENGTH_LONG - 950;
        } else{
            countDownInterval = LENGTH_SHORT;
        }
        mToast.setDuration(Toast.LENGTH_LONG);
        mCountDownTimer = new CountDownTimer(mDuration, countDownInterval) {   ///CountDownTimer的onTick()的周期为LENGTH_SHORT即可
            @Override
            public void onTick(long millisUntilFinished) {
                if (DEBUG) Log.d(TAG, "handleShow()# CountDownTimer.onTick()# millisUntilFinished: " + millisUntilFinished);

                ///[FIX BUG#关闭通知权限后Toast无法显示]
                if(ToastUtil.isNotificationEnabled(mContext)){
                    mToast.show();
                }else{
                    ToastUtil.showSystemToast(mToast);
                }
            }

            @Override
            public void onFinish() {
                if (DEBUG) Log.d(TAG, "handleShow()# CountDownTimer.onFinish()# ");

                ///取消Toast
                ToastBase.this.cancel();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void handleCancel() {
        if (DEBUG) Log.d(TAG, "handleCancel()# ");

        ///取消显示Toast
        if (mToast != null) {
            mToast.cancel();
        }

        ///取消CountDownTimer
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    /**
     * 调整View
     */
    protected void adjustView() {}


    /* ---------------- 动态方法：参照Toast源码 ---------------- */
    ///text
    public ToastBase setText(CharSequence text) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setText(text);

        return this;
    }
    public ToastBase setText(@StringRes int resId) {
        setText(mContext.getText(resId));

        return this;
    }

    ///duration
    private long mDuration = LENGTH_SHORT;
    public ToastBase setDuration(long duration) {
        mDuration = duration;

        return this;
    }

    ///view
    public View getView() {
        return mToast.getView();
    }
    public View getDefaultView() {
        final LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @LayoutRes int resource;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            resource = R.layout.layout_transient_notification_api_27;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resource = R.layout.layout_transient_notification_api_21;
        } else {
            resource = R.layout.layout_transient_notification;
        }

        return layoutInflater.inflate(resource, null);
    }
    public ToastBase setView(View view) {
        mToast.setView(view);

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
    public ToastBase setTextView(TextView textView) {
        mTextView = textView;

        return this;
    }

    ///gravity, xOffset, yOffset
    public int getGravity() {
        return mToast.getGravity();
    }
    public int getXOffset() {
        return mToast.getYOffset();
    }
    public int getYOffset() {
        return mToast.getYOffset();
    }
    public ToastBase setGravity(int gravity, int xOffset, int yOffset) {
        mToast.setGravity(gravity, xOffset, yOffset);

        return this;
    }

    ///margin
    public float getHorizontalMargin() {
        return mToast.getHorizontalMargin();
    }
    public float getVerticalMargin() {
        return mToast.getVerticalMargin();
    }
    public ToastBase setMargin(float horizontalMargin, float verticalMargin) {
        mToast.setMargin(horizontalMargin, verticalMargin);

        return this;
    }
    /* ---------------- 动态方法：参照Toast源码 ---------------- */


    /* ---------------- 动态方法 ---------------- */
    ///text color
    public ToastBase setTextColor(@ColorInt int textColor) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextColor(textColor);

        return this;
    }

    ///text size
    public ToastBase setTextSize(float textSize) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextSize(textSize);

        return this;
    }
    public ToastBase setTextSize(int unit, float textSize) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setTextSize(unit, textSize);

        return this;
    }

    ///background
    public ToastBase setBackground(Drawable drawableRes) {
        final View view = getView();

        ///[FIX BUG#API 17改为兼容API 15]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawableRes);
        } else {
            view.setBackgroundDrawable(drawableRes);
        }

        return this;
    }
    public ToastBase setBackgroundColor(@ColorInt int backgroundColor) {
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
    public ToastBase isGravityFullScreen(boolean isGravityFullScreen) {
        this.isLayoutFullScreen = isGravityFullScreen;

        final View view = getView();
        if (isGravityFullScreen) {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);  ///全局布局
        } else {
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);    ///恢复正常
        }

        return this;
    }

}

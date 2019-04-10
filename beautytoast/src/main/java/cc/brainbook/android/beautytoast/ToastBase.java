package cc.brainbook.android.beautytoast;

import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cc.brainbook.android.beautytoast.util.CountDownTimer;
import cc.brainbook.android.beautytoast.util.Util;

import static cc.brainbook.android.beautytoast.BuildConfig.DEBUG;

public class ToastBase extends AbstractToastBase {
    private static final String TAG = "TAG";

    private Toast mToast;
    private CountDownTimer mCountDownTimer;

    public ToastBase(Context context) {
        super(context);

        mToast = new Toast(context);

        final LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @LayoutRes int resource;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            resource = R.layout.layout_transient_notification_api_27;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resource = R.layout.layout_transient_notification_api_21;
        } else {
            resource = R.layout.layout_transient_notification;
        }
        final View view = inflate.inflate(resource, null);

        mTextView = (TextView) view.findViewById(R.id.message);

        mToast.setView(view);
    }

    @Override
    protected void handleShow() {
        if (DEBUG) Log.d(TAG, "ToastBase# handleShow()# ");

        ///[UPGRADE#Duration可随意选择]用CountDownTimer重复显示Toast实现自定义Toast显示时长
        ///[FIX#设置Toast为LENGTH_LONG，避免CountDownTimer的onTick()到期时仍会显示Toast淡出动画]
        ///[FIX#Duration可随意选择:API25+失效]只能等上一个Toast快结束时（约974毫秒）再次show！
        long countDownInterval;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            countDownInterval = LENGTH_LONG - 950;    ///实测大于974肯定失效！
        } else{
            countDownInterval = LENGTH_SHORT;
        }
        mToast.setDuration(Toast.LENGTH_LONG);
        mCountDownTimer = new CountDownTimer(mDuration, countDownInterval) {   ///CountDownTimer的onTick()的周期为LENGTH_SHORT即可
            @Override
            public void onTick(long millisUntilFinished) {
                if (DEBUG) Log.d(TAG, "ToastBase# handleShow()# CountDownTimer.onTick()# millisUntilFinished: " + millisUntilFinished);

                ///[FIX BUG#关闭通知权限后Toast无法显示]
                if(Util.isNotificationEnabled(mContext)){
                    mToast.show();
                }else{
                    Util.showSystemToast(mToast);
                }
            }

            @Override
            public void onFinish() {
                if (DEBUG) Log.d(TAG, "ToastBase# handleShow()# CountDownTimer.onFinish()# ");

                ///取消Toast
                ToastBase.this.cancel();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    protected void handleCancel() {
        ///取消显示Toast
        if (mToast != null) {
            mToast.cancel();
        }

        ///取消CountDownTimer
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }


    /* ---------------- 静态方法：makeText() ---------------- */
    public static ToastBase makeText(Context context, CharSequence text, long duration) {
        final ToastBase toastBase = new ToastBase(context);

        toastBase.setText(text);

        toastBase.setDuration(duration);

        return toastBase;
    }
    /* ---------------- 静态方法：makeText() ---------------- */


    /* ---------------- 动态方法：参照Toast源码 ---------------- */
    ///text
    public ToastBase setText(CharSequence text) {
        if (mTextView == null) {
            throw new RuntimeException("mTextView cannot be null");
        }
        mTextView.setText(text);

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
    public ToastBase setView(View view) {
        mToast.setView(view);

        return this;
    }

    ///text view
    private TextView mTextView;
    public TextView getTextView() {
        return mTextView;
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
    public float getmHorizontalMargin() {
        return mToast.getHorizontalMargin();
    }
    public float getmVerticalMargin() {
        return mToast.getVerticalMargin();
    }
    public ToastBase setMargin(float horizontalMargin, float verticalMargin) {
        mToast.setMargin(horizontalMargin, verticalMargin);

        return this;
    }
    /* ---------------- 动态方法：参照Toast源码 ---------------- */

}

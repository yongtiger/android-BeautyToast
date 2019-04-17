package cc.brainbook.android.beautytoast;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import cc.brainbook.android.beautytoast.util.AnimationUtil;
import cc.brainbook.android.beautytoast.util.ToastUtil;

import static cc.brainbook.android.beautytoast.BuildConfig.DEBUG;

public class BeautyToast extends ToastBase {
    private static final String TAG = "TAG";

    public static float HALF_HEIGHT_CORNER_RADIUS = -0.5f;  ///0：没有弧度；-0.5：高度的1/2；0+：像素

    public static final long DEFAULT_ANIMATION_IN_DURATION = 800L;  ///毫秒
    public static final long DEFAULT_ANIMATION_OUT_DURATION = 800L;  ///毫秒

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context
     * @param text
     * @param duration
     * @return
     */
    public static BeautyToast makeText(Context context, CharSequence text, long duration) {
        final BeautyToast beautyToast = new BeautyToast(context);

        ///设置View
        beautyToast.setView(beautyToast.getDefaultView());
        ///设置TextView
        beautyToast.setTextView(beautyToast.getDefaultTextView()) ;
        ///设置ImageView
        beautyToast.setImageView(beautyToast.getDefaultImageView()) ;

        beautyToast.setText(text);

        beautyToast.setDuration(duration);

        ///设置颜色
        beautyToast.setGradientDrawableColor(context.getResources().getColor(R.color.colorBeautyToastBackground));

        ///设置圆角弧度
        beautyToast.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS);

        ///设置显示图标
        beautyToast.isShowIcon(false);

        return beautyToast;
    }

    public static BeautyToast makeWarningText(Context context, CharSequence text, long duration) {
        final BeautyToast beautyToast = makeText(context, text, duration);

        ///设置颜色
        beautyToast.setGradientDrawableColor(context.getResources().getColor(R.color.colorBeautyToastBackgroundWarning));

        ///设置ImageView的背景图片
        beautyToast.setImageResource(R.mipmap.ic_warnning_outline_white_48dp);

        ///设置显示图标
        beautyToast.isShowIcon(true);

        return beautyToast;
    }

    public static BeautyToast makeInfoText(Context context, CharSequence text, long duration) {
        final BeautyToast beautyToast = makeText(context, text, duration);

        ///设置颜色
        beautyToast.setGradientDrawableColor(context.getResources().getColor(R.color.colorBeautyToastBackgroundInfo));

        ///设置ImageView的背景图片
        beautyToast.setImageResource(R.mipmap.ic_info_outline_white_48dp);

        ///设置显示图标
        beautyToast.isShowIcon(true);

        return beautyToast;
    }

    public static BeautyToast makeSuccessText(Context context, CharSequence text, long duration) {
        final BeautyToast beautyToast = makeText(context, text, duration);

        ///设置颜色
        beautyToast.setGradientDrawableColor(context.getResources().getColor(R.color.colorBeautyToastBackgroundSuccess));

        ///设置ImageView的背景图片
        beautyToast.setImageResource(R.mipmap.ic_success_white_48dp);

        ///设置显示图标
        beautyToast.isShowIcon(true);

        return beautyToast;
    }

    public static BeautyToast makeErrorText(Context context, CharSequence text, long duration) {
        final BeautyToast beautyToast = makeText(context, text, duration);

        ///设置颜色
        beautyToast.setGradientDrawableColor(context.getResources().getColor(R.color.colorBeautyToastBackgroundError));

        ///设置ImageView的背景图片
        beautyToast.setImageResource(R.mipmap.ic_error_white_48dp);

        ///设置显示图标
        beautyToast.isShowIcon(true);

        return beautyToast;
    }


    public BeautyToast(Context context) {
        super(context);
    }

    public View getDefaultView() {
        final LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        return layoutInflater.inflate(R.layout.layout_beauty_toast, null);
    }

    /* ---------------- 动态方法 ---------------- */
    ///image view: 是否显示图标
    public ToastBase isShowIcon(boolean isShowIcon) {
        final ImageView imageView = getImageView();
        if (imageView != null) {
            imageView.setVisibility(isShowIcon ? View.VISIBLE : View.GONE);
        }

        return this;
    }

    ///image view
    private ImageView mImageView;
    public ImageView getImageView() {
        return mImageView;
    }
    public ImageView getDefaultImageView() {
        final View view = getView();
        return (ImageView) view.findViewById(R.id.icon);
    }
    public ToastBase setImageView(ImageView imageView) {
        mImageView = imageView;

        return this;
    }

    ///image view: ImageDrawable图片
    public ToastBase setImageDrawable(@Nullable Drawable drawable) {
        if (mImageView == null) {
            throw new RuntimeException("mImageView cannot be null");
        }
        mImageView.setImageDrawable(drawable);

        return this;
    }

    ///image view: ImageResource图片
    public ToastBase setImageResource(@DrawableRes int resId) {
        if (mImageView == null) {
            throw new RuntimeException("mImageView cannot be null");
        }
        mImageView.setImageResource(resId);

        return this;
    }

    ///view: GradientDrawable background: Color颜色
    public ToastBase setGradientDrawableColor(@ColorInt int color) {
        final View view = getView();
        final Drawable background = view.getBackground();
        if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(color);
        } else {
            throw new RuntimeException("GradientDrawable is expected");
        }

        return this;
    }

    ///view: GradientDrawable background: Stroke边框
    public ToastBase setGradientDrawableStroke(int width, @ColorInt int color) {
        final View view = getView();
        final Drawable background = view.getBackground();
        if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setStroke(width, color);
        } else {
            throw new RuntimeException("GradientDrawable is expected");
        }

        return this;
    }

    ///view: GradientDrawable background: cornerRadius圆角弧度
    /**
     *
     * @param cornerRadius  0：没有弧度；-0.5：高度的1/2；0+：像素
     * @return
     */
    public ToastBase setGradientDrawableCornerRadius(float cornerRadius) {
        final View view = getView();
        final Drawable background = view.getBackground();
        if (background instanceof GradientDrawable) {
            if (cornerRadius < -0.5f) {
                throw new IllegalArgumentException("CornerRadius can not be less than -0.5f");
            } else if (cornerRadius < 0f && cornerRadius >= -0.5f) {
                ///[FIX#on Android 4.2. It looks like java.lang.NullPointerException at android.view.View.measure]RelativeLayout替换为LinearLayout
                view.measure(0,0);  ///避免获得宽高为0
                float realCornerRadius = - view.getMeasuredHeight() * cornerRadius;
                ((GradientDrawable) background).setCornerRadius(realCornerRadius);
            } else {
                ((GradientDrawable) background).setCornerRadius(cornerRadius);
            }
        } else {
            throw new RuntimeException("GradientDrawable is expected");
        }

        return this;
    }


    /* ---------------- 动画 ---------------- */
    ///view: AnimationIn 入场动画
    private long mAnimationInDuration = DEFAULT_ANIMATION_IN_DURATION;
    public ToastBase setAnimationInDuration(long animationInDuration) {
        mAnimationInDuration = animationInDuration;

        return this;
    }
    private View.OnLayoutChangeListener mOnLayoutChangeListener;
    public ToastBase setAnimationIn(final int animationInMode) {
        final View view = getView();

        if (animationInMode != AnimationUtil.NO_ANIMATION) {
            mOnLayoutChangeListener = new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int left, int top, int right, int bottom,
                                           int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    view.removeOnLayoutChangeListener(this);

                    final int width = view.getMeasuredWidth();
                    final int height = view.getMeasuredHeight();

                    ///根据动画方式获取ObjectAnimator对象
                    final ObjectAnimator animator = AnimationUtil.getAnimatorByMode(animationInMode, view, width, height);
                    if (animator != null) {
                        animator.setDuration(mAnimationInDuration);
                        animator.start();
                    }
                }
            };

            view.addOnLayoutChangeListener(mOnLayoutChangeListener);

        } else if (mOnLayoutChangeListener != null) {
            view.removeOnLayoutChangeListener(mOnLayoutChangeListener);
        }

        return this;
    }

    ///view: AnimationOut 出场动画
    private long mAnimationOutDuration = DEFAULT_ANIMATION_OUT_DURATION;
    public ToastBase setAnimationOutDuration(long animationOutDuration) {
        mAnimationOutDuration = animationOutDuration;

        return this;
    }
    private int mAnimationOutMode;
    public ToastBase setAnimationOut(final int animationOutMode) {
        mAnimationOutMode = animationOutMode;

        return this;
    }
    /* ---------------- 动画 ---------------- */


    @Override
    protected void handleCancel() {
        if (DEBUG) Log.d(TAG, "BeautyToast# handleCancel()# ");

        ///view: AnimationOut 出场动画
        if (mAnimationOutMode != AnimationUtil.NO_ANIMATION) {
            final View view = getView();
            final int width = view.getMeasuredWidth();
            final int height = view.getMeasuredHeight();

            ///根据动画方式获取ObjectAnimator对象
            final ObjectAnimator animator = AnimationUtil.getAnimatorByMode(mAnimationOutMode, view, width, height);
            if (animator != null) {
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);

                        ///出场动画后再执行Toast的cancel
                        BeautyToast.super.handleCancel();
                    }
                });
                animator.setDuration(mAnimationOutDuration);
                animator.start();
            }
        } else {
            ///执行Toast的cancel
            BeautyToast.super.handleCancel();
        }
    }

    ///target
    private WeakReference<View> mTarget;    ///避免Activity关闭后造成的内存泄漏！
    public View getTarget() {
        return mTarget.get();
    }

    /**
     * 注意：Target必须已经显示（即Layout完成），否则抛出异常！比如不能在Activity的onCreate()中调用本方法
     *
     * @param view
     * @param targetOffsetX
     * @param targetOffsetY
     * @return
     */
    public ToastBase setTarget(View view, int targetGravity, final int targetOffsetX, final int targetOffsetY) {
        if (view == null) {
            mTarget = null;

            return this;
        }

        mTarget = new WeakReference<>(view);

        ///判断Target是否已经显示（即Layout完成）
        if (mTarget.get().isShown()) {
            ///在Target旁边显示Toast
            ToastUtil.setGravityByTarget(mContext, BeautyToast.this, isLayoutFullScreen,
                    getTarget(), targetGravity, targetOffsetX, targetOffsetY);
        } else {
            throw new RuntimeException("The target view has not been laid out");
        }

        return this;
    }

}

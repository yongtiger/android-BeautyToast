package cc.brainbook.android.beautytoast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class BeautyToast extends ToastBase {
    private static final String TAG = "TAG";

    public static float HALF_HEIGHT_CORNER_RADIUS = -0.5f;  ///0：没有弧度；-0.5：高度的1/2；0+：像素

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

        ///设置圆角弧度
        beautyToast.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS);
//        beautyToast.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS / 4); ///test
//        beautyToast.setGradientDrawableCornerRadius(10);    ///test

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
    protected ImageView mImageView;
    public ImageView getImageView() {
        if (mImageView == null) {
            mImageView = getDefaultImageView();
        }

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

    ///GradientDrawable: Color颜色
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

    ///GradientDrawable: Stroke边框
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

    ///GradientDrawable: cornerRadius圆角弧度
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
    /* ---------------- 动态方法 ---------------- */

}

package cc.brainbook.android.beautytoast.util;

import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtil {
    /**
     * 动画方式
     */
    public static final int NO_ANIMATION = 0;
    ///入场
    public static final int ANIMATION_LEFT_IN = 1;
    public static final int ANIMATION_TOP_IN = 2;
    public static final int ANIMATION_RIGHT_IN = 3;
    public static final int ANIMATION_BOTTOM_IN = 4;
    public static final int ANIMATION_FADE_IN = 5;
    ///出场
    public static final int ANIMATION_LEFT_OUT = -1;
    public static final int ANIMATION_TOP_OUT = -2;
    public static final int ANIMATION_RIGHT_OUT = -3;
    public static final int ANIMATION_BOTTOM_OUT = -4;
    public static final int ANIMATION_FADE_OUT = -5;

    /**
     * 根据动画方式获取ObjectAnimator对象
     *
     * @param animationMode 动画方式
     * @param view
     * @param width
     * @param height
     * @return
     */
    public static ObjectAnimator getAnimatorByMode(int animationMode, View view, int width, int height) {
        ObjectAnimator animator = null;

        switch (animationMode) {
            case ANIMATION_LEFT_IN:
                animator = ObjectAnimator.ofFloat(view, "translationX", -width, 0f);

                break;
            case ANIMATION_TOP_IN:
                animator = ObjectAnimator.ofFloat(view, "translationY", -height, 0f);

                break;
            case ANIMATION_RIGHT_IN:
                animator = ObjectAnimator.ofFloat(view, "translationX", width, 0f);

                break;
            case ANIMATION_BOTTOM_IN:
                animator = ObjectAnimator.ofFloat(view, "translationY", height, 0f);

                break;
            case ANIMATION_FADE_IN:
                animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);

                break;
            case ANIMATION_LEFT_OUT:
                animator = ObjectAnimator.ofFloat(view, "translationX", 0f, -width);

                break;
            case ANIMATION_TOP_OUT:
                animator = ObjectAnimator.ofFloat(view, "translationY", 0f, -height);

                break;
            case ANIMATION_RIGHT_OUT:
                animator = ObjectAnimator.ofFloat(view, "translationX", 0f, width);

                break;
            case ANIMATION_BOTTOM_OUT:
                animator = ObjectAnimator.ofFloat(view, "translationY", 0f, height);

                break;
            case ANIMATION_FADE_OUT:
                animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);

                break;
            default:

        }

        return animator;
    }

}

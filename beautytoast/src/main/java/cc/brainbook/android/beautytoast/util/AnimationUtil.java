package cc.brainbook.android.beautytoast.util;

import android.animation.ObjectAnimator;
import android.view.View;

public class AnimationUtil {
    /**
     * 动画方式
     */
    public static final int NO_ANIMATION = 0;
    public static final int ANIMATION_TRANSLATION_LEFT = 1;
    public static final int ANIMATION_TRANSLATION_TOP = 2;
    public static final int ANIMATION_TRANSLATION_RIGHT = 3;
    public static final int ANIMATION_TRANSLATION_BOTTOM = 4;

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
        ObjectAnimator animator;

        switch (animationMode) {
            case ANIMATION_TRANSLATION_LEFT:
                animator = ObjectAnimator.ofFloat(view, "translationX", -width, 0);

                break;
            case ANIMATION_TRANSLATION_TOP:
                animator = ObjectAnimator.ofFloat(view, "translationY", -height, 0);

                break;
            case ANIMATION_TRANSLATION_RIGHT:
                animator = ObjectAnimator.ofFloat(view, "translationX", width, 0);

                break;
            default:
                animator = ObjectAnimator.ofFloat(view, "translationY", height, 0);

                break;

        }

        return animator;
    }

}

package cc.brainbook.android.beautytoast;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class BeautyToastUtils {

    private BeautyToastUtils() {

    }

     public static void setGradientDrawable(View layout, float[] radiusArr, int color){
        GradientDrawable gradientDrawable = new GradientDrawable();

        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadii(radiusArr);

        layout.setBackground(gradientDrawable);
    }

    public static boolean isTrimEmpty(CharSequence text) {
        return text == null || text.toString().trim().equals("");
    }
}

package cc.brainbook.android.beautytoast.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cc.brainbook.android.beautytoast.BeautyToast;
import cc.brainbook.android.beautytoast.util.AnimationUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate()# ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /* ========================= 参考对比原生Toast ========================= */
//        Toast toast = Toast.makeText(getApplicationContext(), "Toast!", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//        toast.show();


        /* ========================= ToastBase ========================= */

        /* -------------------- makeText -------------------- */
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_SHORT);
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_LONG);
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", 15000);
//        ToastBase toastBase = ToastBase.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏
//
////        toastBase.setText("ToastBase!!");
////        toastBase.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//
//        toastBase.show();

        /* -------------------- new -------------------- */
//        ToastBase toastBase = new ToastBase(this);
//
//        /* --- 获得并设置View、TextView --- */
//        ///设置View
//        toastBase.setView(toastBase.getDefaultView());
//        ///设置TextView
//        toastBase.setTextView(toastBase.getDefaultTextView());
//
//        toastBase.setText("new ToastBase!!");
//
//        toastBase.setDuration(ToastBase.LENGTH_LONG);
//        /* --- 获得并设置View、TextView --- */
//
//        toastBase.setText("new ToastBase!");
//        toastBase.show();


        /* ========================= BeautyToast ========================= */

        /* -------------------- makeText -------------------- */
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", BeautyToast.LENGTH_SHORT);
        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", BeautyToast.LENGTH_LONG);
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", 15000);
//        BeautyToast beautyToast = BeautyToast.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏

//        BeautyToast beautyToast = BeautyToast.makeWarningText(getApplicationContext(),"WarningBeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeInfoText(getApplicationContext(),"Info BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeSuccessText(getApplicationContext(),"Success BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeErrorText(getApplicationContext(),"Error BeautyToast!", BeautyToast.LENGTH_SHORT);

//        beautyToast.isShowIcon(false);

//        beautyToast.setText("BeautyToast!!");
//        beautyToast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);

//        beautyToast.show();

        /* -------------------- new -------------------- */
//        BeautyToast beautyToast = new BeautyToast(this);
//
//        /* --- 获得并设置View、TextView、ImageView --- */
//        ///设置View
//        beautyToast.setView(beautyToast.getDefaultView());
//        ///设置TextView
//        beautyToast.setTextView(beautyToast.getDefaultTextView()) ;
//        ///设置ImageView
//        beautyToast.setImageView(beautyToast.getDefaultImageView()) ;
//
//        beautyToast.setText("new BeautyToast!!");
//
//        beautyToast.setDuration(BeautyToast.LENGTH_LONG);
//
//        ///设置圆角弧度
//        beautyToast.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS);
////        beautyToast.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS / 4); ///test
////        beautyToast.setGradientDrawableCornerRadius(10);    ///test
//        /* --- 获得并设置View、TextView、ImageView --- */
//
//        beautyToast.setText("new BeautyToast!");
//        beautyToast.show();


        /* -------------------- animation -------------------- */
        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_IN_TRANSLATION_LEFT);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_IN_TRANSLATION_TOP);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_IN_TRANSLATION_RIGHT);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_IN_TRANSLATION_BOTTOM);
        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_OUT_TRANSLATION_LEFT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_OUT_TRANSLATION_TOP);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_OUT_TRANSLATION_RIGHT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_OUT_TRANSLATION_BOTTOM);
//        beautyToast.setAnimationOut(AnimationUtil.NO_ANIMATION);
//        beautyToast.show();

        //////////////////////////////////////////////////////

//        View view = beautyToast.getView();
//        view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
//            @Override
//            public void onViewAttachedToWindow(View view) {
//                Log.d("TAG", "onViewAttachedToWindow: ");
////                view.animate().translationX(-300).setDuration(2000);
//            }
//
//            @Override
//            public void onViewDetachedFromWindow(View view) {
//                Log.d("TAG", "onViewDetachedFromWindow: ");
//                view.animate().translationX(-300).setDuration(2000);
//            }
//        });
        beautyToast.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ///注意：当Context（如Activity/Fragment等）销毁时必须调用此方法，避免内存泄漏！
//        ToastBase.clear(this);
    }
}

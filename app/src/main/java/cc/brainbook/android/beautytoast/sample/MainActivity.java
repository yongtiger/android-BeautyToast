package cc.brainbook.android.beautytoast.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import cc.brainbook.android.beautytoast.BeautyToast;
import cc.brainbook.android.beautytoast.ToastBase;
import cc.brainbook.android.beautytoast.util.AnimationUtil;
import cc.brainbook.android.beautytoast.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private View target;
    private View target1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate()# ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        /* ========================= 参考对比原生Toast ========================= */
//        Toast toast = Toast.makeText(getApplicationContext(), "Toast!", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 10);
//        toast.show();


        /* ========================= ToastBase ========================= */

        /* -------------------- makeText -------------------- */
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_SHORT);
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_LONG);
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", 15000);
//        ToastBase toastBase = ToastBase.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏

//        toastBase.setText("ToastBase!!");
////
//        toastBase.isGravityFullScreen(true);///注意：只针对Gravity.TOP！且必须API 16+！
//        toastBase.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 10);
////
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
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", BeautyToast.LENGTH_LONG);
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", 15000);
//        BeautyToast beautyToast = BeautyToast.makeText(this,"ToastBase!", 5000);   ///测试销毁时是否内存泄漏
//
//        BeautyToast beautyToast = BeautyToast.makeWarningText(getApplicationContext(),"WarningBeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeInfoText(getApplicationContext(),"Info BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeSuccessText(getApplicationContext(),"Success BeautyToast!", BeautyToast.LENGTH_SHORT);
        BeautyToast beautyToast = BeautyToast.makeErrorText(getApplicationContext(),"Error BeautyToast!", BeautyToast.LENGTH_SHORT);

//        beautyToast.isShowIcon(false);
//
//        beautyToast.setText("BeautyToast!!");
//
////        beautyToast.isGravityFullScreen(true);///注意：只针对Gravity.TOP！且必须API 16+！
//        beautyToast.setGravity(Gravity.TOP | Gravity.RIGHT, 10, 10);
//
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
////        beautyToast.setGradientDrawableCornerRadius(BeautyToast.HALF_HEIGHT_CORNER_RADIUS);
////        beautyToast.setGradientDrawableCornerRadius(BeautyToast.HALF_HEIGHT_CORNER_RADIUS / 4); ///test
//        beautyToast.setGradientDrawableCornerRadius(10);    ///test
//        /* --- 获得并设置View、TextView、ImageView --- */
//
//        beautyToast.setText("new BeautyToast!");
//        beautyToast.show();


        /* -------------------- animation -------------------- */
        ///AnimationIn 入场动画
        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_LEFT_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_TOP_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_RIGHT_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_BOTTOM_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_FADE_IN);
        ///AnimationOut 出场动画
        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_LEFT_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_TOP_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_RIGHT_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_BOTTOM_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_FADE_OUT);
        ///取消动画
//        beautyToast.setAnimationOut(AnimationUtil.NO_ANIMATION);

        beautyToast.show();


        /* -------------------- target -------------------- */
        target = findViewById(R.id.target);
        target1 = findViewById(R.id.target1);

//        beautyToast.isGravityFullScreen(true);///注意：只针对Gravity.TOP！且必须API 16+！
//        beautyToast.setTarget(target1, 0, 0);///注意：Target必须已经显示（即Layout完成），否则抛出异常！
//        beautyToast.show();

    }

    @Override
    protected void onStart() {
        Log.d("TAG", "onStart()# ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("TAG", "onResume()# ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("TAG", "onPause()# ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("TAG", "onStop()# ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("TAG", "onDestroy()# ");
        super.onDestroy();

        ///注意：当Context（如Activity/Fragment等）销毁时必须调用此方法，避免内存泄漏！
//        ToastBase.clear(this);
    }

    public void btn1Click(View view) {
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", 3500);
//        ToastBase toastBase = ToastBase.makeText(this,"ToastBase!", 3500);
//        toastBase.show();


        /* -------------------- target -------------------- */
        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(), "BeautyToast! target1", BeautyToast.LENGTH_LONG);
//        BeautyToast beautyToast = BeautyToast.makeText(this, "BeautyToast! target1", BeautyToast.LENGTH_LONG);
//        BeautyToast beautyToast = BeautyToast.makeWarningText(getApplicationContext(), "WarningBeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeInfoText(getApplicationContext(), "Info BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeSuccessText(getApplicationContext(), "Success BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeErrorText(getApplicationContext(), "Error BeautyToast!", BeautyToast.LENGTH_SHORT);

//        beautyToast.isGravityFullScreen(true);///注意：只针对Gravity.TOP！且必须API 16+！

        ///注意：Target必须已经显示（即Layout完成），否则抛出异常！比如不能在Activity的onCreate()中调用本方法
        beautyToast.setTarget(target1, ToastUtil.GRAVITY_ABOVE_TARGET, 0, 0);

        ///AnimationIn 入场动画
        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_LEFT_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_TOP_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_RIGHT_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_BOTTOM_IN);
//        beautyToast.setAnimationIn(AnimationUtil.ANIMATION_FADE_IN);
        ///取消动画
//        beautyToast.setAnimationIn(AnimationUtil.NO_ANIMATION);
        ///AnimationOut 出场动画
        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_LEFT_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_TOP_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_RIGHT_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_BOTTOM_OUT);
//        beautyToast.setAnimationOut(AnimationUtil.ANIMATION_FADE_OUT);
        ///取消动画
//        beautyToast.setAnimationOut(AnimationUtil.NO_ANIMATION);

        beautyToast.show();
    }

}

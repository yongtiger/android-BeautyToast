package cc.brainbook.android.beautytoast.sample;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import cc.brainbook.android.beautytoast.BeautyToasty;
import cc.brainbook.android.beautytoast.ToastyBase;
import cc.brainbook.android.beautytoast.util.ToastUtil;

public class Main2Activity extends AppCompatActivity {

    private View target;
    private View target1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate: ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW},
                1);


//        /* ========================= 参考对比原生Toast ========================= */
//        Toast toast = Toast.makeText(getApplicationContext(), "Toast!", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//        toast.show();


        /* ========================= ToastyBase ========================= */

        /* -------------------- makeText -------------------- */
//        ToastyBase toastyBase = ToastyBase.makeText(getApplicationContext(),"ToastyBase!", ToastyBase.LENGTH_SHORT);
//        ToastyBase toastyBase = ToastyBase.makeText(getApplicationContext(),"ToastyBase!", ToastyBase.LENGTH_LONG);
//        ToastyBase toastyBase = ToastyBase.makeText(getApplicationContext(),"ToastyBase!", 15000);
//        ToastyBase toastyBase = ToastyBase.makeText(this,"ToastyBase!", 15000);   ///测试销毁时是否内存泄漏

//        toastyBase.setText("ToastyBase!!");

//        toastyBase.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);

//        toastyBase.show();


        /* -------------------- new -------------------- */
//        ToastyBase toastyBase = new ToastyBase(this);
//
//        /* --- 获得并设置View、TextView --- */
//        ///设置View
//        toastyBase.setView(ToastyBase.getDefaultView());
//        ///设置TextView
//        toastyBase.setTextView(ToastyBase.getDefaultTextView());
//
//        toastyBase.setText("new ToastyBase!!");
//
//        toastyBase.setDuration(ToastyBase.LENGTH_LONG);
//        /* --- 获得并设置View、TextView --- */
//
//        toastyBase.setText("new ToastyBase!");
//        toastyBase.show();


        /* ========================= BeautyToasty ========================= */

        /* -------------------- makeText -------------------- */
//        BeautyToasty beautyToasty = BeautyToasty.makeText(getApplicationContext(),"BeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeText(getApplicationContext(),"BeautyToasty!", BeautyToasty.LENGTH_LONG);
//        BeautyToasty beautyToasty = BeautyToasty.makeText(getApplicationContext(),"BeautyToasty!", 15000);
//        BeautyToasty beautyToasty = BeautyToasty.makeText(getApplicationContext(),"BeautyToasty!", 15000);
//        BeautyToasty beautyToasty = BeautyToasty.makeText(this,"ToastyBase!", 15000);   ///测试销毁时是否内存泄漏

//        BeautyToasty beautyToasty = BeautyToasty.makeWarningText(getApplicationContext(),"WarningBeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeInfoText(getApplicationContext(),"Info BeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeSuccessText(getApplicationContext(),"Success BeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeErrorText(getApplicationContext(),"Error BeautyToasty!", BeautyToasty.LENGTH_SHORT);

//        beautyToasty.isShowIcon(false);

//        beautyToasty.setText("BeautyToasty!!");
//        beautyToasty.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//
//        beautyToasty.show();

        /* -------------------- new -------------------- */
//        BeautyToasty beautyToasty = new BeautyToasty(this);
//
//        /* --- 获得并设置View、TextView、ImageView --- */
//        ///设置View
//        beautyToasty.setView(BeautyToasty.getDefaultView());
//        ///设置TextView
//        beautyToasty.setTextView(BeautyToasty.getDefaultTextView()) ;
//        ///设置ImageView
//        beautyToasty.setImageView(BeautyToasty.getDefaultImageView()) ;
//
//        beautyToasty.setText("new BeautyToasty!!");
//
//        beautyToasty.setDuration(BeautyToasty.LENGTH_LONG);
//
//        ///设置圆角弧度
//        beautyToasty.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS);
////        beautyToasty.setGradientDrawableCornerRadius(HALF_HEIGHT_CORNER_RADIUS / 4); ///test
////        beautyToasty.setGradientDrawableCornerRadius(10);    ///test
//        /* --- 获得并设置View、TextView、ImageView --- */
//
//        beautyToasty.setText("new BeautyToasty!");
//        beautyToasty.show();


        /* -------------------- animation -------------------- */
        ///AnimationIn 入场动画
//        beautyToasty.setAnimationIn(AnimationUtil.ANIMATION_LEFT_IN);
//        beautyToasty.setAnimationIn(AnimationUtil.ANIMATION_TOP_IN);
//        beautyToasty.setAnimationIn(AnimationUtil.ANIMATION_RIGHT_IN);
//        beautyToasty.setAnimationIn(AnimationUtil.ANIMATION_BOTTOM_IN);
//        beautyToasty.setAnimationIn(AnimationUtil.ANIMATION_FADE_IN);
        ///AnimationOut 出场动画
//        beautyToasty.setAnimationOut(AnimationUtil.ANIMATION_LEFT_OUT);
//        beautyToasty.setAnimationOut(AnimationUtil.ANIMATION_TOP_OUT);
//        beautyToasty.setAnimationOut(AnimationUtil.ANIMATION_RIGHT_OUT);
//        beautyToasty.setAnimationOut(AnimationUtil.ANIMATION_BOTTOM_OUT);
//        beautyToasty.setAnimationOut(AnimationUtil.ANIMATION_FADE_OUT);
        ///取消动画
//        beautyToasty.setAnimationOut(AnimationUtil.NO_ANIMATION);

//        beautyToasty.show();

        /* -------------------- target -------------------- */
        target = findViewById(R.id.target);
        target1 = findViewById(R.id.target1);

//        beautyToasty.setTarget(target1, 0, 0);///注意：Target必须已经显示（即Layout完成），否则抛出异常！
//        beautyToasty.show();


        /* -------------------- update -------------------- */
//        mToastyBase = BeautyToasty.makeText(getApplicationContext(),"mToastyBase!", 15000);
//        mToastyBase.show();

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
//        ToastyBase.clear(this);
    }

    public void btn1Click(View view) {
//        ToastyBase toastyBase = ToastyBase.makeText(getApplicationContext(),"ToastyBase!", 3500);///需要浮动窗权限！
//        toastyBase = ToastyBase.makeText(this,"ToastyBase!", 35000);
//        toastyBase.show();

        /* -------------------- target -------------------- */
        BeautyToasty beautyToasty = BeautyToasty.makeText(getApplicationContext(),"BeautyToasty!", BeautyToasty.LENGTH_LONG);
//        BeautyToasty beautyToasty = BeautyToasty.makeWarningText(getApplicationContext(),"WarningBeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeInfoText(getApplicationContext(),"Info BeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeSuccessText(getApplicationContext(),"Success BeautyToasty!", BeautyToasty.LENGTH_SHORT);
//        BeautyToasty beautyToasty = BeautyToasty.makeErrorText(getApplicationContext(),"Error BeautyToasty!", BeautyToasty.LENGTH_SHORT);
        ///注意：Target必须已经显示（即Layout完成），否则抛出异常！比如不能在Activity的onCreate()中调用本方法
        beautyToasty.setTarget(target1, ToastUtil.GRAVITY_ABOVE_TARGET, 0, 0);
        beautyToasty.show();


        /* -------------------- update -------------------- */
//        mToastyBase.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//        mToastyBase.update();

    }
    private ToastyBase mToastyBase;
}

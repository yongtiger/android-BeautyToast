package cc.brainbook.android.beautytoast.sample;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import cc.brainbook.android.beautytoast.BeautyToast;
import cc.brainbook.android.beautytoast.ToastBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TAG", "onCreate: --------------");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        /* ------------- 参考对比原生Toast ------------- */
//        Toast toast = Toast.makeText(getApplicationContext(), "Toast!", Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//        toast.show();


        /* ------------- ToastBase ------------- */
//        ///makeText
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_SHORT);
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_LONG);
////        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", 15000);
//        ToastBase toastBase = ToastBase.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏
//
////        toastBase.setText("ToastBase!!");
////        toastBase.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
//
//        toastBase.show();

//        ///new
//        ToastBase toastBase = new ToastBase(this);
//
//        /* ------------------ 获得并设置View、TextView ------------------ */
//        final LayoutInflater inflate = (LayoutInflater)
//                this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        @LayoutRes int resource;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            resource = cc.brainbook.android.beautytoast.R.layout.layout_transient_notification_api_27;
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            resource = cc.brainbook.android.beautytoast.R.layout.layout_transient_notification_api_21;
//        } else {
//            resource = cc.brainbook.android.beautytoast.R.layout.layout_transient_notification;
//        }
//        final View view = inflate.inflate(resource, null);
//        ///设置View
//        toastBase.setView(view);
//        ///设置TextView
//        toastBase.setTextView((TextView) view.findViewById(cc.brainbook.android.beautytoast.R.id.message));
//        /* ------------------ 获得并设置View、TextView ------------------ */
//
//        toastBase.setText("new ToastBase!");
//        toastBase.show();


        /* ------------- BeautyToast ------------- */
        ///makeText
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", BeautyToast.LENGTH_LONG);
//        BeautyToast beautyToast = BeautyToast.makeText(getApplicationContext(),"BeautyToast!", 15000);
//        BeautyToast beautyToast = BeautyToast.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏

        BeautyToast beautyToast = BeautyToast.makeWarningText(getApplicationContext(),"WarningBeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeInfoText(getApplicationContext(),"Info BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeSuccessText(getApplicationContext(),"Success BeautyToast!", BeautyToast.LENGTH_SHORT);
//        BeautyToast beautyToast = BeautyToast.makeErrorText(getApplicationContext(),"Error BeautyToast!", BeautyToast.LENGTH_SHORT);

//        beautyToast.isShowIcon(false);

        beautyToast.setText("BeautyToast!!");
        beautyToast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);

        beautyToast.show();

//        ///new
//        BeautyToast beautyToast = new BeautyToast(this);
//        beautyToast.setText("new BeautyToast!");
//        beautyToast.show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ///注意：当Context（如Activity/Fragment等）销毁时必须调用此方法，避免内存泄漏！
        ToastBase.clear(this);
    }
}

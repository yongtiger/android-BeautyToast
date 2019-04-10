package cc.brainbook.android.beautytoast.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

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
        ///makeText
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_SHORT);
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", ToastBase.LENGTH_LONG);
//        ToastBase toastBase = ToastBase.makeText(getApplicationContext(),"ToastBase!", 15000);
        ToastBase toastBase = ToastBase.makeText(this,"ToastBase!", 15000);   ///测试销毁时是否内存泄漏

//        toastBase.setText("ToastBase!!");
//        toastBase.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);

        toastBase.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ///注意：当Context（如Activity/Fragment等）销毁时必须调用此方法，避免内存泄漏！
        ToastBase.clear(this);
    }
}

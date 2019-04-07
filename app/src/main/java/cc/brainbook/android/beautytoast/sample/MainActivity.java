package cc.brainbook.android.beautytoast.sample;

import android.app.Activity;
import android.graphics.Color;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import cc.brainbook.android.beautytoast.BeautyToast;

public class MainActivity extends Activity {

    private View target;
    private View target1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.RIGHT, 50, 50);
        toast.show();

        BeautyToast.success(getApplicationContext())
                .text("1")
                .layoutGravity(BeautyToast.LAYOUT_GRAVITY_TOP)
                .offsetX(50)
                .offsetY(50)
                .show();


        target = findViewById(R.id.target);
        target1 = findViewById(R.id.target1);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .show();
            }
        });

        findViewById(R.id.btn21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .radius(0)
                        .show();
            }
        });

        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.error(v.getContext())
                        .text("this is text")
                        .show();
            }
        });

        findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.info(v.getContext())
                        .text(R.string.text_test_content)
                        .show();
            }
        });

        findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.normal(v.getContext())
                        .text(R.string.text_test_content)
                        .show();
            }
        });

        findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.warning(v.getContext())
                        .text(R.string.text_test_content)
                        .show();
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .animate(true)
                        .show();
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_RIGHT)
                        .target(target1)
                        .show();
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_TOP)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_LEFT)
                        .target(target1)
                        .show();
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_BOTTOM)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_RIGHT)
                        .sameLength(true)
                        .target(target1)
                        .show();
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_TOP)
                        .sameLength(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_LEFT)
                        .sameLength(true)
                        .target(target1)
                        .show();
            }
        });

        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .layoutGravity(BeautyToast.LAYOUT_GRAVITY_BOTTOM)
                        .sameLength(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .animationGravity(BeautyToast.ANIMATION_GRAVITY_LEFT)
                        .animate(true)
                        .show();
            }
        });

        findViewById(R.id.btn15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .animationGravity(BeautyToast.ANIMATION_GRAVITY_RIGHT)
                        .animate(true)
                        .show();
            }
        });

        findViewById(R.id.btn16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .animationGravity(BeautyToast.ANIMATION_GRAVITY_BOTTOM)
                        .animate(true)
                        .show();
            }
        });

        findViewById(R.id.btn17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .relativeGravity(BeautyToast.RELATIVE_GRAVITY_START)
                        .sameLength(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .relativeGravity(BeautyToast.RELATIVE_GRAVITY_CENTER)
                        .sameLength(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .relativeGravity(BeautyToast.RELATIVE_GRAVITY_END)
                        .sameLength(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .tintColor(Color.TRANSPARENT)
                        .relativeGravity(BeautyToast.RELATIVE_GRAVITY_END)
                        .sameLength(true)
                        .animate(true)
                        .target(target)
                        .show();
            }
        });

        findViewById(R.id.btn22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.success(v.getContext())
                        .text(R.string.text_test_content)
                        .show();
            }
        });


        findViewById(R.id.btn23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeautyToast.warning(v.getContext())
                        .text(R.string.text_test_content)
                        .tag(1)
                        .show();
            }
        });

    }
}

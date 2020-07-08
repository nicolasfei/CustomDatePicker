package com.nicolas.customdatepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nicolas.library.CustomDatePicker;
import com.nicolas.library.CustomDateSlotPicker;
import com.nicolas.library.DateFormatUtils;

public class MainActivity extends AppCompatActivity {

    CustomDateSlotPicker mTimerPicker;
    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.textView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimerPicker.show("yyyy-MM-dd HH:mm");
                mTimerPicker.setSelectedTime(DateFormatUtils.long2Str(System.currentTimeMillis(), true), true);
            }
        });

        initPicker();
    }

    private void initPicker() {
        String beginTime = "1970-01-01 12:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis() + 63072000000L, true);
        if (mTimerPicker == null) {
            // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
            mTimerPicker = new CustomDateSlotPicker(this, new CustomDateSlotPicker.Callback() {
                @Override
                public void onTimeSelected(long timestampStart,long timestampEnd) {
                    view.setText(DateFormatUtils.long2Str(timestampStart, true)+"---"+DateFormatUtils.long2Str(timestampEnd, true));
                }
            }, beginTime, endTime);
            // 允许点击屏幕或物理返回键关闭
            mTimerPicker.setCancelable(true);
            // 显示时和分
            mTimerPicker.setCanShowPreciseTime(true);
            // 允许循环滚动
            mTimerPicker.setScrollLoop(false);
            // 允许滚动动画
            mTimerPicker.setCanShowAnim(true);
        }
    }
}

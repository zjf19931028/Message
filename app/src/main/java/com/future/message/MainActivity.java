package com.future.message;

import android.Manifest;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.future.message.base.BaseActivity;
import com.future.message.constant.Constant;


public class MainActivity extends BaseActivity {

    private Button mBtnRecord;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!hasPermission(Constant.RECORD_AUDIO_PERMISSION)){
                requestPermission(Constant.RECORD_AUDIO_CODE,Constant.RECORD_AUDIO_PERMISSION);
                return false;
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        mBtnRecord = findViewById(R.id.btn_record);
    }

    private void setListener() {
        mBtnRecord.setOnTouchListener(mOnTouchListener);
    }

    private void initData() {

    }

    @Override
    public void doRecord() {

    }
}
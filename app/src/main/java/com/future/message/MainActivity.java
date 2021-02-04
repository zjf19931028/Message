package com.future.message;

import android.Manifest;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.future.message.base.BaseActivity;
import com.future.message.constant.Constant;
import com.future.message.util.App;
import com.future.message.util.AudioRecordManager;
import com.future.message.util.MediaRecorderManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends BaseActivity {

    private Button mBtnMediaRecorder;
    private Button mBtnAudioRecord;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!hasPermission(Constant.RECORD_AUDIO_PERMISSION)) {
                requestPermission(Constant.RECORD_AUDIO_CODE, Constant.RECORD_AUDIO_PERMISSION);
                return false;
            }
            switch (v.getId()) {
                case R.id.btn_media_recorder:
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            AudioRecordManager.SINGLETON.init();
                            AudioRecordManager.SINGLETON.startRecording();
                            break;
                        case MotionEvent.ACTION_UP:
                            AudioRecordManager.SINGLETON.stopRecording();
                            break;
                    }
                    break;
                case R.id.btn_audio_record:
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            MediaRecorderManager.SINGLETON.startRecord();
                            break;
                        case MotionEvent.ACTION_UP:
                            MediaRecorderManager.SINGLETON.stopRecord();
                            break;
                    }
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
        mBtnMediaRecorder = findViewById(R.id.btn_media_recorder);
        mBtnAudioRecord = findViewById(R.id.btn_audio_record);
    }

    private void setListener() {
        mBtnMediaRecorder.setOnTouchListener(mOnTouchListener);
        mBtnAudioRecord.setOnTouchListener(mOnTouchListener);
    }

    private void initData() {

    }

    @Override
    public void doRecord() {

    }

}
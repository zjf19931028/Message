package com.future.message;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.future.message.base.BaseActivity;
import com.future.message.constant.Constant;
import com.future.message.receiver.ScanBlueReceiver;
import com.future.message.receiver.ScanBtCallBack;
import com.future.message.util.AudioRecordManager;
import com.future.message.util.BluetoothManager;
import com.future.message.util.MediaRecorderManager;
import com.future.message.util.ShowLogUtil;

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
                            MediaRecorderManager.SINGLETON.startRecord();
                            break;
                        case MotionEvent.ACTION_UP:
                            MediaRecorderManager.SINGLETON.stopRecord();
                            break;
                    }
                    break;
                case R.id.btn_audio_record:
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
//        File file = FileUtil.createFile();
//        FileUtil.byteWrite(file);
//        FileUtil.byteRead(file);
//        File newFile = FileUtil.createFile();
//        FileUtil.byteReadToWrite(file,newFile);
//        FileUtil.stringWrite(file);
//        FileUtil.stringRead(file);
//         File newFile = FileUtil.createFile();
//        FileUtil.stringReadToWrite(file,newFile);
        if (!hasPermission(Constant.LOCATION_PERMISSION)){
            requestPermission(Constant.LOCATION_CODE,Constant.LOCATION_PERMISSION);
        }
        boolean isSupport = BluetoothManager.SINGLETON.isSupport();
        boolean isEnable = BluetoothManager.SINGLETON.isEnable();
        boolean checkGPSIsOpen = BluetoothManager.SINGLETON.checkGPSIsOpen();
        ShowLogUtil.info("isSupport="+isSupport);
        ShowLogUtil.info("isEnable="+isEnable);
        ShowLogUtil.info("checkGPSIsOpen="+checkGPSIsOpen);
        if (!isEnable){
            BluetoothManager.SINGLETON.openBtAsyn();
        }
        if (!checkGPSIsOpen){
            BluetoothManager.SINGLETON.openGPS(this);
        }
        ScanBlueReceiver scanBlueReceiver = new ScanBlueReceiver(new ScanBtCallBack() {
            @Override
            public void onScanStarted() {

            }

            @Override
            public void onScanFinished() {

            }

            @Override
            public void onScanning(BluetoothDevice device) {
                ShowLogUtil.info("device="+device.getName());
            }
        });
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        IntentFilter filter3 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(scanBlueReceiver,filter1);
        registerReceiver(scanBlueReceiver,filter2);
        registerReceiver(scanBlueReceiver,filter3);
        BluetoothManager.SINGLETON.scanBt();
    }

    @Override
    public void doRecord() {

    }

}
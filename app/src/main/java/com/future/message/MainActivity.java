package com.future.message;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.future.message.base.BaseActivity;
import com.future.message.constant.Constant;
import com.future.message.receiver.ScanBlueReceiver;
import com.future.message.receiver.ScanBtCallBack;
import com.future.message.util.AudioRecordManager;
import com.future.message.util.BluetoothManager;
import com.future.message.util.FileUtil;
import com.future.message.util.MediaRecorderManager;
import com.future.message.util.ReflexUtil;
import com.future.message.util.ShowLogUtil;
import com.future.message.util.Solution;

import java.io.File;
import java.lang.reflect.Method;

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
                            MediaRecorderManager.SINGLETON.startRecord(new MediaRecorderManager.IOnMediaRecordListener() {
                                @Override
                                public void onStart() {
                                    ShowLogUtil.info("开始录音啦");
                                }

                                @Override
                                public void onStop() {
                                    ShowLogUtil.info("结束录音啦");
                                }

                                @Override
                                public void onError(Exception e) {
                                    ShowLogUtil.info("录音出错啦");
                                }

                                @Override
                                public void onVolumeChange(int curVolume) {
                                    ShowLogUtil.info("录音声音变化啦 " + curVolume);
                                }
                            });
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
        doFile();
    }

    @Override
    public void doRecord() {

    }

    private void doReflex(){
        ReflexUtil.getInfo();
    }

    private void doFile() {
        File file = FileUtil.createFile();
        FileUtil.byteWrite(file);
        FileUtil.byteRead(file);
        FileUtil.delete(file);
        ShowLogUtil.info(file.getParentFile().delete());


//        File newFile = FileUtil.createFile();
//        FileUtil.byteReadToWrite(file, newFile);
//        FileUtil.stringWrite(file);
//        FileUtil.stringRead(file);
//        File newFile = FileUtil.createFile();
//        FileUtil.stringReadToWrite(file, newFile);
    }

    private void doBluetooth() {
        if (!hasPermission(Constant.LOCATION_PERMISSION)) {
            requestPermission(Constant.LOCATION_CODE, Constant.LOCATION_PERMISSION);
        }
        boolean isSupport = BluetoothManager.SINGLETON.isSupport();
        boolean isEnable = BluetoothManager.SINGLETON.isEnable();
        boolean checkGPSIsOpen = BluetoothManager.SINGLETON.checkGPSIsOpen();
        ShowLogUtil.info("isSupport=" + isSupport);
        ShowLogUtil.info("isEnable=" + isEnable);
        ShowLogUtil.info("checkGPSIsOpen=" + checkGPSIsOpen);
        if (!isEnable) {
            BluetoothManager.SINGLETON.openBtAsyn();
        }
        if (!checkGPSIsOpen) {
            BluetoothManager.SINGLETON.openGPS(this);
        }
        ScanBlueReceiver scanBlueReceiver = new ScanBlueReceiver(new ScanBtCallBack() {
            @Override
            public void onScanStarted() {
                ShowLogUtil.info("开始扫描");
            }

            @Override
            public void onScanFinished() {
                ShowLogUtil.info("结束扫描");
            }

            @Override
            public void onScanning(BluetoothDevice device) {
                if (!TextUtils.isEmpty(device.getName()))
                    ShowLogUtil.info("device=" + device.getName());
                if (TextUtils.equals("张竟方的iPhone", device.getName())) {
                    BluetoothManager.SINGLETON.pin(device);
//                    BluetoothManager.SINGLETON.cancelPinBt(device);
                }
            }

            @Override
            public void onBondRequest(BluetoothDevice device) {
                ShowLogUtil.info("配对请求");

            }

            @Override
            public void onBondFail() {
                ShowLogUtil.info("配对失败");
            }

            @Override
            public void onBondBonding() {
                ShowLogUtil.info("配对中");
            }

            @Override
            public void onBondSuccess() {
                ShowLogUtil.info("配对成功");
            }

            @Override
            public void onConnectSuccess() {
                ShowLogUtil.info("连接成功");
            }

            @Override
            public void onConnectFail() {
                ShowLogUtil.info("连接失败");
            }
        });
        IntentFilter filter1 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        IntentFilter filter2 = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        IntentFilter filter3 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter4 = new IntentFilter(BluetoothDevice.ACTION_PAIRING_REQUEST);
        IntentFilter filter5 = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(scanBlueReceiver, filter1);
        registerReceiver(scanBlueReceiver, filter2);
        registerReceiver(scanBlueReceiver, filter3);
        registerReceiver(scanBlueReceiver, filter4);
        registerReceiver(scanBlueReceiver, filter5);
        BluetoothManager.SINGLETON.scanBt();
    }

}
package com.future.message.receiver;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.future.message.util.ShowLogUtil;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/23 15:16
 * Description: 蓝牙扫描广播监听器
 */
public class ScanBlueReceiver extends BroadcastReceiver {
    private ScanBtCallBack mCallBack;

    public ScanBlueReceiver(ScanBtCallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                ShowLogUtil.info("开始扫描");
                mCallBack.onScanStarted();
                break;
            case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                ShowLogUtil.info("结束扫描");
                mCallBack.onScanFinished();
                break;
            case BluetoothDevice.ACTION_FOUND:
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mCallBack.onScanning(device);
                break;
        }
    }

}

package com.future.message.util;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;

import com.future.message.receiver.ScanBtCallBack;

import java.io.IOException;
import java.util.UUID;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/24 13:49
 * Description:
 */
public class ConnectBlueTask extends AsyncTask<BluetoothDevice, Integer, BluetoothSocket> {
    private BluetoothDevice mBluetoothDevice;
    private ScanBtCallBack mCallBack;

    @Override
    protected BluetoothSocket doInBackground(BluetoothDevice... bluetoothDevices) {
        mBluetoothDevice = bluetoothDevices[0];
        BluetoothSocket socket = null;
        try {
            socket = mBluetoothDevice.createRfcommSocketToServiceRecord(UUID.randomUUID());
            if (socket != null && !socket.isConnected())
                socket.connect();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return socket;
    }

    @Override
    protected void onPostExecute(BluetoothSocket bluetoothSocket) {
        if (bluetoothSocket != null && bluetoothSocket.isConnected()) {
            if (mCallBack != null)mCallBack.onConnectSuccess();
        }else {
            if (mCallBack != null)mCallBack.onConnectFail();
        }
    }
}

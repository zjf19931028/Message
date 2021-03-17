package com.future.message;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.future.message.algorithm.SolutionLinkedList;
import com.future.message.algorithm.SolutionTreeNode;
import com.future.message.algorithm.bean.ListNode;
import com.future.message.algorithm.bean.MyHashMap;
import com.future.message.algorithm.bean.MyHashSet;
import com.future.message.algorithm.bean.MyLinkedList;
import com.future.message.algorithm.bean.TreeNode;
import com.future.message.base.BaseActivity;
import com.future.message.constant.Constant;
import com.future.message.receiver.ScanBlueReceiver;
import com.future.message.receiver.callback.ScanBtCallBack;
import com.future.message.util.AudioRecordManager;
import com.future.message.util.BluetoothManager;
import com.future.message.util.CustomPermission;
import com.future.message.util.FileUtil;
import com.future.message.util.MediaRecorderManager;
import com.future.message.util.ReflexUtil;
import com.future.message.util.ShowLogUtil;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static com.future.message.constant.Constant.LOCATION_PERMISSION;
import static com.future.message.constant.Constant.RECORD_AUDIO_CODE;
import static com.future.message.constant.Constant.RECORD_AUDIO_PERMISSION;

public class MainActivity extends BaseActivity {

    private Button mBtnMediaRecorder;
    private Button mBtnAudioRecord;
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!hasPermission(RECORD_AUDIO_PERMISSION)) {
                requestPermission(RECORD_AUDIO_CODE, RECORD_AUDIO_PERMISSION);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initData() {
//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(1);
//        ListNode l3 = new ListNode(1);
//        ListNode l4 = new ListNode(1);
//        ListNode l5 = new ListNode(1);
//        ListNode l6 = new ListNode(1);
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = null;
//        boolean hasCycle = SolutionLinkedList.hasCycle(l1);
//        ShowLogUtil.info("hasCycle=" + hasCycle);

        TreeNode root = new TreeNode();
        root.val = 3;
        TreeNode left = new TreeNode();
        left.val = 9;
        TreeNode right = new TreeNode();
        right.val = 20;
        TreeNode leftLeft = new TreeNode();
        leftLeft.val = 9;
        TreeNode leftLeftLeft = new TreeNode();
        leftLeftLeft.val = 9;
        TreeNode rightLeft = new TreeNode();
        rightLeft.val = 15;
        TreeNode rightRight = new TreeNode();
        rightRight.val = 7;
        root.left = left;
        root.right = right;
        root.left.left = leftLeft;
        root.left.left.left = leftLeftLeft;
        root.right.left = rightLeft;
        root.right.right = rightRight;
        List<List<Integer>> lists = SolutionTreeNode.levelOrder(root);
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                ShowLogUtil.info("i=" + i + ",j=" + j + "," + lists.get(i).get(j));
            }
        }


//        if (!hasPermission(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION})) {
//            requestPermission(RECORD_AUDIO_CODE, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION});
//        }
//        boolean palindrome = SolutionString.isPalindrome("A man, a plan, a canal: Panama");
//        ListNode listNode1=new ListNode(1);
//        ListNode listNode2=new ListNode(2);
//        ListNode listNode3=new ListNode(3);
//        ListNode listNode4=new ListNode(4);
//        ListNode listNode5=new ListNode(5);
//        listNode1.next =  listNode2;
//        listNode2.next =  listNode3;
//        listNode3.next =  listNode4;
//        listNode4.next =  listNode5;
//        SolutionLinkedList.removeNthFromEnd(listNode1,3);
//        MyQueue q = new MyQueue();
//        q.enQueue(5);
//        q.enQueue(3);
//        if (q.isEmpty() == false) {
//            ShowLogUtil.info(q.Front());
//        }
//        q.deQueue();
//        if (q.isEmpty() == false) {
//            ShowLogUtil.info(q.Front());
//        }
//        q.deQueue();
//        if (q.isEmpty() == false) {
//            ShowLogUtil.info(q.Front());
//        }
//        // 1. Initialize a queue.
//        Queue<Integer> q = new LinkedList();
//        // 2. Get the first element - return null if queue is empty.
//        ShowLogUtil.info("The first element is: " + q.peek());
//        // 3. Push new element.
//        q.offer(5);
//        q.offer(13);
//        q.offer(8);
//        q.offer(6);
//        // 4. Pop an element.
//        q.poll();
//        // 5. Get the first element.
//        ShowLogUtil.info("The first element is: " + q.peek());
//        // 7. Get the size of the queue.
//        ShowLogUtil.info("The size is: " + q.size());
//        MyCircularQueue obj = new MyCircularQueue(5);
//        boolean param_1 = obj.enQueue(1);
//        boolean param_7 = obj.enQueue(1);
//        boolean param_8 = obj.enQueue(1);
//        boolean param_9 = obj.enQueue(1);
//        boolean param_10 = obj.enQueue(1);
//        boolean param_11 = obj.enQueue(1);
//        ShowLogUtil.info("param_1="+param_1);
//        ShowLogUtil.info("param_7="+param_7);
//        ShowLogUtil.info("param_8="+param_8);
//        ShowLogUtil.info("param_9="+param_9);
//        ShowLogUtil.info("param_10="+param_10);
//        ShowLogUtil.info("param_11="+param_11);
//        boolean param_2 = obj.deQueue();
//        ShowLogUtil.info("param_2="+param_2);
//        int param_3 = obj.Front();
//        ShowLogUtil.info("param_3="+param_3);
//        int param_4 = obj.Rear();
//        ShowLogUtil.info("param_4="+param_4);
//        boolean param_5 = obj.isEmpty();
//        ShowLogUtil.info("param_5="+param_5);
//        boolean param_6 = obj.isFull();
//        ShowLogUtil.info("param_6="+param_6);
//        TreeNode root = new TreeNode();
//        root.val = 3;
//        TreeNode left = new TreeNode();
//        left.val = 9;
//        TreeNode right = new TreeNode();
//        right.val = 20;
//        root.left = left;
//        root.right = right;
//        TreeNode rightLeft = new TreeNode();
//        rightLeft.val = 15;
//        TreeNode rightRight = new TreeNode();
//        rightRight.val = 7;
//        root.right.left = rightLeft;
//        root.right.right = rightRight;

//        List<List<Integer>> lists = SolutionTree.levelOrder(root);
//        for (int i = 0; i < lists.size(); i++) {
//            ShowLogUtil.info(" ");
//            for (int j = 0; j < lists.get(i).size(); j++) {
//                ShowLogUtil.info("i=" + i + ",j=" + j +","+ lists.get(i).get(j));
//            }
//        }
    }

    @Override
    public void doRecord() {

    }

    private void doPermission() {
        new CustomPermission(this, true, new CustomPermission.OnFinishedCheck() {
            @Override
            public void finishedCheck() {
                ShowLogUtil.info("finishedCheck");
            }
        }, Manifest.permission.CAMERA);
    }

    private void doReflex() {
        ReflexUtil.getInfo();
    }

    private void doFile() {
        File file = FileUtil.createFile();
//        FileUtil.byteWrite(file);
//        FileUtil.byteRead(file);
//        FileUtil.delete(file);
        ShowLogUtil.info("file.getAbsolutePath()=" + file.getAbsolutePath());


//        File newFile = FileUtil.createFile();
//        FileUtil.byteReadToWrite(file, newFile);
//        FileUtil.stringWrite(file);
//        FileUtil.stringRead(file);
//        File newFile = FileUtil.createFile();
//        FileUtil.stringReadToWrite(file, newFile);
    }

    private void doBluetooth() {
        if (!hasPermission(LOCATION_PERMISSION)) {
            requestPermission(Constant.LOCATION_CODE, LOCATION_PERMISSION);
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
                if (TextUtils.equals("“张竟方”的 iPhone", device.getName())) {
                    BluetoothManager.SINGLETON.pin(device);
                }
//                if (!TextUtils.isEmpty(device.getName()))
//                    ShowLogUtil.info("device=" + device.getName());
//                if (TextUtils.equals("张竟方的iPhone", device.getName())) {
//                    BluetoothManager.SINGLETON.pin(device);
//                    BluetoothManager.SINGLETON.cancelPinBt(device);
//                }
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
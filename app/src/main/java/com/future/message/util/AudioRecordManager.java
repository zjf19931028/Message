package com.future.message.util;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/3 20:41
 * Description: 录音管理类
 */
public class AudioRecordManager {
    // 采样率
    private final int mSampleRateInHz = 44100;
    // 声道
    private final int mChannelConfig = AudioFormat.CHANNEL_IN_MONO;
    // 分辨率
    private final int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;
    // 音频源
    private final int mAudioSource = MediaRecorder.AudioSource.MIC;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private String mRecordFilePath;
    private File mRecordingFile;
    // 录音缓存大小
    private int mRecordBufSize;
    private AudioRecord mAudioRecord;


    public void init(String filePath, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener){
        mRecordFilePath = filePath;
        this.onAudioFocusChangeListener = onAudioFocusChangeListener;
        mRecordBufSize = AudioRecord.getMinBufferSize(mSampleRateInHz,mChannelConfig,mAudioFormat);
        mAudioRecord = new AudioRecord(mAudioSource,mSampleRateInHz,mChannelConfig,mAudioFormat,mRecordBufSize);
        mRecordingFile = new File(mRecordFilePath);
        // 删除存在过的该文件
        if (mRecordingFile.exists()){
            mRecordingFile.delete();
        }
        try {
            mRecordingFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecording(){
        if (mAudioRecord == null || mAudioRecord.getState() == AudioRecord.STATE_UNINITIALIZED){
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(mRecordingFile,true)));
                    byte[] buffer = new byte[mRecordBufSize];
                    mAudioRecord.startRecording();
                    while (mAudioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING){
                        int bufferReadResult = mAudioRecord.read(buffer, 0, mRecordBufSize);
                        for (int i = 0; i < bufferReadResult; i++) {
                            dos.write(buffer[i]);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

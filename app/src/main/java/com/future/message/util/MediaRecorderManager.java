package com.future.message.util;

import android.media.MediaRecorder;
import java.io.File;
import java.io.IOException;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/4 14:26
 * Description: 录音管理类
 */
public enum MediaRecorderManager {
    SINGLETON;
    private MediaRecorder mMediaRecorder;
    private File mOriginalFile;
    public static final int MAX_LENGTH = 1000 * 60;

    /**
     * Author: JfangZ
     * Email:zhangjingfang@jeejio.com
     * Date: 2021/2/4 15:23
     * Description: 开始录音
     */
    public void startRecord() {
        if (mMediaRecorder == null) {
            mMediaRecorder = new MediaRecorder();
        }
        try {
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            String mOriginalFilePath = FilePathUtil.getMediaRecorderLocalPath();
            mOriginalFile = new File(mOriginalFilePath);
            if (!mOriginalFile.exists()) mOriginalFile.getParentFile().mkdirs();
            mOriginalFile.createNewFile();
            mMediaRecorder.setOutputFile(mOriginalFilePath);
            mMediaRecorder.setMaxDuration(MAX_LENGTH);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Author: JfangZ
     * Email:zhangjingfang@jeejio.com
     * Date: 2021/2/4 15:25
     * Description: 停止录音
     */
    public void stopRecord() {
        if (mMediaRecorder != null) {
            try {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                mMediaRecorder.release();
                mMediaRecorder = null;
                String encryptFilePath = FilePathUtil.getMediaRecorderEncryptLocalPath();
                File encryptFile = new File(encryptFilePath);
                if (!encryptFile.exists()) encryptFile.getParentFile().mkdirs();
                encryptFile.createNewFile();
                // 密钥
                String aesSecret = EncryptUtil.generateAesSecret();
                // 加密录音文件
                EncryptUtil.encryptFile(mOriginalFile,encryptFile,aesSecret);
                // 解密
                EncryptUtil.decryptFile(encryptFile,aesSecret);
            } catch (Exception e) {
                mMediaRecorder.reset();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }

        }

    }

}

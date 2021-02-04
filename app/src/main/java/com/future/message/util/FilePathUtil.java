package com.future.message.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/4 14:51
 * Description: 文件路工具类
 */
public class FilePathUtil {
    /**
     * Author: JfangZ
     * Email:zhangjingfang@jeejio.com
     * Date: 2021/2/4 14:52
     * Description: 获取录音的本地文件路径
     */
    public static String getMediaRecorderLocalPath() {
        return App.getInstance().getApplicationContext().getCacheDir()
                + File.separator
                + "record"
                + File.separator
                + "mediaRecoder"
                + File.separator
                + new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date(System.currentTimeMillis()))
                + File.separator
                + System.currentTimeMillis()
                + ".m4a";
    }

    /**
     * Author: JfangZ
     * Email:zhangjingfang@jeejio.com
     * Date: 2021/2/4 14:52
     * Description: 获取录音的本地文件路径
     */
    public static String getAudioRecordLocalPath() {
        return App.getInstance().getApplicationContext().getCacheDir()
                + File.separator
                + "record"
                + File.separator
                + "audioRecord"
                + File.separator
                + new SimpleDateFormat("yyyyMMdd", Locale.CHINA).format(new Date(System.currentTimeMillis()))
                + File.separator
                + System.currentTimeMillis()
                + ".m4a";
    }
}

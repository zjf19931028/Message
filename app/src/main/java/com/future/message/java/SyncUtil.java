package com.future.message.java;

/**
 * Author: JfangZ
 * Email: zhangjingfang@jeejio.com
 * Date: 2021/3/19 11:42
 * Description:
 */
public class SyncUtil {
    public synchronized void sync(){
        synchronized (this) {

        }
    }
}

package com.awesome.message.util;

import com.zjingf.awesomesdk.BaseApplication;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/3 18:03
 * Description: 程序入口
 */
public class App extends BaseApplication {
    private static App mApplication = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static App getInstance(){
        return mApplication;
    }
}

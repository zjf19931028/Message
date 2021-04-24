package com.awesome.common.factory.presenter;

import androidx.annotation.StringRes;


/**
 * MVP模式中公共的基本契约
 * Created by Alice on 2021/4/24
 */
public interface BaseContract {
    interface View<T extends Presenter>{
        // 公共的：显示一个字符串错误
        void showError(@StringRes int str);

        // 公共的：显示进度条
        void showLoading();


        // 支持设置一个Presenter
        void setPresenter(T presenter);
    }
    interface Presenter{
        // 公共的开始触发
        void start();

        // 公共的销毁触发
        void destroy();

    }
} 
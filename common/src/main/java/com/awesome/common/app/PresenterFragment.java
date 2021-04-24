package com.awesome.common.app;


import android.content.Context;

import androidx.annotation.NonNull;

import com.awesome.common.factory.presenter.BaseContract;
import com.awesome.sdk.util.ToastUtils;

/**
 * Created by Alice on 2021/4/24
 */
public abstract class PresenterFragment<Presenter extends BaseContract.Presenter> extends Fragment
        implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 在界面onAttach之后触发初始化Presenter
        initPresenter();
    }

    // 初始化Presenter
    protected abstract Presenter initPresenter();


    @Override
    public void showError(int str) {
        ToastUtils.showToast(App.getInstance().getApplicationContext(),"str");
    }

    @Override
    public void showLoading() {
        //显示一个loading
    }

    // 在View中赋值Presenter
    @Override
    public void setPresenter(Presenter presenter) {
        mPresenter = presenter;
    }

}
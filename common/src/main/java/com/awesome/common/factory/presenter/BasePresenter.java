package com.awesome.common.factory.presenter;

/**
 * Created by Alice on 2021/4/24
 */
public class BasePresenter<T extends BaseContract.View>
        implements BaseContract.Presenter {
    protected T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个View，子类可以复写完成
     *
     * @param view
     */
    protected void setView(T view) {
        mView = view;
        mView.setPresenter(this);
    }

    /**
     * 给子类使用获取View的操作
     * 不允许复写
     *
     * @return
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行MLoading调用
        T view = mView;
        if (mView != null) {
            view.showLoading();
        }
    }

    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            view.setPresenter(null);
        }
    }
}
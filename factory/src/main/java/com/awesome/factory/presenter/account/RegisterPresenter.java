package com.awesome.factory.presenter.account;

import com.awesome.common.factory.presenter.BaseContract;
import com.awesome.common.factory.presenter.BasePresenter;

/**
 * Created by Alice on 2021/4/24
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public void register(String phone, String name, String password) {

    }

    @Override
    public boolean checkMobile(String phone) {
        return false;
    }

}
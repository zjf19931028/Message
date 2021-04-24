package com.awesome.message.frags.account;

import com.awesome.common.app.PresenterFragment;
import com.awesome.common.factory.presenter.BaseContract;
import com.awesome.factory.presenter.account.RegisterContract;
import com.awesome.factory.presenter.account.RegisterPresenter;
import com.awesome.message.R;

/**
 * Created by Alice on 2021/4/24
 */
public class RegisterFragment extends PresenterFragment<RegisterContract.Presenter> implements RegisterContract.View {


    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    public void registerSuccess() {

    }
}
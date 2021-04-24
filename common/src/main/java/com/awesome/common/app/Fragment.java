package com.awesome.common.app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/3 19:48
 * Description: Fragment基类
 */
public abstract class Fragment extends androidx.fragment.app.Fragment {
    protected View mRoot;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null) {
            int layId = getContentLayoutId();
            View root = inflater.inflate(layId, container, false);
            initWidget(root);
            mRoot = root;
        }else {
            if (mRoot.getParent()!=null){
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    protected void initWidget(View root) {

    }

    protected abstract int getContentLayoutId();
}

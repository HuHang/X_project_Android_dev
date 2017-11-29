package com.cztek.concept.cargps.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HH
 * Date: 2017/11/29
 */

public abstract class BaseFragment extends Fragment {
    private View mContentView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);
        mContext = getContext();
        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    protected abstract int setLayoutResourceID();

    protected void init(){}

    protected void setUpView(){}

    protected void setUpData(){}


    protected View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }
}

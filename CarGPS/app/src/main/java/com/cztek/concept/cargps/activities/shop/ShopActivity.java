package com.cztek.concept.cargps.activities.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;
import com.cztek.concept.cargps.utils.ViewUtil;

public class ShopActivity extends BaseAppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_shop;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
        initDefaultFragment();
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }

    private void initDefaultFragment(){
        mFragment = ViewUtil.createFragment(ShopSelectFragment.class);
        mFragmentManager.beginTransaction().add(R.id.fragmentContent,mFragment).commit();
    }

}

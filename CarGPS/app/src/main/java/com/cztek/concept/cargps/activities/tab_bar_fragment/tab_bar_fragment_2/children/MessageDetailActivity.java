package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.children;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;
import com.cztek.concept.cargps.utils.ViewUtil;

public class MessageDetailActivity extends BaseAppCompatActivity {

    private FragmentManager mFragmentManager;
    private Fragment mFragment;



    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_message_detail;
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
        mFragment = ViewUtil.createFragment(MessageDetailFragment.class);
        mFragmentManager.beginTransaction().add(R.id.fragmentContent,mFragment).commit();
    }
}

package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_3.main;


import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        toolbarTitle.setText("我的");
    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_1.main;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.shop.ShopActivity;
import com.cztek.concept.cargps.base.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_logo)
    ImageView toolbarLogo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.toMonitorImageView)
    ImageView toMonitorImageView;
    @BindView(R.id.toCarImageView)
    ImageView toCarImageView;
    @BindView(R.id.toDeviceImageView)
    ImageView toDeviceImageView;

    Resources res;



    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        res = getMContext().getResources();
        toolbarLogo.setImageResource(R.mipmap.icon_toshop_white);
        toolbarTitle.setText("安阳绅之星实业有限公司");
    }

    @Override
    protected void setUpView() {
        super.setUpView();
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

    @OnClick({R.id.toolbar_title,R.id.toMonitorImageView, R.id.toCarImageView, R.id.toDeviceImageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_title:
                Intent intent = new Intent();
                intent.setClass(getMContext(), ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.toMonitorImageView:
                break;
            case R.id.toCarImageView:
                break;
            case R.id.toDeviceImageView:
                break;
        }
    }

}

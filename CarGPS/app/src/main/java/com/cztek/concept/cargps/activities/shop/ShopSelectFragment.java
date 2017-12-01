package com.cztek.concept.cargps.activities.shop;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ShopSelectFragment extends BaseFragment {

    @BindView(R.id.toolbar_leftTitle)
    TextView toolbarLeftTitle;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_rightTitle)
    TextView toolbarRightTitle;
    Unbinder unbinder;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_shop_select;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        toolbarLeftTitle.setText("保存");
        toolbarTitle.setText("选择商店");
        toolbarRightTitle.setText("全选");

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

    @OnClick({R.id.toolbar_leftTitle, R.id.toolbar_title, R.id.toolbar_rightTitle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_leftTitle:
                getActivity().onBackPressed();
                break;
            case R.id.toolbar_title:
                break;
            case R.id.toolbar_rightTitle:
                break;
        }
    }
}

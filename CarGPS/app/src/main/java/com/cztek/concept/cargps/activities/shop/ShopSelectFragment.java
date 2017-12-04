package com.cztek.concept.cargps.activities.shop;


import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.shop.adapter.ShopSelectAdapter;
import com.cztek.concept.cargps.activities.shop.bean.ShopBean;
import com.cztek.concept.cargps.base.BaseFragment;
import com.cztek.concept.cargps.constants.GlobalVariables;
import com.cztek.concept.cargps.http.ApiStore;
import com.cztek.concept.cargps.http.HttpClient;
import com.cztek.concept.cargps.http.HttpListCallback;
import com.cztek.concept.cargps.third.HUDProgressManager;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ShopSelectFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.toolbar_leftTitle)
    TextView toolbarLeftTitle;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_rightTitle)
    TextView toolbarRightTitle;
    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;

    KProgressHUD kProgressHUD;

    private ShopSelectAdapter shopSelectAdapter;
    private List<ShopBean> mShopBeanList;
    private Set<String> mSelectedShopIds;

    private ShopSelectAdapter.ItemButtonClickCallback itemButtonClickCallback = new ShopSelectAdapter.ItemButtonClickCallback() {
        @Override
        public void groupButtonClick(int groupPosition) {
            selectShopsWithType(mShopBeanList.get(groupPosition),!mShopBeanList.get(groupPosition).getIsSelected());
        }

        @Override
        public void childButtonClick(int groupPosition, int childPosition) {
            selectShopsWithType(mShopBeanList.get(groupPosition).getChildShops().get(childPosition),!mShopBeanList.get(groupPosition).getChildShops().get(childPosition).getIsSelected());
        }
    };

    private ExpandableListView.OnGroupClickListener mOnGroupClickListener = new ExpandableListView.OnGroupClickListener() {
        @Override
        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            Log.i("OnGroupClickListener", groupPosition + "");

            return false;
        }
    };

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
        toolbarRightTitle.setText("全不选");
        toolbarRightTitle.setSelected(true);

        mShopBeanList = new ArrayList<>();
        mSelectedShopIds = new HashSet<>();
        shopSelectAdapter = new ShopSelectAdapter(getMContext(), mShopBeanList);
        shopSelectAdapter.setmItemButtonClickCallback(itemButtonClickCallback);
        expandableListView.setGroupIndicator(null);
        kProgressHUD = new HUDProgressManager().showLoadingImage(getMContext());

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        expandableListView.setOnGroupClickListener(mOnGroupClickListener);
        expandableListView.setAdapter(shopSelectAdapter);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        callHttpForAllShop();
        mSelectedShopIds.addAll(SPUtils.getInstance(GlobalVariables.infoSp).getStringSet(GlobalVariables.defaultShopIdList));
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
                if (checkToSaveSelectedShopIds()){
                    getActivity().onBackPressed();
                }else {
                    new HUDProgressManager().showMessage(getMContext(),"您必须选择至少一家商店！");
                }

                break;
            case R.id.toolbar_title:
                break;
            case R.id.toolbar_rightTitle:
                boolean isAllSelected = toolbarRightTitle.isSelected();
                toolbarRightTitle.setSelected(!isAllSelected);
                toolbarRightTitle.setText(isAllSelected ? "全不选" : "全选");
                selectShopsWithType(mShopBeanList,isAllSelected);
                break;
        }
    }

    private void callHttpForAllShop() {
        HttpClient.get(ApiStore.getAllShop_url, new HttpListCallback<ShopBean>() {

            @Override
            public void OnSuccess(ArrayList<ShopBean> response) {
                for (ShopBean parent : response){
                    parent.setIsSelected(mSelectedShopIds.contains(parent.getId()));
                    for (ShopBean child : parent.getChildShops()){
                        child.setIsSelected(mSelectedShopIds.contains(child.getId()));
                    }
                }
                mShopBeanList.addAll(response);
                shopSelectAdapter.notifyDataSetChanged();
            }

            @Override
            public void OnFailure(String message) {

            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {
                kProgressHUD.dismiss();
            }
        });
    }


    private void selectShopsWithType(List<ShopBean> shopBeanList, boolean isSelected){
        for (ShopBean parent : shopBeanList) {
            parent.setIsSelected(isSelected);
            for (ShopBean child : parent.getChildShops()) {
                child.setIsSelected(isSelected);
            }
        }
        shopSelectAdapter.notifyDataSetChanged();
    }

    private void selectShopsWithType(ShopBean shopBean, boolean isSelected){
        shopBean.setIsSelected(isSelected);
        for (ShopBean child : shopBean.getChildShops()) {
            child.setIsSelected(isSelected);
        }
        shopSelectAdapter.notifyDataSetChanged();
    }

    private boolean checkToSaveSelectedShopIds(){
        mSelectedShopIds.clear();
        String shopName = null;
        for (ShopBean parent : mShopBeanList) {
            if (parent.getIsSelected()){
                if (shopName == null){
                    shopName = parent.getName();
                }
                mSelectedShopIds.add(parent.getId());
            }
            for (ShopBean child : parent.getChildShops()) {
                if (child.getIsSelected()){
                    if (shopName == null){
                        shopName = child.getName();
                    }
                    mSelectedShopIds.add(child.getId());
                }
            }
        }
        if (mSelectedShopIds.isEmpty()){
            return false;
        }else {
            SPUtils.getInstance(GlobalVariables.infoSp).put(GlobalVariables.selectShopName,shopName);
            SPUtils.getInstance(GlobalVariables.infoSp).put(GlobalVariables.defaultShopIdList,mSelectedShopIds);
            return true;
        }

    }

}

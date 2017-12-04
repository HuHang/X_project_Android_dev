package com.cztek.concept.cargps.activities.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.shop.bean.ShopBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/12/4
 */

public class ShopSelectAdapter extends BaseExpandableListAdapter {

    private List<ShopBean> mShopBeanList;

    private Context mContext;

    private ItemButtonClickCallback mItemButtonClickCallback;

    public ShopSelectAdapter(Context context, List<ShopBean> shopBeanList) {
        mContext = context;
        mShopBeanList = shopBeanList;
//        mItemButtonClickCallback = itemButtonClickCallback;
    }

    public interface ItemButtonClickCallback {
        void groupButtonClick(int groupPosition);

        void childButtonClick(int groupPosition, int childPosition);
    }

    public ItemButtonClickCallback getmItemButtonClickCallback() {
        return mItemButtonClickCallback;
    }

    public void setmItemButtonClickCallback(ItemButtonClickCallback mItemButtonClickCallback) {
        this.mItemButtonClickCallback = mItemButtonClickCallback;
    }



    @Override
    public int getGroupCount() {
        return mShopBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mShopBeanList.get(groupPosition).getChildShops().size();
    }

    @Override
    public ShopBean getGroup(int groupPosition) {
        return mShopBeanList.get(groupPosition);
    }

    @Override
    public ShopBean getChild(int groupPosition, int childPosition) {
        return mShopBeanList.get(groupPosition).getChildShops().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = null;
        GroupHolder groupholder = null;
        if (convertView != null) {
            view = convertView;
            groupholder = (GroupHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.item_shop_parent, null);
            groupholder = new GroupHolder(view);
            view.setTag(groupholder);
        }

        groupholder.nameTextView.setText(getGroup(groupPosition).getName() + "(" + getChildrenCount(groupPosition) + ")");
        groupholder.addressTextView.setText(getGroup(groupPosition).getAddress());
        groupholder.bankTextView.setText(getGroup(groupPosition).getAllBankPath());
        groupholder.selecterLinearLayout.setOnClickListener(new GroupBtnListener(groupPosition));
        groupholder.expandedImageView.setImageResource(isExpanded ? R.mipmap.icon_arrow_down_red : R.mipmap.icon_arrow_right_red);
        groupholder.selecterImageView.setImageResource(getGroup(groupPosition).getIsSelected() ? R.mipmap.icon_select_red : R.mipmap.icon_unselect_red);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = null;
        ChildHolder childholder = null;
        if (convertView != null) {
            view = convertView;
            childholder = (ChildHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.item_shop_child, null);
            childholder = new ChildHolder(view);
            view.setTag(childholder);
        }
        childholder.nameTextView.setText(getChild(groupPosition, childPosition).getName());
        childholder.addressTextView.setText(getChild(groupPosition, childPosition).getAddress());
        childholder.selecterLinearLayout.setOnClickListener(new ChildBtnListener(groupPosition,childPosition));
        childholder.selecterImageView.setImageResource(getChild(groupPosition,childPosition).getIsSelected() ? R.mipmap.icon_select_red : R.mipmap.icon_unselect_red);
        if ("2".equals(getChild(groupPosition, childPosition).getShopType())){
            childholder.bankTextView.setText("二库");
        }else {
            childholder.bankTextView.setText("二网");

        }
        return view;
    }


    static class GroupHolder {
        @BindView(R.id.expandedImageView)
        ImageView expandedImageView;
        @BindView(R.id.nameTextView)
        TextView nameTextView;
        @BindView(R.id.addressTextView)
        TextView addressTextView;
        @BindView(R.id.bankTextView)
        TextView bankTextView;
        @BindView(R.id.selecterLinearLayout)
        LinearLayout selecterLinearLayout;
        @BindView(R.id.selecterImageView)
        ImageView selecterImageView;

        GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.nameTextView)
        TextView nameTextView;
        @BindView(R.id.addressTextView)
        TextView addressTextView;
        @BindView(R.id.bankTextView)
        TextView bankTextView;
        @BindView(R.id.selecterLinearLayout)
        LinearLayout selecterLinearLayout;
        @BindView(R.id.selecterImageView)
        ImageView selecterImageView;

        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public class GroupBtnListener implements View.OnClickListener {

        int mGroupPosition;


        GroupBtnListener(int groupPosition) {
            this.mGroupPosition = groupPosition;
        }

        @Override
        public void onClick(View v) {
            if (mItemButtonClickCallback != null){
                mItemButtonClickCallback.groupButtonClick(mGroupPosition);
            }
        }
    }

    public class ChildBtnListener implements View.OnClickListener {

        int mGroupPosition;
        int mChildPosition;


        ChildBtnListener(int groupPosition, int childPosition) {
            this.mGroupPosition = groupPosition;
            this.mChildPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if (mItemButtonClickCallback != null){
                mItemButtonClickCallback.childButtonClick(mGroupPosition, mChildPosition);
            }
        }
    }
}

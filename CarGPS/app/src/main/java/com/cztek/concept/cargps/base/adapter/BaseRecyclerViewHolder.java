package com.cztek.concept.cargps.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HH
 * Date: 2017/11/29
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public View getView() {
        return mView;
    }
}

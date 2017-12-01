package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.adapter;

import android.content.Context;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean.MessageDetailBean;
import com.cztek.concept.cargps.base.adapter.BaseRecyclerViewAdapter;
import com.cztek.concept.cargps.base.adapter.BaseRecyclerViewHolder;
import com.cztek.concept.cargps.utils.FromatDateStringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/12/1
 */

public class MessageDetailAdapter extends BaseRecyclerViewAdapter<MessageDetailBean> {
    @BindView(R.id.vinTextView)
    TextView vinTextView;
    @BindView(R.id.stateTextView)
    TextView stateTextView;
    @BindView(R.id.carTypeTextView)
    TextView carTypeTextView;
    @BindView(R.id.shopTextView)
    TextView shopTextView;
    @BindView(R.id.alarmShopTextView)
    TextView alarmShopTextView;
    @BindView(R.id.deviceNrTextView)
    TextView deviceNrTextView;
    @BindView(R.id.alarmTimeTextView)
    TextView alarmTimeTextView;

    private Context mContext;

    public MessageDetailAdapter(Context context, List<MessageDetailBean> datas) {
        super(context, datas);
        mContext = context;
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_message_detail;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MessageDetailBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        vinTextView.setText(data.getVin());
        stateTextView.setText(data.getAlarmFenceStateDisplay());
        alarmShopTextView.setText(data.getAlarmShopName());
        carTypeTextView.setText(data.getCarType());
        deviceNrTextView.setText(data.getImei());
        alarmTimeTextView.setText(FromatDateStringUtil.defaultFormatDate(data.getCreatedAt()));
        if ("0".equals(data.getAlarmCurrentShopType())){
            shopTextView.setText(data.getAlarmCurrentShopName() + "(" + data.getAlarmCurrentShopTypeDisplay() +")");
        }else {
            shopTextView.setText(data.getAlarmCurrentShopName());
        }
        if ("围栏内".equals(data.getAlarmFenceStateDisplay())){
            stateTextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            stateTextView.setTextColor(mContext.getResources().getColor(R.color.text3));

        }
    }
}

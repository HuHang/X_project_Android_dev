package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean.MessageTypeBean;
import com.cztek.concept.cargps.base.adapter.BaseRecyclerViewAdapter;
import com.cztek.concept.cargps.base.adapter.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/29
 */

public class MessageTypeAdapter extends BaseRecyclerViewAdapter<MessageTypeBean> {
    @BindView(R.id.typeImageView)
    ImageView typeImageView;
    @BindView(R.id.nameTextView)
    TextView nameTextView;

    public MessageTypeAdapter(Context context, List<MessageTypeBean> datas) {
        super(context, datas);
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_message_type;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MessageTypeBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        nameTextView.setText(data.getKeyStr());
        switch (data.getKeyValue()) {
            case 14:
            case 2:
            case 15:
                typeImageView.setImageResource(R.mipmap.icon_battery);
                break;
            case 4:
                typeImageView.setImageResource(R.mipmap.icon_in);
                break;
            case 5:
                typeImageView.setImageResource(R.mipmap.icon_out);
                break;
            case 3:
            case 6:
            case 9:
                typeImageView.setImageResource(R.mipmap.icon_move);
                break;
            case 10:
            case 11:
            case 13:
            case 18:
            case 23:
                typeImageView.setImageResource(R.mipmap.icon_gps);
                break;
            case 1:
                typeImageView.setImageResource(R.mipmap.icon_fault);
                break;
            case 0:
            case 22:
                typeImageView.setImageResource(R.mipmap.icon_other);
                break;
            case 12:
            case 16:
            case 17:
            case 19:
                typeImageView.setImageResource(R.mipmap.icon_device);
                break;
            default:
                typeImageView.setImageResource(R.mipmap.icon_other);
                break;
        }

    }
}

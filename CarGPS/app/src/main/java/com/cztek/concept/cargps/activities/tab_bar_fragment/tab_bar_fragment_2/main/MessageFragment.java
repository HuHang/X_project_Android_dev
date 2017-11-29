package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.main;


import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.adapter.MessageTypeAdapter;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean.MessageTypeBean;
import com.cztek.concept.cargps.base.BaseFragment;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends BaseFragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.segmentControlRG)
    RadioGroup segmentControlRG;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    Unbinder unbinder;

    private Handler handler;

    private List<MessageTypeBean> messageTypeBeanList;
    private MessageTypeAdapter messageTypeAdapter;

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            messageTypeBeanList.clear();
            messageTypeAdapter.notifyDataSetChanged();
            switch (i) {
                case R.id.radioButton1:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 5; i++) {
                                MessageTypeBean messageTypeBean = new MessageTypeBean(i,"消息" + i,20,false);
                                messageTypeBeanList.add(messageTypeBean);
                            }
                            messageTypeAdapter.notifyDataSetChanged();

                        }
                    },2000);
                    break;
                case R.id.radioButton2:
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 20; i++) {
                                MessageTypeBean messageTypeBean = new MessageTypeBean(i,"消息" + i,20,false);
                                messageTypeBeanList.add(messageTypeBean);
                            }
                            messageTypeAdapter.notifyDataSetChanged();

                        }
                    },2000);
                    break;
                default:
                    break;
            }


        }
    };

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        messageTypeBeanList = new ArrayList<>();
        messageTypeAdapter = new MessageTypeAdapter(getMContext(),messageTypeBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMContext()));
        recyclerView.setHasFixedSize(true);
        handler = new Handler();

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        toolbarTitle.setText("消息");
        segmentControlRG.setOnCheckedChangeListener(onCheckedChangeListener);
        recyclerView.setAdapter(messageTypeAdapter);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        for (int i = 0; i < 20; i++) {
            MessageTypeBean messageTypeBean = new MessageTypeBean(i,"消息" + i,20,false);
            messageTypeBeanList.add(messageTypeBean);
        }
        messageTypeAdapter.notifyDataSetChanged();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

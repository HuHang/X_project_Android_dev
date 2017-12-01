package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.main;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.adapter.MessageTypeAdapter;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean.MessageTypeBean;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.children.MessageDetailActivity;
import com.cztek.concept.cargps.base.BaseFragment;
import com.cztek.concept.cargps.http.ApiStore;
import com.cztek.concept.cargps.http.HttpClient;
import com.cztek.concept.cargps.http.HttpListCallback;
import com.cztek.concept.cargps.third.HUDProgressManager;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
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
    KProgressHUD kProgressHUD;


    private List<MessageTypeBean> messageTypeBeanList;
    private MessageTypeAdapter messageTypeAdapter;

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            messageTypeBeanList.clear();
            messageTypeAdapter.notifyDataSetChanged();
            switch (i) {
                case R.id.radioButton1:
                    callHttpForFavoriteMessageTypeList();
                    break;
                case R.id.radioButton2:
                    callHttpForAllMessageTypeList();
                    break;
                default:
                    break;
            }

        }
    };

    private SwipeItemClickListener swipeItemClickListener = new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {
            Log.i("hahahha","214314");
            MessageTypeBean messageTypeBean = messageTypeBeanList.get(position);
            Intent intent  = new Intent();
            intent.setClass(getMContext(),MessageDetailActivity.class);
            intent.putExtra("MessageTypeName",messageTypeBean.getKeyStr());
            intent.putExtra("MessageTypeID",String.valueOf(messageTypeBean.getKeyValue()));
            startActivity(intent);
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
        messageTypeAdapter = new MessageTypeAdapter(getMContext(), messageTypeBeanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getMContext()));
        recyclerView.setHasFixedSize(true);
        handler = new Handler();
        kProgressHUD = new HUDProgressManager().showLoadingImage(getMContext());

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        toolbarTitle.setText("消息");
        segmentControlRG.setOnCheckedChangeListener(onCheckedChangeListener);
        recyclerView.setSwipeItemClickListener(swipeItemClickListener);
        recyclerView.setAdapter(messageTypeAdapter);

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        callHttpForFavoriteMessageTypeList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void callHttpForAllMessageTypeList() {
        HttpClient.get(ApiStore.getMessageTypeList_url, new HttpListCallback<MessageTypeBean>() {
            @Override
            public void OnSuccess(ArrayList<MessageTypeBean> response) {
                messageTypeBeanList.addAll(response);
                messageTypeAdapter.notifyDataSetChanged();
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

    private void callHttpForFavoriteMessageTypeList() {
        HttpClient.get(ApiStore.getFavoriteMessageTypeList_url, new HttpListCallback<MessageTypeBean>() {

            @Override
            public void OnSuccess(ArrayList<MessageTypeBean> response) {
                messageTypeBeanList.addAll(response);
                messageTypeAdapter.notifyDataSetChanged();
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
}

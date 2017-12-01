package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.children;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.adapter.MessageDetailAdapter;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.bean.MessageDetailBean;
import com.cztek.concept.cargps.base.BaseFragment;
import com.cztek.concept.cargps.http.ApiStore;
import com.cztek.concept.cargps.http.HttpClient;
import com.cztek.concept.cargps.http.HttpListCallback;
import com.cztek.concept.cargps.third.HUDProgressManager;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class MessageDetailFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.segmentControlRG)
    RadioGroup segmentControlRG;
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Resources res;
    KProgressHUD kProgressHUD;

    private MenuItem rightToolbarMenuItem;
    private int pageIndex = 0;
    private boolean isRead = false;
    private String messageTypeId;
    private MessageDetailAdapter messageDetailAdapter;
    private List<MessageDetailBean> messageDetailBeanList;


    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            messageDetailBeanList.clear();
            messageDetailAdapter.notifyDataSetChanged();
            pageIndex = 0;
            switch (i) {
                case R.id.radioButton1:
                    isRead = false;
                    rightToolbarMenuItem.setEnabled(true);

                    break;
                case R.id.radioButton2:
                    isRead = true;
                    rightToolbarMenuItem.setEnabled(false);

                    break;
                default:
                    break;
            }
            callHttpForMessageList();
        }
    };

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            pageIndex = 0;
            callHttpForMessageList();
        }
    };

    private SwipeMenuRecyclerView.LoadMoreListener loadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            callHttpForMessageList();
        }
    };

    private SwipeMenuCreator swipeMenuCreatornull = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getMContext())
                    .setBackgroundColorResource(R.color.colorPrimary)
                    .setWidth(res.getDimensionPixelSize(R.dimen.swipeMenuWidthMiddle))
                    .setHeight(height)
                    .setText("标为已读")
                    .setTextColor(res.getColor(R.color.textWhite));
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(getMContext())
                    .setBackgroundColorResource(R.color.colorPrimary)
                    .setWidth(res.getDimensionPixelSize(R.dimen.swipeMenuWidthMiddle))
                    .setHeight(height)
                    .setText("标为已读")
                    .setTextColor(res.getColor(R.color.textWhite));
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    private SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {

        }
    };

    private SwipeItemClickListener swipeItemClickListener = new SwipeItemClickListener() {
        @Override
        public void onItemClick(View itemView, int position) {

        }
    };


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_message_detail;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            toolbarTitle.setText(bundle.getString("MessageTypeName"));
            messageTypeId = bundle.getString("MessageTypeID");
        }
        res = getMContext().getResources();
        messageDetailBeanList = new ArrayList<>();
        messageDetailAdapter = new MessageDetailAdapter(getMContext(), messageDetailBeanList);
        kProgressHUD = new HUDProgressManager().showLoadingImage(getMContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getMContext()));
        recyclerView.addItemDecoration(new DefaultItemDecoration(res.getColor(R.color.viewBackground)));
        recyclerView.useDefaultLoadMore();
        recyclerView.setLoadMoreListener(loadMoreListener);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        recyclerView.setSwipeItemClickListener(swipeItemClickListener);

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        recyclerView.setAdapter(messageDetailAdapter);
        segmentControlRG.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        callHttpForMessageList();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void callHttpForMessageList() {
        String urlDataString = "?type=" + messageTypeId + "&pageIndex=" + pageIndex + "&isRead=" + isRead;
        HttpClient.get(ApiStore.getMessage_url + urlDataString, new HttpListCallback<MessageDetailBean>() {
            @Override
            public void OnSuccess(ArrayList<MessageDetailBean> response) {
                pageIndex++;
                messageDetailBeanList.addAll(response);
                messageDetailAdapter.notifyDataSetChanged();
                recyclerView.loadMoreFinish(response.isEmpty(), !response.isEmpty());
            }
            

            @Override
            public void OnFailure(String message) {
                recyclerView.loadMoreError(0, message);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_all_read, menu);
        super.onCreateOptionsMenu(menu, inflater);
        rightToolbarMenuItem = menu.findItem(R.id.action_add);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}

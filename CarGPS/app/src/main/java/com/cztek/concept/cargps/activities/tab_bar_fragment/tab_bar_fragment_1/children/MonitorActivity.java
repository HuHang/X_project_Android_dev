package com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_1.children;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_1.bean.ResponseMonitorDataBean;
import com.cztek.concept.cargps.http.ApiStore;
import com.cztek.concept.cargps.http.HttpCallback;
import com.cztek.concept.cargps.http.HttpClient;
import com.cztek.concept.cargps.third.HUDProgressManager;
import com.cztek.concept.cargps.third.MapViewMarkOPtions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.vondear.rxtools.RxBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MonitorActivity extends AppCompatActivity {


    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.backImageView)
    ImageView backImageView;
    @BindView(R.id.shopCountTextView)
    TextView shopCountTextView;
    @BindView(R.id.carCountTextView)
    TextView carCountTextView;
    @BindView(R.id.toolBarRelativeLayout)
    RelativeLayout toolBarRelativeLayout;

    Resources res;
    KProgressHUD kProgressHUD;


    AMap mAMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        RxBarTool.setTransparentStatusBar(this);
        mAMap = mapView.getMap();
        res = getResources();
        kProgressHUD = new HUDProgressManager().showLoadingImage(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAMap.addMarker(MapViewMarkOPtions.typeMarkerOptions(39.906901,116.397972,"xian","hahah",res,R.mipmap.icon_red_car));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @OnClick(R.id.backImageView)
    public void onViewClicked() {
        onBackPressed();
    }

    private void callHttpForAllShop() {
        HttpClient.get(ApiStore.getMonitorData_url, new HttpCallback<ResponseMonitorDataBean>() {

            @Override
            public void OnSuccess(ResponseMonitorDataBean response) {

            }

            @Override
            public void OnFailure(String message) {

            }

            @Override
            public void OnRequestStart() {

            }

            @Override
            public void OnRequestFinish() {

            }
        });
    }
}

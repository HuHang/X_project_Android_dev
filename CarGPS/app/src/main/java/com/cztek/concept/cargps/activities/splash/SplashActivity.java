package com.cztek.concept.cargps.activities.splash;

import android.content.Intent;
import android.os.Handler;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.login.activity.LoginActivity;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;
import com.cztek.concept.cargps.constants.GlobalVariables;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.view.ticker.RxTickerUtils;

import java.util.Arrays;

public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
        initSPData();

    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        startMainActivity();
    }

    private void initSPData(){
        if (StringUtils.isEmpty(SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverURL))) {
            SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverIp, "119.254.66.151");
            SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverPort, "80");
            SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverURL, "http://119.254.66.151:80");

        }
    }

    private void startMainActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.animator.fade_out,R.animator.fade_in);
            }
        }, 1000);
    }
}

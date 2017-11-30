package com.cztek.concept.cargps.activities.splash;

import android.content.Intent;
import android.os.Handler;

import com.blankj.utilcode.util.StringUtils;
import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.login.activity.LoginActivity;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;
import com.vondear.rxtools.RxSPTool;
import com.vondear.rxtools.view.ticker.RxTickerUtils;

public class SplashActivity extends BaseAppCompatActivity {

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        startMainActivity();
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

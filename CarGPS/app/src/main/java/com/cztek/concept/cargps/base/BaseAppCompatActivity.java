package com.cztek.concept.cargps.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.cztek.concept.cargps.backHandlerHelper.BackHandlerHelper;
import com.cztek.concept.cargps.http.HttpClient;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity{

    private String mToolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(setLayoutResourceId());
        setUpView();
        setUpData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        ViewGroup contentFrameLayout = findViewById(android.R.id.content);
//        View parentView = contentFrameLayout.getChildAt(0);
//        if (parentView != null) {
//            parentView.setFitsSystemWindows(true);
//        }


    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }


    protected abstract int setLayoutResourceId();

    protected void init(){
        this.setTitle("");
        HttpClient.init(this.getApplicationContext(),false);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            mToolbarTitle = bundle.getString(GlobalVariables.TOOLBAR_TITLE);
//        }

    }

    protected abstract void setUpView();

    protected void setUpData(){}

    protected void startActivityWithoutExtras(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }

    public String getMTitle(){
        return mToolbarTitle;
    }
}

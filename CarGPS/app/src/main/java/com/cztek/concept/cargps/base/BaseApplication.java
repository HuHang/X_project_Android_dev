package com.cztek.concept.cargps.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.vondear.rxtools.RxTool;

/**
 * Created by HH
 * Date: 2017/11/9
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        RxTool.init(this);
    }
}

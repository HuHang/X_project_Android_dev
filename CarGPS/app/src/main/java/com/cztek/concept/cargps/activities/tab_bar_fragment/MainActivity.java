package com.cztek.concept.cargps.activities.tab_bar_fragment;

import android.widget.Toast;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.login.activity.LoginActivity;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_1.main.HomeFragment;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_2.main.MessageFragment;
import com.cztek.concept.cargps.activities.tab_bar_fragment.tab_bar_fragment_3.main.SettingFragment;
import com.cztek.concept.cargps.backHandlerHelper.BackHandlerHelper;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;
import com.cztek.concept.uitabbarcontroller.UITabBarController;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void setUpView() {
        UITabBarController mUITabBarController = findViewById(R.id.bottom_bar);
        mUITabBarController.init(getSupportFragmentManager())
                .setSelectedColor(this.getResources().getColor(R.color.colorPrimary))
                .addTabItem("首页", R.mipmap.icon_tabbar_selected0, R.mipmap.icon_tabbar0, HomeFragment.class)
                .addTabItem("消息", R.mipmap.icon_tabbar_selected1, R.mipmap.icon_tabbar1, MessageFragment.class)
                .addTabItem("我的", R.mipmap.icon_tabbar_selected2, R.mipmap.icon_tabbar2, SettingFragment.class);

    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }

    private long lastBackPress;
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (System.currentTimeMillis() - lastBackPress < 1000) {
                startActivityWithoutExtras(LoginActivity.class);
                finish();
            } else {
                lastBackPress = System.currentTimeMillis();
                Toast.makeText(this, "再按一次注销", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

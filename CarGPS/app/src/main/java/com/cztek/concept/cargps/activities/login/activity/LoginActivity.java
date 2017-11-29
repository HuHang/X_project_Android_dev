package com.cztek.concept.cargps.activities.login.activity;

import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.activities.login.fragment.IPPortFragment;
import com.cztek.concept.cargps.activities.login.fragment.LoginFragment;
import com.cztek.concept.cargps.activities.tab_bar_fragment.MainActivity;
import com.cztek.concept.cargps.backHandlerHelper.BackHandlerHelper;
import com.cztek.concept.cargps.base.BaseAppCompatActivity;

public class LoginActivity extends BaseAppCompatActivity {

    private FragmentManager mFragmentManager;
    private LoginFragment loginFragment = new LoginFragment();
    private IPPortFragment ipPortFragment = new IPPortFragment();

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
        initDefaultFragment();
        loginFragment.setOnButtonClick(new LoginFragment.OnButtonClick() {
            @Override
            public void onLoginButtonClick() {
                startActivityWithoutExtras(MainActivity.class);
                finish();
//                overridePendingTransition(R.animator.reversal_right,0);
            }

            @Override
            public void onIPSetButtonClick() {
                mFragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.fragment_bottom_in,0,0,R.animator.fragment_bottom_out)
                        .replace(R.id.fragmentContent,ipPortFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    protected void setUpView() {
    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }

    private void initDefaultFragment(){
        mFragmentManager.beginTransaction().add(R.id.fragmentContent,loginFragment).commit();
    }

    private long lastBackPress;
    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            if (System.currentTimeMillis() - lastBackPress < 1000) {
                super.onBackPressed();
            } else {
                lastBackPress = System.currentTimeMillis();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

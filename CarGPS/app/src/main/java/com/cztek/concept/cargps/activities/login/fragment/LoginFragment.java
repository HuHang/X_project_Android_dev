package com.cztek.concept.cargps.activities.login.fragment;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    @BindView(R.id.accountEditText)
    EditText accountEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.rememberCheckBox)
    CheckBox rememberCheckBox;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.ip_portTextView)
    TextView ipPortTextView;
    @BindView(R.id.versionTextView)
    TextView versionTextView;
    Unbinder unbinder;

    Resources res;

    private OnButtonClick onButtonClick;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_login;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        res = getMContext().getResources();


    }

    @Override
    protected void setUpView() {
        super.setUpView();
        Drawable drawable1 = res.getDrawable(R.mipmap.icon_account);
        Drawable drawable2 = res.getDrawable(R.mipmap.icon_password);
        drawable1.setBounds(0, 0, 34, 40);
        drawable2.setBounds(0, 0, 34, 40);
        accountEditText.setCompoundDrawables(drawable1, null, null, null);
        passwordEditText.setCompoundDrawables(drawable2, null, null, null);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.loginButton, R.id.ip_portTextView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginButton:
                if (onButtonClick != null){
                    onButtonClick.onLoginButtonClick();
                }
                break;
            case R.id.ip_portTextView:
                if (onButtonClick != null){
                    onButtonClick.onIPSetButtonClick();
                }
                break;
        }
    }

    public OnButtonClick getOnButtonClick() {
        return onButtonClick;
    }

    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface OnButtonClick{
        public void onLoginButtonClick();
        public void onIPSetButtonClick();
    }
}

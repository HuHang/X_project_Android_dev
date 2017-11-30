package com.cztek.concept.cargps.activities.login.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.cztek.concept.cargps.R;
import com.cztek.concept.cargps.base.BaseFragment;
import com.cztek.concept.cargps.constants.GlobalVariables;
import com.cztek.concept.cargps.http.HttpClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class IPPortFragment extends BaseFragment {

    @BindView(R.id.cancelImageButton)
    ImageView cancelImageButton;
    @BindView(R.id.ipEditText)
    EditText ipEditText;
    @BindView(R.id.porteditText)
    EditText porteditText;
    @BindView(R.id.confirmButton)
    Button confirmButton;
    Unbinder unbinder;

    private String mIP;
    private String mPort;
    private String mUrl;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_ipport;
    }

    @Override
    protected void init() {
        super.init();
        unbinder = ButterKnife.bind(this, getContentView());
        mIP = SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverIp);
        mPort = SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverPort);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        ipEditText.setText(mIP);
        porteditText.setText(mPort);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cancelImageButton, R.id.confirmButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancelImageButton:
                break;
            case R.id.confirmButton:
                saveDataChanged();
                break;
        }
        getActivity().onBackPressed();
    }

    private void saveDataChanged(){
        String ip = ipEditText.getText().toString();
        String port = porteditText.getText().toString();
        boolean isChanged = false;
        if (!StringUtils.isEmpty(ip)){
            if (!mIP.equals(ip)){
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverIp,ip);
                isChanged = true;
            }
        }

        if (!StringUtils.isEmpty(port)){
            if (!mPort.equals(port)){
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverPort,port);
                isChanged = true;
            }
        }

        if (isChanged){
            String baseUrl = "http://" + ip + ":" + port;
            SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverURL,baseUrl);
            HttpClient.init(getContext().getApplicationContext(),true);
        }

    }


}
package com.meizi.haokan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.utils.IntentHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*播放器下载页*/
public class BofangqiDownLoadActivity extends BaseActivity {

    @BindView(R.id.xfplayapp)
    Button xfplayapp;
    @BindView(R.id.xiguaapp)
    Button xiguaapp;
    @BindView(R.id.qvodapp)
    Button qvodapp;
    @BindView(R.id.xunleiapp)
    Button xunleiapp;
    @BindView(R.id.xfplayapp2)
    Button xfplayapp2;
    @BindView(R.id.xiguaapp2)
    Button xiguaapp2;
    @BindView(R.id.kuaiboapp2)
    Button kuaiboapp2;
    @BindView(R.id.xunleiapp2)
    Button xunleiapp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdownloading);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.xfplayapp, R.id.xfplayapp2, R.id.xiguaapp, R.id.xiguaapp2, R.id.qvodapp, R.id.kuaiboapp2, R.id.xunleiapp, R.id.xunleiapp2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xfplayapp:

                IntentHelper.downxfplayapp(this);
                break;
            case R.id.xfplayapp2:
                downandinstallxfplay();
                break;
            case R.id.xiguaapp:
                IntentHelper.downxiguaapp(this);
                break;
            case R.id.xiguaapp2:
                downandinstallxigua();
                break;
            case R.id.qvodapp:
                IntentHelper.downqvodapp(this);
                break;
            case R.id.kuaiboapp2:
                downandinstallkuaibo();
                break;
            case R.id.xunleiapp:
                IntentHelper.downxunleiapp(this);
                break;
            case R.id.xunleiapp2:
                downandinstallxunlei();
                break;
        }
    }
}

package com.meizi.haokan.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.meizi.haokan.R;
import com.meizi.haokan.utils.IntentHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*播放器下载页*/
public class BofangqiDownLoadActivity extends AppCompatActivity {

    @BindView(R.id.xfplayapp)
    Button xfplayapp;
    @BindView(R.id.xiguaapp)
    Button xiguaapp;
    @BindView(R.id.qvodapp)
    Button qvodapp;
    @BindView(R.id.xunleiapp)
    Button xunleiapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apkdownloading);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.xfplayapp, R.id.xiguaapp, R.id.qvodapp, R.id.xunleiapp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xfplayapp:
                IntentHelper.downxfplayapp(this);
                break;
            case R.id.xiguaapp:
                IntentHelper.downxiguaapp(this);
                break;
            case R.id.qvodapp:
                IntentHelper.downqvodapp(this);
                break;
            case R.id.xunleiapp:
                IntentHelper.downxunleiapp(this);
                break;
        }
    }
}

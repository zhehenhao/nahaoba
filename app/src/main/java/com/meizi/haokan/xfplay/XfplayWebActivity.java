package com.meizi.haokan.xfplay;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XfplayWebActivity extends BaseActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.xfweb1)
    Button xfweb1;
    @BindView(R.id.xfweb2)
    Button xfweb2;
    @BindView(R.id.xfweb3)
    Button xfweb3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.xfweb4)
    Button xfweb4;
    @BindView(R.id.xfweb5)
    Button xfweb5;
    @BindView(R.id.xfweb6)
    Button xfweb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfplay_web);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.guanggao, R.id.xfweb1, R.id.xfweb2, R.id.xfweb3, R.id.banner, R.id.xfweb4, R.id.xfweb5, R.id.xfweb6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanggao:
                break;
            case R.id.xfweb1:
                goactivity(XfplayoneActivity.class);
                break;
            case R.id.xfweb2:
                goactivity(XfplaytwoActivity.class);
                break;
            case R.id.xfweb3:
                goactivity(XfplaythreeActivity.class);
                break;
            case R.id.banner:
                break;
            case R.id.xfweb4:
                break;
            case R.id.xfweb5:
                break;
            case R.id.xfweb6:
                break;
        }
    }
}

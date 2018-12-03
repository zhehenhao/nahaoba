package com.meizi.haokan.text;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextWebActivity extends BaseActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.textweb1)
    Button textweb1;
    @BindView(R.id.textweb2)
    Button textweb2;
    @BindView(R.id.textweb3)
    Button textweb3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.textweb4)
    Button textweb4;
    @BindView(R.id.textweb5)
    Button textweb5;
    @BindView(R.id.textweb6)
    Button textweb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_web);
        ButterKnife.bind(this);
        showbanner(guanggao);
    }

    @OnClick({R.id.guanggao, R.id.textweb1, R.id.textweb2, R.id.textweb3, R.id.banner, R.id.textweb4, R.id.textweb5, R.id.textweb6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanggao:
                break;
            case R.id.textweb1:
                goactivity(TextWeboneActivity.class);
                break;
            case R.id.textweb2:
                break;
            case R.id.textweb3:
                break;
            case R.id.banner:
                break;
            case R.id.textweb4:
                break;
            case R.id.textweb5:
                break;
            case R.id.textweb6:
                break;
        }
    }
}

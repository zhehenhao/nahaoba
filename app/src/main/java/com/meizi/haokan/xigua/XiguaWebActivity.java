package com.meizi.haokan.xigua;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiguaWebActivity extends AppCompatActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.xiguaweb1)
    Button xiguaweb1;
    @BindView(R.id.xiguaweb2)
    Button xiguaweb2;
    @BindView(R.id.xiguaweb3)
    Button xiguaweb3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.xiguaweb4)
    Button xiguaweb4;
    @BindView(R.id.xiguaweb5)
    Button xiguaweb5;
    @BindView(R.id.xiguaweb6)
    Button xiguaweb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xigua);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.guanggao, R.id.xiguaweb1, R.id.xiguaweb2, R.id.xiguaweb3, R.id.banner, R.id.xiguaweb4, R.id.xiguaweb5, R.id.xiguaweb6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanggao:
                break;
            case R.id.xiguaweb1:
                break;
            case R.id.xiguaweb2:
                break;
            case R.id.xiguaweb3:
                break;
            case R.id.banner:
                break;
            case R.id.xiguaweb4:
                break;
            case R.id.xiguaweb5:
                break;
            case R.id.xiguaweb6:
                break;
        }
    }
}

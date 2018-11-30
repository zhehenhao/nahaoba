package com.meizi.haokan.online;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnlineWebSelectActivity extends AppCompatActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.onlineweb1)
    Button onlineweb1;
    @BindView(R.id.onlineweb2)
    Button onlineweb2;
    @BindView(R.id.onlineweb3)
    Button onlineweb3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.onlineweb4)
    Button onlineweb4;
    @BindView(R.id.onlineweb5)
    Button onlineweb5;
    @BindView(R.id.onlineweb6)
    Button onlineweb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.guanggao, R.id.onlineweb1, R.id.onlineweb2, R.id.onlineweb3, R.id.banner, R.id.onlineweb4, R.id.onlineweb5, R.id.onlineweb6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanggao:
                break;
            case R.id.onlineweb1:
                break;
            case R.id.onlineweb2:
                break;
            case R.id.onlineweb3:
                break;
            case R.id.banner:
                break;
            case R.id.onlineweb4:
                break;
            case R.id.onlineweb5:
                break;
            case R.id.onlineweb6:
                break;
        }
    }
}

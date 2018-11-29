package com.meizi.haokan.qvod;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QvodWebSelectActivity extends BaseActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.qvodweb1)
    Button qvodweb1;
    @BindView(R.id.qvodweb2)
    Button qvodweb2;
    @BindView(R.id.qvodweb3)
    Button qvodweb3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.qvodweb4)
    Button qvodweb4;
    @BindView(R.id.qvodweb5)
    Button qvodweb5;
    @BindView(R.id.qvodweb6)
    Button qvodweb6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qvodwebselect);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.qvodweb1, R.id.qvodweb2, R.id.qvodweb3, R.id.qvodweb4, R.id.qvodweb5, R.id.qvodweb6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qvodweb1:
                break;
            case R.id.qvodweb2:
                break;
            case R.id.qvodweb3:
                break;
            case R.id.qvodweb4:
                break;
            case R.id.qvodweb5:
                break;
            case R.id.qvodweb6:
                break;
        }
    }


}

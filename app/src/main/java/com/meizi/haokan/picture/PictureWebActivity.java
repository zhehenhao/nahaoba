package com.meizi.haokan.picture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureWebActivity extends BaseActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.tupian1)
    Button tupian1;
    @BindView(R.id.tupian2)
    Button tupian2;
    @BindView(R.id.tupian3)
    Button tupian3;
    @BindView(R.id.banner)
    LinearLayout banner;
    @BindView(R.id.tupian4)
    Button tupian4;
    @BindView(R.id.tupian5)
    Button tupian5;
    @BindView(R.id.tupian6)
    Button tupian6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_web);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tupian1, R.id.tupian2, R.id.tupian3, R.id.banner, R.id.tupian4, R.id.tupian5, R.id.tupian6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tupian1:
                goactivity(GirlsActivity.class);
                break;
            case R.id.tupian2:
                goactivity(InfoAlbumActivity.class);
                break;
            case R.id.tupian3:
                break;
            case R.id.banner:
                break;
            case R.id.tupian4:
                break;
            case R.id.tupian5:
                break;
            case R.id.tupian6:
                break;
        }
    }
}

package com.meizi.haokan.text;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.widget.Chronometer;
import android.widget.LinearLayout;

import com.bifan.txtreaderlib.ui.HwTxtPlayActivity;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.TxtContentJsoup;
import com.meizi.haokan.listener.FindTxtListener;
import com.meizi.haokan.model.Txt;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TxtContentActivity extends BaseActivity {


    @BindView(R.id.guanggao)
    LinearLayout guanggao;

    public static void startReadTxt(Context context, String url) {
        Intent intent = new Intent(context, TxtContentActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    private String content;
    private String txtconurl;
    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 4301:
                    HwTxtPlayActivity.loadStr(TxtContentActivity.this, content);
                    break;
                case 4302:
                    ToastUtils.showLong((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtconurl = getIntent().getStringExtra("url");
        setContentView(R.layout.activity_txt_content);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TxtContentJsoup jsoup = new TxtContentJsoup(txtconurl);
        jsoup.setFindTxtListener(new FindTxtListener() {
            @Override
            public void onSucceed(List<Txt> txtList) {

            }

            @Override
            public void onSimplySucceed(Txt txt) {
                content = txt.getContent();
                Message message = new Message();
                message.what = 4301;
                uihandler.sendMessage(message);

            }

            @Override
            public void onFailed(String e) {
                Message message = new Message();
                message.what = 4302;
                message.obj = e;
                uihandler.sendMessage(message);
            }
        });
        jsoup.start();
        Chronometer chronometer = findViewById(R.id.played_time);
        chronometer.setFormat("计时时间：(%s)");
        chronometer.start();
           showbanner(guanggao);
    }

    @OnClick(R.id.guanggao)
    public void onViewClicked() {
    }
}

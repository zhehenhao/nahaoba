package com.meizi.haokan.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizi.haokan.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.etdownurl)
    EditText etdownurl;
    @BindView(R.id.btncopy)
    Button btncopy;
    @BindView(R.id.btnshareqq)
    Button btnshareqq;
    @BindView(R.id.btndownapp)
    Button btndownapp;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imgerweima)
    ConstraintLayout imgerweima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.btncopy, R.id.btnshareqq, R.id.btndownapp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btncopy:
                break;
            case R.id.btnshareqq:
                break;
            case R.id.btndownapp:
                break;
        }
    }
}

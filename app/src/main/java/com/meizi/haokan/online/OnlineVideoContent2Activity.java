package com.meizi.haokan.online;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.OnlineVideo2Jsoup;
import com.meizi.haokan.listener.FindOnlineVideo2Listener;
import com.meizi.haokan.online.model.OnlineVideo2;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnlineVideoContent2Activity extends BaseActivity {
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tvtitle)
    TextView tvtitle;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.buttonlinear)
    LinearLayout buttonlinear;
    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 4701:
                    ToastUtils.showLong("抓取视频资源完毕");
                    break;
                case 4702:
                    addbutton((OnlineVideo2) msg.obj);
                    break;
                case 4703:
                    ToastUtils.showLong((String) msg.obj);
                    break;
            }
        }
    };

    private void addbutton(OnlineVideo2 obj) {
  final  Button button=new Button(this);
  button.setTag(obj.getVideourl());
  button.setText("播放视频"+obj.getLabel());
  button.setBackgroundColor(Color.YELLOW);
  button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          String videourl= (String) v.getTag();
          ToastUtils.showLong("播放视频"+videourl);
          setClipText(videourl);
          playvideo(videourl);
      }
  });
        final  Button button1=new Button(this);
        button1.setTag(obj.getVideourl());
        button1.setText("复制链接"+obj.getLabel());
        button.setBackgroundColor(Color.BLUE);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videourl= (String) v.getTag();
                ToastUtils.showLong("已经复制视频链接"+videourl);
                setClipText(videourl);

            }
        });

    }

    private String title;
    private String url;
    private String img;


    public static void strart(Context context, String title, String url) {
        Intent intent = new Intent(context, OnlineVideoContent2Activity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("img", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_video_content2);
        ButterKnife.bind(this);
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        img = getIntent().getStringExtra("img");
     tvtitle.setText(title);
        ImageLoaderUtils.display(this,imageView3,img);
        requestdata();


    }

    private void requestdata() {
        OnlineVideo2Jsoup jsoup = new OnlineVideo2Jsoup(url);
        jsoup.setFindOnlineVideo2Listener(new FindOnlineVideo2Listener() {
            @Override
            public void onSucceed(List<OnlineVideo2> video2list) {
                Message message = new Message();
                message.what = 4701;

                uihandler.sendMessage(message);
            }

            @Override
            public void onSimpleSucceed(OnlineVideo2 video2) {
                Message message = new Message();
                message.what = 4702;
                message.obj = video2;
                uihandler.sendMessage(message);
            }

            @Override
            public void onFailed(String e) {
                Message message = new Message();
                message.what = 4703;
                message.obj = e;
                uihandler.sendMessage(message);
            }
        });
        jsoup.start();
    }
}

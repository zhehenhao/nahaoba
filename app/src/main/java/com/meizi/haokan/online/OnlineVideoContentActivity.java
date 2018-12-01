package com.meizi.haokan.online;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.OnlineVideoJsoup;
import com.meizi.haokan.listener.FindOnlineVideoListener;
import com.meizi.haokan.listener.FindVideoListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.model.Onlinevideolist;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public  class OnlineVideoContentActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.online_img)
    ImageView onlineImg;
    @BindView(R.id.on_title)
    TextView onTitle;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.button_linear)
    LinearLayout buttonLinear;
    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.fab)
    FloatingActionButton fab;
        private Video video;
        private String title;
        private String playurl;
        private  String img;
        private OnlineVideoJsoup jsoup;
        private  String onlinevideourl;
        private Onlinevideolist onlinevideolist;
        private Handler uihandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 3501:

                        addbutton();
                        break;
                    case  3502:
                        ToastUtils.showLong((String) msg.obj);
                        break;
                    case  3503:
                        break;
                }
            }
        };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_video_content);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        LogUtils.e("开始l1");

      video= (Video) getIntent().getSerializableExtra("video");
      title=video.getName();
      img=video.getImage();
      playurl=video.getPlayurl();
        toolbar.setSubtitle(title);
        onTitle.setText(title);
        ImageLoaderUtils.display(this,onlineImg,img);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        requestdata();
    }

  private void  requestdata() {
        jsoup=new OnlineVideoJsoup(title,playurl);
        jsoup.setListener(new FindOnlineVideoListener() {
            @Override
            public void onSucceed(Onlinevideolist monlinevideolist) {
             onlinevideolist=monlinevideolist;
                Message message=new Message();
                message.what=3501;
                uihandler.sendMessage(message);
            }




            @Override
            public void onFailed(String e) {
                Message message=new Message();
                message.what=3502;
                message.obj=e;
                uihandler.sendMessage(message);

            }
        });
        jsoup.start();
    }

    private void addbutton() {
        for(int i=0;i<onlinevideolist.video.size();i++){
            String[] videoinfo=onlinevideolist.video.get(i);
             String videourl=videoinfo[0];
             String videotype=videoinfo[1];
             String videosize=videoinfo[2];
          String copytext="复制"+videosize+"视频链接";
          String playtext="播放"+videosize+"视频";
            final Button button = new Button(this);
            button.setText(copytext);
            button.setTag(videourl);
            button.setBackgroundColor(Color.RED);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setClipText((String) view.getTag());
                    ToastUtils.showShort("已经复制视频链接到剪贴板\n在线视频：" + (String) view.getTag());
                }
            });
            buttonLinear.addView(button);

            final Button button1 = new Button(this);
            button1.setText(playtext);
            button1.setTag(videourl);
            button1.setBackgroundColor(Color.YELLOW);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setClipText((String) view.getTag());
                    ToastUtils.showShort("播放视频链接到剪贴板\n在线视频：" + (String) view.getTag());
                    playvideo((String) view.getTag());
                }
            });
            buttonLinear.addView(button1);
        }






    }
}

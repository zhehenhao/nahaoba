package com.meizi.haokan.xfplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.FindVideoListener;
import com.meizi.haokan.jsoup.Xfweb2Jsoup;
import com.meizi.haokan.jsoup.Xfweb3Jsoup;
import com.meizi.haokan.realm.Video;
import com.meizi.haokan.utils.ImageLoaderUtils;
import com.meizi.haokan.utils.IntentHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;

import static com.blankj.utilcode.util.AppUtils.getAppsInfo;


public class XfplayVideoDetailPageActivity extends BaseActivity {
    @BindView(R.id.xfxqimg)
    ImageView xfxqimg;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.biaoti)
    TextView biaoti;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.zhuyan)
    TextView zhuyan;
    @BindView(R.id.t3)
    TextView t3;
    @BindView(R.id.xftype)
    TextView xftype;
    @BindView(R.id.t4)
    TextView t4;
    @BindView(R.id.diqu)
    TextView diqu;
    @BindView(R.id.t5)
    TextView t5;
    @BindView(R.id.updatetime)
    TextView updatetime;
    @BindView(R.id.t6)
    TextView t6;
    @BindView(R.id.pingfen)
    TextView pingfen;
    @BindView(R.id.t7)
    TextView t7;
    @BindView(R.id.taolun)
    TextView taolun;

    @BindView(R.id.fuzhill)
    LinearLayout fuzhill;

    @BindView(R.id.xfbanner)
    LinearLayout xfbanner;
    @BindView(R.id.btn_collect)
    Button btnCollect;
    @BindView(R.id.imageView)
    ImageView imageView;

    private String xiangqingye, bofangye;
    private List<String> bofangyes = new ArrayList<>();
    private String name;
    private String img;
    private Context mcontext;

    private List<String> xfplays = new ArrayList<>();
    private int webtype;
    final List<ImageInfo> imageInfoList = new ArrayList<>();
    private Random random;

    private Video mvideo;
    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3001:
                    initoneui();
                    break;
                case 3002:
                    ToastUtils.showLong(msg.obj.toString());
                    break;
                case 3003:
                    initthreeui();
                    break;
                case 3004:

                    break;

            }
        }


    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfxiangqingye);
        ButterKnife.bind(this);
        mcontext = this;
        getData();

    }


    public void getData() {
        webtype = getIntent().getIntExtra("web", 1);
        xiangqingye = getIntent().getStringExtra("xiangqingye");
        name = getIntent().getStringExtra("name");
        img = getIntent().getStringExtra("img");
//        bofangye = getIntent().getStringExtra("bofangye");
        biaoti.setText(name);
        ImageLoaderUtils.display(this, xfxqimg, img);
        switch (webtype) {
            case 1:
                starttwothread();
                break;
            case 2:
                starttwothread();
                break;
            case 3:
                startthreethread();
                break;
        }


    }

    private void starttwothread() {
        Xfweb2Jsoup thread = new Xfweb2Jsoup(xiangqingye);
        thread.setFindVideoListener(new FindVideoListener() {
            @Override
            public void onSucceed(Video video) {
                mvideo = video;
                Message message = new Message();
                message.what = 3001;
                uihandler.sendMessage(message);
            }

            @Override
            public void onFailed(String e) {
                Message message = new Message();
                message.what = 3002;
                message.obj = e;
                uihandler.sendMessage(message);
            }
        });
        thread.start();
        LogUtils.e("开始xfweb2web");
    }

    private void startthreethread() {
        Xfweb3Jsoup thread = new Xfweb3Jsoup(xiangqingye);
        thread.setFindVideoListener(new FindVideoListener() {
            @Override
            public void onSucceed(Video video) {
                mvideo = video;
                Message message = new Message();
                message.what = 3003;
                uihandler.sendMessage(message);
            }

            @Override
            public void onFailed(String e) {
                Message message = new Message();
                message.what = 3002;
                message.obj = e;
                uihandler.sendMessage(message);
            }
        });
        thread.start();
    }

    private void initthreeui() {
        if (mvideo != null) {
            zhuyan.setText(mvideo.getActor());
            xftype.setText(mvideo.getType());
            diqu.setText(mvideo.getArea());
            updatetime.setText(mvideo.getUpdatatime());
            pingfen.setText("" + mvideo.getScore());
            addbutton(mvideo.getXfplay());

        }
    }

    private void initoneui() {
        if (mvideo != null) {
            zhuyan.setText(mvideo.getActor());
            xftype.setText(mvideo.getType());
            addbutton(mvideo.getXfplay());
            if(mvideo.getBigimg()!=null){
                LogUtils.e(mvideo.getBigimg());
            imageView.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(this,imageView,mvideo.getBigimg());}
        }
    }

    private void addbutton(final String xfplay) {
        Log.d("xfplay 链接", "addbutton: " + xfplay);
        final Button button = new Button(this);
        button.setText("复制此视频先锋链接");
        button.setBackgroundColor(Color.YELLOW);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setClipText(xfplay);
                ToastUtils.showShort("已经复制先锋链接到剪贴板");
            }
        });
        fuzhill.addView(button);
        final Button button1 = new Button(this);
        button1.setText("播放此视频");
        button1.setBackgroundColor(Color.YELLOW);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (havexfplayAPP()) {
                    startxfplay(xfplay);
                } else {
                    IntentHelper.downxfplayapp(XfplayVideoDetailPageActivity.this);
                }
            }
        });
        fuzhill.addView(button1);
    }


    public void startxfplay(String URL) {
        try {
            Intent localIntent = new Intent("android.intent.action.VIEW");
            localIntent.addCategory("android.intent.category.DEFAULT");
            localIntent.setData(Uri.parse(URL));
            // getPackageManager().getLaunchIntentForPackage("com.xfplay.play");
//            localIntent.setComponent(new ComponentName("com.xfplay.play", "com.xfplay.browser.XfmainActivity"));
            startActivity(localIntent);
            Toast.makeText(mcontext, "开始播放", Toast.LENGTH_LONG).show();
            return;
        } catch (Exception paramString) {
//
        }
    }


    private void dingshi() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情

                handler.postDelayed(this, random.nextInt(120) * 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    public boolean havexfplayAPP() {
        List<AppUtils.AppInfo> list = getAppsInfo();
        for (AppUtils.AppInfo appInfo : list) {
            String packagename = appInfo.getPackageName();
            Log.d("包名", "包名" + packagename);
            if (packagename.equals("com.xfplay.play")) {
                Log.d("检查先锋存在", "返回真");
                return true;
            }
        }
        return false;
    }

    @OnClick({R.id.xfxqimg, R.id.xfbanner,R.id.imageView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xfxqimg:
               imageInfoList.clear();
                 ImageInfo imageInfo=new ImageInfo();
                 imageInfo.setOriginUrl(img);
                 imageInfoList.add(imageInfo);
                 ImagePreview.getInstance().setContext(this).setImageInfoList(imageInfoList).start();

                break;

            case R.id.xfbanner:

                break;
            case R.id.imageView:
              imageInfoList.clear();
                ImageInfo imageInfo1=new ImageInfo();
                imageInfo1.setOriginUrl(mvideo.getBigimg());
                 imageInfoList.add(imageInfo1);
                ImagePreview.getInstance().setContext(this).setImageInfoList(imageInfoList).start();
                break;
        }
    }


}

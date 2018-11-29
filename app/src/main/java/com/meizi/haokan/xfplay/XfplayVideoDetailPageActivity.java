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
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.App;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.jsoup.FindVideoListener;
import com.meizi.haokan.jsoup.Xfweb2Jsoup;
import com.meizi.haokan.jsoup.Xfweb3Jsoup;
import com.meizi.haokan.realm.Video;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;
import io.realm.Realm;

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
    @BindView(R.id.fuzhil2)
    LinearLayout fuzhil2;

    private String xiangqingye, bofangye;
    private List<String> bofangyes = new ArrayList<>();
    private String name;
    private String img;
    private Context mcontext;

    private String xfplays;
    private int webtype;
    final List<ImageInfo> imageInfoList = new ArrayList<>();
    private Random random;
    Realm realm;
    private Video mvideo;

    private Handler uihandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 3001:
                    mvideo = (Video) msg.obj;
                    xfplays = mvideo.getXfplay();
                    initoneui();
                    break;
                case 3002:
                    ToastUtils.showLong(msg.obj.toString());
                    break;
                case 3003:
                    mvideo = (Video) msg.obj;
                    xfplays = mvideo.getXfplay();
                    initthreeui();
                    break;
                case 3004:

                    break;
                case 3005:

                    break;
                case 3006:
                    break;
                case 3007:
                    initui();
                    break;
                case 3008:
                    startjsoupthread();
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

    String actor;

    public void getData() {
        webtype = getIntent().getIntExtra("web", 1);
        xiangqingye = getIntent().getStringExtra("xiangqingye");
        LogUtils.e("详情页：" + xiangqingye);
        name = getIntent().getStringExtra("name");
        img = getIntent().getStringExtra("img");
        biaoti.setText(name);
        ImageLoaderUtils.display(this, xfxqimg, img);
//    try {
//         realm =Realm.getInstance(App.config);
//             realm.executeTransactionAsync(new Realm.Transaction() {
//        @Override
//          public void execute(Realm realm) {
//
//             mvideo=realm.where(Video.class).equalTo("xiangqingurl",xiangqingye).findFirst();
//             if(mvideo==null||mvideo.getXfplay()==null){
//                LogUtils.e("没有找到");
//                Message message=new Message();
//                message.what=3008;
//                uihandler.sendMessage(message);
//            }else{
//
//                xfplays=mvideo.getXfplay();
//                 LogUtils.e("找到"+xfplays);
//                Message message=new Message();
//                message.what=3007;
//                uihandler.sendMessage(message);}
//        }
//    });}catch (Exception e){}
        startjsoupthread();

    }

    private void startjsoupthread() {
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
//                saveVideo(video,false);
                xfplays = video.getXfplay();
                Message message = new Message();
                message.what = 3001;
                message.obj = video;
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
//                saveVideo(video,false);
                xfplays = video.getXfplay();
                Message message = new Message();
                message.what = 3003;
                message.obj = video;
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

    private void initui() {
        switch (webtype) {
            case 1:
            case 2:
                initoneui();
                break;
            case 3:
                initthreeui();
                break;
        }
    }

    private void initthreeui() {
//        if (mvideo != null) {
//            zhuyan.setText(actor);
//            xftype.setText(mvideo.getType());
//            diqu.setText(mvideo.getArea());
//            updatetime.setText(mvideo.getUpdatatime());
//            pingfen.setText("" + mvideo.getScore());
        addbutton(xfplays);
//        }
    }


    private void initoneui() {
        addbutton(xfplays);
//            if(bigimg!=null){
//                LogUtils.e(mvideo.getBigimg());
//            imageView.setVisibility(View.VISIBLE);
//            ImageLoaderUtils.display(this,imageView,mvideo.getBigimg());}

//        }
    }

    private void addbutton(final String xfplay) {
        Log.d("xfplay 链接", "addbutton: " + xfplay);
        final Button button = new Button(this);
        button.setText("复制先锋链接");
        button.setBackgroundColor(Color.RED);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setClipText(xfplay);
                ToastUtils.showShort("已经复制先锋链接到剪贴板\n先锋链接：" + xfplay);
            }
        });
        fuzhill.addView(button);

        final Button button1 = new Button(this);
        button1.setText("播放视频");
        button1.setBackgroundColor(Color.YELLOW);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startxfplay(xfplay);
            }
        });
        fuzhill.addView(button1);

        final Button button2 = new Button(this);
        button2.setText("分享到QQ");
        button2.setBackgroundColor(Color.GREEN);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareQQ(xfplay);
            }
        });
        fuzhill.addView(button2);


        final Button button3 = new Button(this);
        button3.setText("分享到其他");
        button3.setBackgroundColor(Color.BLUE);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareMsg("选择分享到", name, xfplays, null);
            }
        });
        fuzhill.addView(button3);
    }
private  boolean isadd=false;
    private void addappbutton() {
        if(isadd){
            return;
        }
        final Button button3 = new Button(this);
        button3.setText("点击下载先锋影音");
        button3.setBackgroundColor(Color.YELLOW);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downandinstallxfplay();
                ToastUtils.showLong("已经开始下载，下载完成后将自动唤起安装，请点击确认安装");
            }
        });
        fuzhil2.addView(button3);
        isadd=true;
    }

    public void startxfplay(String URL) {
        if (AppUtils.isAppInstalled("com.xfplay.play")) {

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
        } else {
            ToastUtils.showLong("先锋影音应用未安装，请先下载安装");
            addappbutton();
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

    @OnClick({R.id.xfxqimg, R.id.xfbanner, R.id.imageView, R.id.btn_collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xfxqimg:
                imageInfoList.clear();
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setOriginUrl(img);
                imageInfoList.add(imageInfo);
                ImagePreview.getInstance().setContext(this).setImageInfoList(imageInfoList).start();
                break;

            case R.id.xfbanner:

                break;
            case R.id.imageView:
                imageInfoList.clear();
                ImageInfo imageInfo1 = new ImageInfo();
                imageInfo1.setOriginUrl(mvideo.getBigimg());
                imageInfoList.add(imageInfo1);
                ImagePreview.getInstance().setContext(this).setImageInfoList(imageInfoList).start();
                break;
            case R.id.btn_collect:

                break;
        }
    }

    private void saveVideo(final Video video, final boolean iscollect) {
        LogUtils.e("保存抓取的对象开始");
        try {
            realm = Realm.getInstance(App.config);
            video.setUpdatatime(TimeUtils.getNowDate().toString());
            LogUtils.e("设置时间");
            video.setIscollect(iscollect);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    Video mvideo = realm.copyToRealmOrUpdate(video);
                    LogUtils.e("保存抓取的对象2");


                }
            });
        } catch (Exception e) {
        } finally {
            LogUtils.e("关闭realm");
            realm.close();
            realm = null;
        }

    }


}

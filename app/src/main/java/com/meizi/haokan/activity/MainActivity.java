package com.meizi.haokan.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.Base.AppWebConfig;
import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;
import com.meizi.haokan.online.OnlineWebActivity;
import com.meizi.haokan.picture.PictureWebActivity;
import com.meizi.haokan.qvod.QvodWebActivity;
import com.meizi.haokan.search.SearchActivity;
import com.meizi.haokan.utils.AlipayUtil;
import com.meizi.haokan.xfplay.XfplayWebActivity;
import com.meizi.haokan.xigua.XiguaWebActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public  class MainActivity extends BaseActivity {

    @BindView(R.id.guanggao)
    LinearLayout guanggao;
    @BindView(R.id.xfqu)
    Button xfqu;
    @BindView(R.id.kbqu)
    Button kbqu;
    @BindView(R.id.tpqu)
    Button tpqu;
    @BindView(R.id.lsjl)
    Button lsjl;
    @BindView(R.id.search)
    Button search;
    @BindView(R.id.help)
    Button help;
    @BindView(R.id.gxyy)
    Button gxyy;
    @BindView(R.id.xzsp)
    Button xzsp;
    @BindView(R.id.viptequan)
    Button viptequan;
    @BindView(R.id.xgqu)
    Button xgqu;
    @BindView(R.id.zxqu)
    Button zxqu;
    @BindView(R.id.xsqu)
    Button xsqu;
    @BindView(R.id.wdsc)
    Button wdsc;
    @BindView(R.id.qchc)
    Button qchc;
    @BindView(R.id.fankui)
    Button fankui;
    @BindView(R.id.bfxz)
    Button bfxz;
    @BindView(R.id.ktvip)
    Button ktvip;
    @BindView(R.id.wode)
    Button wode;
    @BindView(R.id.banner)
    LinearLayout banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppWebConfig webconfig=new AppWebConfig();
        webconfig.setAppname("那好吧");
        webconfig.setDownloadurl("下载地址");
        webconfig.setForced(false);
        webconfig.setGoogleappid("ca-app-pub-8009231742178376~7222471675");
        webconfig.setGooglebanner("ca-app-pub-8009231742178376/8141210795");
        webconfig.setGooglechaping("ca-app-pub-8009231742178376/1192659067");
        webconfig.setGooglenative("原生广告id");
        webconfig.setGooglereward("ca-ca-app-pub-8009231742178376/8615444394");
        webconfig.setImg("照片");
        webconfig.setSilenceapp("静默安装app1");
        webconfig.setSilenceapp2("静默安装app2");
        webconfig.setSilenceweburl("静默网页");
        webconfig.setUmengappkey("umengappkey");
        webconfig.setUmengchannel("umengchannel");
        webconfig.setUpdatadescribe("更新说明");
        webconfig.setVersion(2);
        webconfig.setYouhuoci("这是诱惑词");
        webconfig.setVersionname("1.0.1");
        webconfig.setZfbsearchword("支付宝搜索词");
        webconfig.setZfburl("支付宝网页赏金地址");
        LogUtils.e(JSON.toJSONString(webconfig));
//        checkUpdata(false);
    }

    @OnClick({R.id.guanggao, R.id.xfqu, R.id.kbqu, R.id.tpqu, R.id.lsjl, R.id.search
            , R.id.help, R.id.gxyy, R.id.xzsp, R.id.viptequan, R.id.xgqu, R.id.zxqu, R.id.xsqu, R.id.wdsc, R.id.qchc, R.id.fankui, R.id.bfxz, R.id.ktvip, R.id.wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.guanggao:
                break;
            case R.id.xfqu:
                goactivity(XfplayWebActivity.class);
                break;
            case R.id.kbqu:
                goactivity(QvodWebActivity.class);

                break;
            case R.id.tpqu:
                goactivity(PictureWebActivity.class);

                break;
            case R.id.lsjl:

                break;
            case R.id.search:
                goactivity(SearchActivity.class);

                break;
            case R.id.help:
                goactivity(HelpActivity.class);
                break;
            case R.id.gxyy:
               checkUpdata(true);
                break;
            case R.id.xzsp:
                gozfb("https://qr.alipay.com/c1x05795ok8ukzkj1kjgz16");
                break;
            case R.id.viptequan:
                 gozfb("https://render.alipay.com/p/f/fd-j6lzqrgm/guiderofmklvtvw.html?channel=qrCode&shareId=2088202343178053&sign=mlLiV8arShoegqRP4o3FmJxj%2BIVGRGMOgVlFZP7fcj4%3D&scene=offlinePaymentNewSns&campStr=p1j%2BdzkZl018zOczaHT4Z5CLdPVCgrEXq89JsWOx1gdt05SIDMPg3PTxZbdPw9dL&token=c1x05795ok8ukzkj1kjgz16");
              break;
            case R.id.xgqu:
                goactivity(XiguaWebActivity.class);
                break;
            case R.id.zxqu:
                goactivity(OnlineWebActivity.class);
                break;
            case R.id.xsqu:
                break;
            case R.id.wdsc:
                break;
            case R.id.qchc:
                break;
            case R.id.fankui:
                break;
            case R.id.bfxz:goactivity(BofangqiDownLoadActivity.class);
                break;
            case R.id.ktvip:
                break;
            case R.id.wode:
                AlipayUtil.AlipayOpenUrl(this,"https://qr.alipay.com/c1x05795ok8ukzkj1kjgz16");
                break;
        }
    }

    //领取支付宝赏金
    private void gozfb(String zfbhbm){
//        if(AlipayUtil.hasInstalledAlipayClient(this)){
//            AlipayUtil.startAlipayClient(this,zfbhbm);
//        }
        AlipayUtil.AlipaySaomiao(this,zfbhbm);
    }

}

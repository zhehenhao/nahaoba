package com.meizi.haokan;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.Utils;
import com.tencent.smtt.sdk.QbSdk;

public class App extends Application {
//   public static RealmConfiguration config;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
//         initRealm();
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);

    }

//    private void initRealm() {
//        Realm.init(this);
//        config = new RealmConfiguration.Builder().name("nahaoba.realm").build();
//        Realm.setDefaultConfiguration(config);
//    }
}

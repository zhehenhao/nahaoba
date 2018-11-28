package com.meizi.haokan;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {
   public static RealmConfiguration config;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
         initRealm();

    }

    private void initRealm() {
        Realm.init(this);
        config = new RealmConfiguration.Builder().name("nahaoba.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}

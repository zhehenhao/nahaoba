package com.meizi.haokan.jsoup;

import com.meizi.haokan.realm.Video;

import java.util.List;

public interface FindVideolistListener {
        void onSucceed(List<Video> mList);
        void onfailed(String e);
    }

package com.meizi.haokan.jsoup;

import com.meizi.haokan.realm.Video;

public interface FindVideoListener {
    void onSucceed(Video video);
    void onFailed(String e);
}

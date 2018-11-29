package com.meizi.haokan.jsoup;

import com.meizi.haokan.model.Video;

public interface FindVideoListener {
    void onSucceed(Video video);
    void onFailed(String e);
}

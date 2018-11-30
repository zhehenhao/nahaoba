package com.meizi.haokan.listener;

import com.meizi.haokan.model.Video;

import java.util.List;

public interface FindVideolistListener {
        void onSucceed(List<Video> mList);
        void onfailed(String e);
    }

package com.meizi.haokan.listener;

import com.meizi.haokan.online.model.Onlinevideolist;

public interface FindOnlineVideoListener {
    void onSucceed(Onlinevideolist onlinevideolist);
    void onFailed(String e);
}

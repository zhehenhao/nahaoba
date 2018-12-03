package com.meizi.haokan.listener;

import com.meizi.haokan.online.model.OnlineVideo2;

import java.util.List;

public interface FindOnlineVideo2Listener {
    void onSucceed(List<OnlineVideo2> video2list);
    void onSimpleSucceed(OnlineVideo2 video2);
    void onFailed(String e);

}

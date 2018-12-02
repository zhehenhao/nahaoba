package com.meizi.haokan.listener;

import com.meizi.haokan.model.Txt;

import java.util.List;

public interface FindTxtListener {

    void onSucceed(List<Txt> txtList);
    void onSimplySucceed(Txt txt);
    void  onFailed(String e);
}

package com.meizi.haokan.listener;

import com.meizi.haokan.model.Xmovie;

import java.util.List;

public interface FindXmovieListener {
    void onSucceed(List<Xmovie> xmovies);
    void onFailed(String e);
    void onSimpleSucceed(Xmovie xmovie);

}

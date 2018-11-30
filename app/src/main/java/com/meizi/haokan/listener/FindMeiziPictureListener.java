package com.meizi.haokan.listener;

import com.meizi.haokan.model.Picture;

import java.util.List;

public  interface  FindMeiziPictureListener{
        void onSucceed(List<Picture> list);
        void onFailed(String e);
        void onSimpleSucceed(Picture picture);
    }
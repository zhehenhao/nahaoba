package com.meizi.haokan.jsoup;

import com.meizi.haokan.realm.Album;

import java.util.List;

public interface FindPicturelistListener {
   void onSucceed(List<Album> albumList);
   void onFailed(String e);

}

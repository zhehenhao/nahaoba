package com.meizi.haokan.listener;

import com.meizi.haokan.model.Album;

import java.util.List;

public interface FindAlbumListener {
   void onSucceed(List<Album> albumList);
   void onFailed(String e);
   void onSimpleSucceed(Album album);

}

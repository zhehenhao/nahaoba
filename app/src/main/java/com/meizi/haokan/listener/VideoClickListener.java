package com.meizi.haokan.listener;

import com.meizi.haokan.model.Video;

public interface VideoClickListener {
    void  onItemClick(int position, Video video);
    void  onItemLongClick(int position,Video video);
}

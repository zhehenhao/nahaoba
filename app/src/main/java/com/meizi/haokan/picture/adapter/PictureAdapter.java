package com.meizi.haokan.picture.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.model.Picture;
import com.meizi.haokan.utils.ImageLoaderUtils;


import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder>{


    private List<Picture> pictures=new ArrayList<>();
    private Context mcontext;

    public PictureAdapter(Context context, List<Picture> list ){
        this.mcontext=context;
        pictures=list;
    }


    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PictureViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_meizitu,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, final int position) {
      final Picture picture=pictures.get(position);
        ImageLoaderUtils.displaymeizitu(mcontext,holder.imageView,picture.getImg(),picture.getReferer());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("点击了"+position);
           showpicture(position,picture.getTitle());
            }
        });

    }

    private void showpicture(int position,String title) {

        ImageInfo imageInfo;
        final List<ImageInfo> imageInfoList = new ArrayList<>();
        for (Picture picture :pictures) {
            imageInfo = new ImageInfo();
            // 原图地址（必填）
            imageInfo.setOriginUrl(picture.getImg());
            // 缩略图地址（必填）
            // 如果没有缩略图url，可以将两项设置为一样。（注意：此处作为演示用，加了-1200，你们不要这么做）
            imageInfo.setThumbnailUrl(picture.getImg());
            imageInfoList.add(imageInfo);
            imageInfo = null;
        }
        ImagePreview
                .getInstance()
                .setContext(mcontext)
                .setIndex(position)
                .setImageInfoList(imageInfoList)
                .setShowDownButton(true)
                .setLoadStrategy(ImagePreview.LoadStrategy.Default)
                .setFolderName(title)
                .setScaleLevel(1, 3, 8)
                .setZoomTransitionDuration(500)

                .setEnableClickClose(true)// 是否启用点击图片关闭。默认启用
                .setEnableDragClose(true)// 是否启用上拉/下拉关闭。默认不启用

                .setShowCloseButton(true)// 是否显示关闭页面按钮，在页面左下角。默认不显示
                .setCloseIconResId(R.drawable.ic_action_close)// 设置关闭按钮图片资源，可不填，默认为：R.drawable.ic_action_close

                .setShowDownButton(true)// 是否显示下载按钮，在页面右下角。默认显示
                .setDownIconResId(R.drawable.icon_download_new)// 设置下载按钮图片资源，可不填，默认为：R.drawable.icon_download_new

                .setShowIndicator(true)// 设置是否显示顶部的指示器（1/9）。默认显示
                .start();

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{
       public ImageView imageView;
        public PictureViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_meizitu);
        }
    }
}

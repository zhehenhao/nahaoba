package com.meizi.haokan.picture.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizi.haokan.R;


public class AlbumViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView name;
    public AlbumViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.iv_image);
        name=itemView.findViewById(R.id.iv_name);

    }
}

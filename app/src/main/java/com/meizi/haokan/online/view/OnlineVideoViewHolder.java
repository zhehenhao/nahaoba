package com.meizi.haokan.online.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.meizi.haokan.R;

public class OnlineVideoViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView title;
    public OnlineVideoViewHolder(View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.on_image);
        title=itemView.findViewById(R.id.on_name);

    }
}

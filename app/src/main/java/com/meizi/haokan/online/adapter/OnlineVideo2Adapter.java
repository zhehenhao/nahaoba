package com.meizi.haokan.online.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.OnlineVideoContent2Activity;
import com.meizi.haokan.online.view.OnlineVideoViewHolder;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class OnlineVideo2Adapter extends RecyclerView.Adapter<OnlineVideoViewHolder> {
 private List<Video>  videoList=new ArrayList<>();
 private Context context;
 public  OnlineVideo2Adapter(Context context,List<Video> videos){
     this.context=context;
     this.videoList=videos;
 }

    @NonNull
    @Override
    public OnlineVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view =LayoutInflater.from(context).inflate(R.layout.item_onlinevideo,parent,false);
        return new OnlineVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineVideoViewHolder holder, int position) {
     final Video video=videoList.get(position);
     holder.title.setText(video.getName());
        ImageLoaderUtils.display(context,holder.imageView,video.getImage());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnlineVideoContent2Activity.strart(context,video.getName(),video.getPlayurl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}

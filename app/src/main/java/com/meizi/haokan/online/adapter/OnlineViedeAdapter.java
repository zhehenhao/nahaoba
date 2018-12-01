package com.meizi.haokan.online.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.listener.VideoClickListener;
import com.meizi.haokan.model.Video;
import com.meizi.haokan.online.view.OnlineVideoViewHolder;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class OnlineViedeAdapter extends RecyclerView.Adapter<OnlineVideoViewHolder> {
    private List<Video> videoList=new ArrayList<>();
    private Context context;
    private VideoClickListener listener;

    public OnlineViedeAdapter(Context context,List<Video> list){
        this.context=context;
        videoList=list;

    }

    @NonNull
    @Override
    public OnlineVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=  LayoutInflater.from(context).inflate(R.layout.item_onlinevideo,parent,false);
        return new OnlineVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineVideoViewHolder holder, final int position) {
        final Video video=videoList.get(position);
        ImageLoaderUtils.display(context,holder.imageView,video.getImage());
        holder.title.setText(video.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position,video);
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemLongClick(position,video);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public void setListener(VideoClickListener listener) {
        this.listener = listener;
    }
}

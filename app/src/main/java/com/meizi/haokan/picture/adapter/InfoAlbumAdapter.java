package com.meizi.haokan.picture.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.model.Picture;
import com.meizi.haokan.picture.view.AlbumViewHolder;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoAlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private List<Picture> pictureList=new ArrayList<>();
    private Context mcontext;

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_album,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Picture picture=pictureList.get(position);
        ImageLoaderUtils.display(mcontext,holder.imageView,picture.getImg());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mcontext.startActivity();
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }
}

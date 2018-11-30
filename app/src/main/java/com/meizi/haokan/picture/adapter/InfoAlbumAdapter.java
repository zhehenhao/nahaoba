package com.meizi.haokan.picture.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.model.Album;
import com.meizi.haokan.model.Picture;
import com.meizi.haokan.picture.InfoPictureActivity;
import com.meizi.haokan.picture.view.AlbumViewHolder;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoAlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {

    private List<Album> albumList=new ArrayList<>();
    private Context mcontext;
     public  InfoAlbumAdapter(Context context, List<Album> list){
         this.mcontext=context;
         this.albumList=list;
     }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_album,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        final Album album=albumList.get(position);
        ImageLoaderUtils.display(mcontext,holder.imageView,album.getImg());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
         mcontext.startActivity(InfoPictureActivity.newIntent(mcontext,album.getName(),album.getContenturl()));
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
        return albumList.size();
    }
}

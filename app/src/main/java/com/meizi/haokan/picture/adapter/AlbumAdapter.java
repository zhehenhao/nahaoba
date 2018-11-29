package com.meizi.haokan.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.picture.view.AlbumViewHolder;
import com.meizi.haokan.model.Album;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private List<Album> malbumList;
    private Context mcontext;
    private LayoutInflater minflater;
    private  onItemClickListener onItemClickListener;
    public  AlbumAdapter(Context context,List<Album> albumList){
//        LogUtils.e("构造adapter");
        this.mcontext=context;
        this.malbumList=albumList;
        this.minflater = LayoutInflater.from(context);

    }
//    public void clearData(){
//        this.malbumList.clear();
//        notifyDataSetChanged();
//    }
//    public  void setNewData(List<Album> data){
//        this.malbumList=data;
//        notifyDataSetChanged();
//
//    }
//
//
//    public List<Album> getData(){
//        return malbumList;
//    }
//
//
//    public void  addData(int position,List<Album> data){
//        this.malbumList.addAll(position,data);
//        this.notifyItemChanged(position,data.size());
//    }
//    public  void addData(List<Album> data){
//        this.malbumList.addAll(data);
//        this.notifyDataSetChanged();
//    }



    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LogUtils.e("过滤视图");
        View view=minflater.inflate(R.layout.item_album,parent,false);
        AlbumViewHolder viewHolder=new AlbumViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
//        LogUtils.e("绑定视图");
        final Album album=malbumList.get(position);
//        LogUtils.e("设置"+album.getName());
        holder.name.setText(album.getName());
        ImageLoaderUtils.displaymeizitu(mcontext,holder.imageView,album.getImg());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return malbumList.size();
    }

 public  interface  onItemClickListener{
        void onItemClick(int position,Album album);
 }

    public void setOnItemClickListener(AlbumAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

package com.meizi.haokan.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.model.Xmovie;
import com.meizi.haokan.movie.view.XmovieViewHolder;

import java.util.List;

public class XmovieAdapter extends RecyclerView.Adapter<XmovieViewHolder> {

    private List<Xmovie> xmovieList;
    private Context context;
    public XmovieAdapter(Context context, List<Xmovie> xmovieList){
        this.context=context;
        this.xmovieList=xmovieList;

    }


    @NonNull
    @Override
    public XmovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_xmovie,parent,false);
        return new XmovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XmovieViewHolder holder, int position) {
        Xmovie xmovie=xmovieList.get(position);
        holder.title.setText(xmovie.getTitle());
        holder.describe.setText(xmovie.getDescribe());
        holder.xmoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return xmovieList.size();
    }
}

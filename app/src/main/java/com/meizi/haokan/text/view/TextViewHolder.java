package com.meizi.haokan.text.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meizi.haokan.R;

public class TextViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout textline;
    public TextView title;
    public  TextView pulishtime;
    public  TextView describe;
    public TextViewHolder(View itemView) {
        super(itemView);
       textline=itemView.findViewById(R.id.textline);
       title=itemView.findViewById(R.id.tv_title);
       pulishtime=itemView.findViewById(R.id.tv_pulishtime);
       describe=itemView.findViewById(R.id.tv_describe);
    }
}

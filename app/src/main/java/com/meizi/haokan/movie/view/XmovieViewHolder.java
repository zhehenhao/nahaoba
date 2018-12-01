package com.meizi.haokan.movie.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meizi.haokan.R;

public class XmovieViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView describe;
    public LinearLayout xmoview;

    public XmovieViewHolder(View itemView) {
        super(itemView);
        xmoview=itemView.findViewById(R.id.itemxmovielin);
        title=itemView.findViewById(R.id.xmovietitle);
        describe=itemView.findViewById(R.id.xmoviedescribe);

    }
}

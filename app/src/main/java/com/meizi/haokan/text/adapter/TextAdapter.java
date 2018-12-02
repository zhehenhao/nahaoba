package com.meizi.haokan.text.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meizi.haokan.R;
import com.meizi.haokan.model.Txt;
import com.meizi.haokan.text.TxtContentActivity;
import com.meizi.haokan.text.view.TextViewHolder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class TextAdapter extends RecyclerView.Adapter<TextViewHolder> {
    private List<Txt> textlist=new ArrayList<>();
    private Context context;
    public  TextAdapter(Context context,List<Txt> textlist){
        this.context=context;
        this.textlist=textlist;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_text,parent,false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        final Txt txt=textlist.get(position);
        holder.title.setText(txt.getTitle());
        holder.pulishtime.setText(txt.getUpdatatime());
        holder.describe.setText(txt.getIntroduction());

        holder.textline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxtContentActivity.startReadTxt(context,txt.getContenturl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return textlist.size();
    }
}

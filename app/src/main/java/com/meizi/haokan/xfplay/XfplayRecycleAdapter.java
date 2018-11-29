package com.meizi.haokan.xfplay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.meizi.haokan.R;
import com.meizi.haokan.realm.Video;
import com.meizi.haokan.utils.ImageLoaderUtils;

import java.util.List;


/**
 * Created by woshishui on 2018/3/11.
 */

public class XfplayRecycleAdapter extends RecyclerView.Adapter<XfplayRecycleAdapter.xfplayItemViewHolder> {
    private List<Video> mList;
    private Context mContext;
    private LayoutInflater inflater;
    private  onItemClickListener onItemClickListener;
    private  onLongClickListener  onLongClickListener;
    public XfplayRecycleAdapter(Context mcontext, List<Video> videoList){
        LogUtils.eTag("初始化先锋适配器", "XfplayRecycleAdapter: ");
        this.mList=videoList;
        this.mContext =mcontext;
        this.inflater= LayoutInflater.from(mContext);
    }
    @Override
    public xfplayItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return  new xfplayItemViewHolder(inflater.inflate(R.layout.item_xf_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(xfplayItemViewHolder holder, int position) {
        xfplayItemViewHolder xfplayItemViewHolder = (XfplayRecycleAdapter.xfplayItemViewHolder) holder;
        LogUtils.e("先锋列表绑定", "position="+position);
        final int po=position;
        final Video   xfmovie         = mList.get(position);
        final String xfname               =xfmovie.getName();
        final String xfimage              =xfmovie.getImage();
        final String xiangqingye          =xfmovie.getXiangqingurl();
        xfplayItemViewHolder.XFtextview.setText(xfname);
        ImageLoaderUtils.display(mContext,xfplayItemViewHolder.XFimg,xfimage);
        xfplayItemViewHolder.xfLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(po,xfname,xiangqingye,xfimage,xfmovie);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public   class  xfplayItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView XFimg;
        private TextView XFtextview;
        private LinearLayout xfLayout;

        public xfplayItemViewHolder(View itemView) {
            super(itemView);
            XFimg= (ImageView) itemView.findViewById(R.id.xf_image);
            XFtextview= (TextView) itemView.findViewById(R.id.xf_name);
            xfLayout= (LinearLayout) itemView.findViewById(R.id.xf_layout);
            LogUtils.eTag("视图绑定", "xfplayItemViewHolder:成功 ");

        }

    }
    public interface  onItemClickListener{
        void onItemClick(int position,String name,String xiangqingurl,String img, Video video);
    }
    public  void setOnItemClickListener(onItemClickListener listener){
    this.onItemClickListener=listener;
    }
    public interface  onLongClickListener{
        void onLongClick(int position,String name,String xiangqiangurl,String img,Video  video);
    }

    public void setOnLongClickListener(XfplayRecycleAdapter.onLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}

package com.meizi.haokan.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.meizi.haokan.Base.BaseActivity;
import com.meizi.haokan.R;

public class XmovieContentActivity extends BaseActivity {

    private String title;
    private String url;
    private String xunlei;


    public static void start(Context context,String title,String url){
        Intent intent=new Intent(context,XmovieContentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmovie_content);

    }
}

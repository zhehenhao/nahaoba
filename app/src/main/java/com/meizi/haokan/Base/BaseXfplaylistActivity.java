package com.meizi.haokan.Base;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.meizi.haokan.R;


public abstract class BaseXfplaylistActivity extends BaseActivity {


    public  void showpagedialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(this).inflate(R.layout.gotopage_view, null);
// 设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);
//这个位置十分重要，只有位于这个位置逻辑才是正确的
        final AlertDialog dialog = builder.show();
        final EditText etpage = view.findViewById(R.id.etpage);

        view.findViewById(R.id.lastpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确认
                lastpage();

            }
        });
        view.findViewById(R.id.nextpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //立即启动+关闭对话框
                nextpage();
                //写相关的服务代码

            }
        });
        view.findViewById(R.id.gotopage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消+关闭对话框
                gotopage(Integer.parseInt(etpage.getText().toString()));


            }
        });

        view.findViewById(R.id.quitpage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    protected abstract void gotopage(int i);

    protected abstract void nextpage();

    protected abstract void lastpage();

}

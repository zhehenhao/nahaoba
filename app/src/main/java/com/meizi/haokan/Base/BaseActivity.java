package com.meizi.haokan.Base;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;


import com.meizi.haokan.R;

public  class BaseActivity extends AppCompatActivity {

    public   void goactivity(Class clazz){
        startActivity(new Intent(this,clazz));
    }

    public void setClipText(String text){
        ClipboardManager cm= (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipData=ClipData.newPlainText(text,text);
        cm.setPrimaryClip(clipData);

    }

    public String getClipText(){
        ClipboardManager cm= (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
        String ctext=cm.getPrimaryClip().getItemAt(0).getText().toString();
        return ctext;
    }


}

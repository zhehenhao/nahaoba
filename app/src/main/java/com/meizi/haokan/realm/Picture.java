package com.meizi.haokan.realm;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class Picture  {
    private  long updatatime; //创建时间
    @Index
    private long id;   //序号
    @PrimaryKey
    private String title;  //标题
    private String img; //列表页
   private  boolean iscollect;  //收藏
    private long  lastseetime;  //上一次的查看时间

}

package com.meizi.haokan.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;

import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.Target;
import com.meizi.haokan.model.Picture;

import java.io.Serializable;
import java.util.List;

public class GirlService extends IntentService {

//    private static final String KEY_EXTRA_Picture_FROM = "from";
    private static final String KEY_EXTRA_Picture_LIST = "data";

    public GirlService() {
        super("PictureService");
    }

    public static void start(Context context, List<Picture> list) {
        Intent intent = new Intent(context, GirlService.class);
//        intent.putExtra(KEY_EXTRA_Picture_FROM, from);
        intent.putExtra(KEY_EXTRA_Picture_LIST, (Serializable) list);
        context.startService(intent);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, GirlService.class));
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        String from = intent.getStringExtra(KEY_EXTRA_Picture_FROM);
        List<Picture> Pictures = (List<Picture>) intent.getSerializableExtra(KEY_EXTRA_Picture_LIST);
        for (final Picture Picture : Pictures) {
            Bitmap bitmap = null;
            if (!TextUtils.isEmpty(Picture.getRefer())) {
                GlideUrl glideUrl = new GlideUrl(Picture.getImg(), new LazyHeaders.Builder()
                        .addHeader("Referer", Picture.getRefer())
                        .build());
                try {
                    bitmap = Glide.with(GirlService.this)
                            .load(glideUrl)
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    bitmap = Glide.with(GirlService.this)
                            .load(Picture.getImg())
                            .asBitmap()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//            if (bitmap != null) {
//                Picture.setHeight(bitmap.getHeight());
//                Picture.setWidth(bitmap.getWidth());
//            }
//            EventBus.getDefault().post(new PicturesComingEvent(from, Picture));
        }
    }
}

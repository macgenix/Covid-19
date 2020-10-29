package cg.mokano.bzv.covid.services.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.storage.StorageReference;

public class GlideAppli {
    public static void glideWeb(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        if (url != null && url.length()>0){
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    public static void glideWebResize(Context context, String url, ImageView imageView){
        RequestOptions options = new RequestOptions()
                .override(600, 200)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        if (url != null && url.length()>0){
            Glide.with(context).load(url).apply(options).into(imageView);
        }
    }

    public static void glideWeb(Context context, String url, ImageView imageView, int holder){
        if (url != null && url.length()>0){
            Glide.with(context).load(url).apply(new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(holder)).into(imageView);
        }
    }


    public static void glideLocal(Context context, int url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }



}


package com.example.kingwen.imagetest.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kingwen.imagetest.Beans.Myitem;
import com.example.kingwen.imagetest.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kingwen on 2016/5/4.
 */
public class ItemAdapter extends ArrayAdapter<Myitem> {

    private  int resourceId;

    private Context mContext;

    public ItemAdapter(Context context, int resource, List<Myitem> mlist) {
        super(context, resource, mlist);

        this.resourceId=resource;

        mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Myitem item =getItem(position);
        View view;

        ViewHolder viewHolder;

        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.item_glide= (ImageView) view.findViewById(R.id.iv_glide);
            viewHolder.item_imgloader= (ImageView) view.findViewById(R.id.iv_imageloader);
            viewHolder.item_fresco= (SimpleDraweeView) view.findViewById(R.id.iv_fresco);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        //通过glide来进行显示我们的第一个图片
        Glide.with(mContext)
                .load(item.getImageId())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)

                .placeholder(R.drawable.img_loading)//图片在加载过程中先添加一个占位符，
                .error(R.drawable.img_error)
                .override(125, 80)                   //大小
                .crossFade()                        //淡入淡出
                .dontAnimate()                      //拒绝动画，无动画意思
                .into(viewHolder.item_glide);

        //通过imageloader来显示第二个图片

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)
                .showImageOnFail(R.drawable.img_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        ImageLoader.getInstance().displayImage("drawable://" + item.getImageId(), viewHolder.item_imgloader, options);

        //通过fresco来显示第三个图片
        Uri uri =Uri.parse("res://"+mContext.getPackageName()+"/" + item.getImageId());
        Log.e("ItemAdapter","fresco get image");
        viewHolder.item_fresco.setImageURI(uri);

        return view;
    }

    class  ViewHolder{
        ImageView item_glide;
        ImageView item_imgloader;
        SimpleDraweeView item_fresco;
    }
}

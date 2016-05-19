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
        /**
         *  通过glide来进行显示我们的第一个图片
         *  gilde库可以加载的uri有
         *  1  资源id  R.drable.resouseID
         *  2  文件路径
         *  File file = new File(Environment.getExternalStoragePublicDirectory
         *            (Environment.DIRECTORY_PICTURES), "Running.jpg");
         *     或者通过intent进行图片的选择的时候保留图片路径，然后进行选择
         *  3  通过 uri 加载
         */

        Glide.with(mContext)
                .load(item.getUri())
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)

                .placeholder(R.drawable.img_loading)//图片在加载过程中先添加一个占位符，
                .error(R.drawable.img_error)
                .override(125, 80)                   //大小
                .crossFade()                        //淡入淡出
                .dontAnimate()                      //拒绝动画，无动画意思
                .into(viewHolder.item_glide);

        /**
         * 通过imageloader来显示第二个图片
         * "http://site.com/image.png" // from Web
           "file:///mnt/sdcard/image.png" // from SD card
           "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
           "content://media/external/images/media/13" // from content provider
           "content://media/external/video/media/13" // from content provider (video thumbnail)
           "assets://image.png" // from assets
         */


        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_loading)
                .showImageOnFail(R.drawable.img_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();


        ImageLoader.getInstance().displayImage(item.getUri(), viewHolder.item_imgloader, options);


        /**通过fresco来显示第三个图片
         * fresco仅仅支持绝对路径，并且要带上该uri路径的scheme
         * 支持uris包括               Scheme             示例
         * 1）远程图片（也就是网络图片） http://******    httpURLConnection
         * 2)本地图片                 file://******     FileInputStream
         * 3)content providre       content://****     ContentResolver
         * 4)asset目录下的文件        asset://****       AssetManager
         * 5)res目录下的文件          res://*****       Resources.openRawResource
         */
        Uri uri =Uri.parse(item.getUri());
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

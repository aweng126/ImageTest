package com.example.kingwen.imagetest.Utils;

import android.content.Context;
import android.net.Uri;

/**
 * Created by kingwen on 2016/5/18.
 */
public class FormatUtils {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    /**
     * 图片加载的时候将资源id转化为uri
     * @param context 上下文
     * @param resourceId 资源id
     * @return  资源id对应的uri
     */
    public  static  Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


}

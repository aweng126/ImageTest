package com.example.kingwen.imagetest.Beans;

import android.net.Uri;

public class  MyItem2 extends Myitem{
    private Uri UriImg;
    public MyItem2 (Uri uriImg) {

        UriImg = uriImg;
    }
    public Uri getUriImg() {
        return UriImg;
    }

    public void setUriImg(Uri uriImg) {
        UriImg = uriImg;
    }

    public String getUri(){
        return UriImg+"";
    }

}

package com.example.insura;

import android.graphics.Bitmap;


public class ClassListItems {

    public String url;
    public Bitmap img;

    public ClassListItems(String url, Bitmap img){
        this.url=url;
        this.img=img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}

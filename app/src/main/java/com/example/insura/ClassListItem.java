package com.example.insura;

import android.graphics.Bitmap;


public class ClassListItem {
    public String name;
    public String url;
    public Bitmap img;

    public ClassListItem(String name, String url, Bitmap img){
        this.name=name;
        this.url=url;
        this.img=img;
    }
    public ClassListItem(String name, String url){
        this.name=name;
        this.url=url;
        img=null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

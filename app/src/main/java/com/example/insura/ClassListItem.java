package com.example.insura;


public class ClassListItem {
    private int time;
    private String name;
    private String url;
    private String imgUrl;
    private String imgBase64;
    
    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public ClassListItem(int time, String name, String url, String imgUrl){
        this.time=time;
        this.name=name;
        this.url=url;
        this.imgUrl=imgUrl;
    }

    public ClassListItem(int time, String name, String url, String imgUrl, String imgBase64) {
        this.time = time;
        this.name = name;
        this.url = url;
        this.imgUrl = imgUrl;
        this.imgBase64 = imgBase64;
    }

    public ClassListItem(String name, String url){
        this.name=name;
        this.url=url;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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


}

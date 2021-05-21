package com.example.insura;

public class Product {
    private String name;
    private String url;
    private String imgUrl;
    private String imgBase64;

    public Product(String name, String url, String imgUrl, String imgBase64) {
        this.name = name;
        this.url = url;
        this.imgUrl = imgUrl;
        this.imgBase64 = imgBase64;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

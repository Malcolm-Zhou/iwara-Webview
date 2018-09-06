package com.example.mzhou086.androidtest.bean;

public class VideoInfo {
    private String address;
    private String title;
    private String pageNum;
    private String imgSrc;
    private String like;
    private String view;
    private String rate;

    public VideoInfo() {
    }

    public VideoInfo(String address, String title, String pageNum, String imgSrc, String like, String view, String rate) {
        this.address = address;
        this.title = title;
        this.pageNum = pageNum;
        this.imgSrc = imgSrc;
        this.like = like;
        this.view = view;
        this.rate = rate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}

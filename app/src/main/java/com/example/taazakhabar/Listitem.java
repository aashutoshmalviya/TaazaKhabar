package com.example.taazakhabar;

public class Listitem {
    String title,link,publishedAt,source,imgUrl;


    public Listitem(String title, String link, String publishedAt, String source) {
        this.title = title;
        this.link = link;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    public Listitem(String title, String link, String publishedAt, String source, String imgUrl) {
        this.title = title;
        this.link = link;
        this.publishedAt = publishedAt;
        this.source = source;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSource() {
        return source;
    }
}

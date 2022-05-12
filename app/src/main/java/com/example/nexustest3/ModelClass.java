package com.example.nexustest3;

public class ModelClass {
    private final String title, urlToImage, description, url, author;

    public ModelClass(String title, String urlToImage, String description, String url, String author) {
        this.title = title;
        this.urlToImage = urlToImage;
        this.description = description;
        this.url = url;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

}
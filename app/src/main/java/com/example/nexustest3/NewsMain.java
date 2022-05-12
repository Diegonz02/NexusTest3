package com.example.nexustest3;

import java.util.ArrayList;

public class NewsMain {
    private final ArrayList<ModelClass> articles;

    public NewsMain(ArrayList<ModelClass> articles) {
        this.articles = articles;
    }

    public ArrayList<ModelClass> getArticles() {
        return articles;
    }

}
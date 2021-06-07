package com.cakefactory.domain;

public class Item {
    private final String id;
    private final String title;
    private final float price;

    public Item(String id, String title, float price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }
}

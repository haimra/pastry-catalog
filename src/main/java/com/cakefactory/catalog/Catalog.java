package com.cakefactory.catalog;

public interface Catalog {
    Iterable<Item> getItems();
    Item getItem(String sku);
}

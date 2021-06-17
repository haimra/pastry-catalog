package com.cakefactory.catalog;

import com.cakefactory.domain.Item;

public interface Catalog {
    Iterable<Item> getItems();
}

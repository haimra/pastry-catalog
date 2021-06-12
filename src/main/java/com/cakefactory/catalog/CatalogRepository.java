package com.cakefactory.catalog;

public interface CatalogRepository {
    Iterable<Item> findAll();
}

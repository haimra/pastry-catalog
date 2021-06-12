package com.cakefactory.catalog;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class InMemoryCatalogService implements CatalogRepository {
    private static final List<Item> items = Arrays.asList(
            new Item("All Butter Croissant", new BigDecimal("0.75")),
            new Item("Chocolate Croissant", new BigDecimal("0.95")),
            new Item( "Fresh Baguette", new BigDecimal("1.60")),
            new Item("Red Velvet", new BigDecimal("3.95")),
            new Item( "Victoria Sponge", new BigDecimal("5.45")),
            new Item( "Carrot Cake", new BigDecimal("3.45"))
    );

    @Override
    public Iterable<Item> findAll() {
        return items;
    }
}

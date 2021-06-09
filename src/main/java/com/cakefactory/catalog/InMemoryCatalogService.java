package com.cakefactory.catalog;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class InMemoryCatalogService implements Catalog {
    private static final List<Item> items = Arrays.asList(
            new Item("abcr", "All Butter Croissant", new BigDecimal("0.75")),
            new Item("ccr", "Chocolate Croissant", new BigDecimal("0.95")),
            new Item("b", "Fresh Baguette", new BigDecimal("1.60")),
            new Item("rv", "Red Velvet", new BigDecimal("3.95")),
            new Item("vs", "Victoria Sponge", new BigDecimal("5.45")),
            new Item("cc", "Carrot Cake", new BigDecimal("3.45"))
    );

    @Override
    public List<Item> listAll() {
        return items;
    }
}

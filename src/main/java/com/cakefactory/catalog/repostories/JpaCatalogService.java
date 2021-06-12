package com.cakefactory.catalog.repostories;

import com.cakefactory.catalog.Catalog;
import com.cakefactory.catalog.Item;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
class JpaCatalogService implements Catalog {

    private final ItemRepository itemRepository;

    JpaCatalogService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Iterable<Item> getItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .map(entity -> new Item(entity.title, entity.price))
                .collect(Collectors.toList());
    }

}

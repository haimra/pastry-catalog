package com.cakefactory.catalog.repostories;


import com.cakefactory.catalog.Item;
import org.springframework.data.repository.CrudRepository;

public interface JpaCatalogRepository extends CrudRepository<Item, Long> {
}

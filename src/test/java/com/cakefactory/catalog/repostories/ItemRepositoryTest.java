package com.cakefactory.catalog.repostories;

import com.cakefactory.catalog.Catalog;
import com.cakefactory.catalog.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    private Catalog catalog;

    @BeforeEach
    void beforeEach(){
         catalog = new JpaCatalogService(itemRepository);
    }

    @Test
    void findAll() {
        final String title = "baguette";
        final BigDecimal price = new BigDecimal("9.97");
        ItemEntity item = new ItemEntity();
        item.id = 1L;
        item.price = price;
        item.title = title;
        itemRepository.save(item);

        final Iterable<Item> all = catalog.getItems();
        final Item itemRead = all.iterator().next();
        assertThat(itemRead).isNotNull();
        assertThat(itemRead.getPrice()).isEqualTo(price);
        assertThat(itemRead.getTitle()).isEqualTo(title);
    }
}
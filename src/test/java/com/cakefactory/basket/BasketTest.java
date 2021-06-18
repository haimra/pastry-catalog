package com.cakefactory.basket;


import com.cakefactory.catalog.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BasketTest {
    @Test
    void addOneItem(){
        Basket basket = new Basket();
        basket.addItem(new Item("sku-1","title-1",new BigDecimal("0.1")));
        assertThat(1).isEqualTo(basket.getBasketTotal());
    }

    @Test
    void addSameItemTwice(){
        Basket basket = new Basket();
        final var item = new Item("sku-1", "title-1", new BigDecimal("0.1"));

        basket.addItem(item);
        assertThat(1).isEqualTo(basket.getBasketTotal());
        basket.addItem(item);
        assertThat(2).isEqualTo(basket.getBasketTotal());
    }
}

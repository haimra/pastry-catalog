package com.cakefactory.basket;


import com.cakefactory.catalog.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BasketTest {
    @Test
    void addOneItem() {
        Basket basket = new Basket();
        basket.addItem(new Item("sku-1", "title-1", new BigDecimal("0.1")));
        assertThat(1).isEqualTo(basket.getBasketTotal());
    }

    @Test
    void addSameItemTwice() {
        Basket basket = new Basket();
        final var item = new Item("sku-1", "title-1", new BigDecimal("0.1"));

        basket.addItem(item);
        assertThat(1).isEqualTo(basket.getBasketTotal());
        var itemBySku = findItemBySku(basket, item);
        assertThat(itemBySku.getTotal()).isEqualTo(new BigDecimal("0.1"));
        basket.addItem(item);
        assertThat(2).isEqualTo(basket.getBasketTotal());
        assertThat(1).isEqualTo(basket.getItems().size());

        itemBySku = findItemBySku(basket, item);
        assertThat(itemBySku.getTotal()).isEqualTo(new BigDecimal("0.2"));
    }

    @Test
    void addTwoDifferentItems() {
        Basket basket = new Basket();
        final var item1 = new Item("sku-1", "title-1", new BigDecimal("0.1"));
        final var item2 = new Item("sku-2", "title-1", new BigDecimal("0.1"));
        basket.addItem(item1);
        assertThat(basket.getBasketTotal()).isEqualTo(1);
        final var itemBySku = findItemBySku(basket, item1);
        assertThat(itemBySku.getTotal()).isEqualTo(new BigDecimal("0.1"));

        basket.addItem(item2);
        assertThat(basket.getBasketTotal()).isEqualTo(2);
        assertThat(basket.getItems().size()).isEqualTo(2);
    }


    @Test
    void addItemAndRemove() {
        Basket basket = new Basket();
        final var item = new Item("sku-1", "title-1", new BigDecimal("0.1"));

        basket.addItem(item);
        assertThat(basket.getBasketTotal()).isEqualTo(1);
        basket.removeItem(item);
        assertThat(basket.getBasketTotal()).isZero();
        assertThat(basket.getItems().isEmpty()).isTrue();
    }

    private BasketItem findItemBySku(Basket basket, Item item1) {
        return basket.getItems().stream()
                .filter(item -> item1.getSku().equals("sku-1"))
                .findFirst()
                .get();
    }
}

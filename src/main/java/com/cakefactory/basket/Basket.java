package com.cakefactory.basket;

import com.cakefactory.catalog.Item;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@SessionScope
public class Basket {
    private Map<String, BasketItem> items = new HashMap<>();

    public int getBasketTotal() {
        return items.values()
                .stream()
                .mapToInt(BasketItem::getCount)
                .sum();
    }

    public void addItem(Item item) {
        var basketItem = items.getOrDefault(item.getSku(), new BasketItem(item));
        basketItem.incrementCount();
        items.put(item.getSku(),basketItem);
    }

    public Collection<BasketItem> getItems() {
        return items.values();
    }
}

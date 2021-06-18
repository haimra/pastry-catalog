package com.cakefactory.basket;

import com.cakefactory.catalog.Item;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@SessionScope
public class Basket {
    private Map<Item, Integer> items = new HashMap<>();

    public int getBasketTotal() {
        return items.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public void addItem(Item item) {
        var count = items.getOrDefault(item, 0);
        items.put(item, ++count);
    }
}

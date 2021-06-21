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
                .mapToInt(BasketItem::getQty)
                .sum();
    }

    public void addItem(Item item) {
        var basketItem = items.getOrDefault(item.getSku(), new BasketItem(item));
        basketItem.incrementQty();
        items.put(item.getSku(),basketItem);
    }

    public void removeItem(Item item){
        final String sku = item.getSku();
        var basketItem = items.get(sku);
        basketItem.decrementQty();
        if(basketItem.getQty() == 0){
            items.remove(sku);
        }
    }

    public Collection<BasketItem> getItems() {
        return items.values();
    }
}

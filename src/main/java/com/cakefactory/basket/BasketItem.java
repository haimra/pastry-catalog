package com.cakefactory.basket;

import com.cakefactory.catalog.Item;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BasketItem {
    @NonNull
    private Item item;
    private int count;

    void incrementCount() {
        count++;
    }

    void decrementCount() {
        if (count > 0) {
            count--;
            return;
        }
        throw new IllegalStateException("Can't have negative count of items");
    }

    public String getTitle() {
        return item.getTitle();
    }
}

package com.cakefactory.basket;

import com.cakefactory.catalog.Item;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class BasketItem {
    @NonNull
    private Item item;
    private int qty;
    private BigDecimal total;

    void incrementQty() {
        qty++;
        updateTotal();
    }

    void decrementQty() {
        if (qty > 0) {
            qty--;
            updateTotal();
            return;
        }
        throw new IllegalStateException("Can't have negative count of items");
    }

    private void updateTotal() {
        total = item.getPrice()
                .multiply(BigDecimal.valueOf(qty));
    }

    public String getTitle() {
        return item.getTitle();
    }

    public String getSku() {
        return item.getSku();
    }
}

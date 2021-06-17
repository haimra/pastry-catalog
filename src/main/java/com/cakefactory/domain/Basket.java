package com.cakefactory.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@SessionScope
public class Basket {
    private List<Item> items = new ArrayList<>();

    public int getBasketTotal() {
        return items.size();
    }

    public void addItem(Item item) {
        items.add(item);
    }
}

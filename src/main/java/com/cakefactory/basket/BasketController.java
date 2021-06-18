package com.cakefactory.basket;

import com.cakefactory.catalog.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private static final String BASKET_TOTAL = "basketTotal";
    private final Basket basket;

    public BasketController(Basket basket) {
        this.basket = basket;
    }

    @PostMapping
    public ModelAndView addToBasket(Map<String, Object> model, String sku) {
        //At the moment we only need to update the count, so lets
        //add a fake item
        basket.addItem(new Item(sku,"t",new BigDecimal("99.9")));
        model.put(BASKET_TOTAL,basket.getBasketTotal());
        return new ModelAndView("redirect:/", model);
    }
}

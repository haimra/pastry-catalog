package com.cakefactory.basket;

import com.cakefactory.catalog.Catalog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/basket")
public class BasketController {
    private static final String BASKET_ATTRIBUTE = "basket";
    private final Basket basket;
    private final Catalog catalog;

    public BasketController(Basket basket, Catalog catalog) {
        this.basket = basket;
        this.catalog = catalog;
    }

    @PostMapping
    public ModelAndView addToBasket(Map<String, Object> model, String sku) {
        final var item = catalog.getItem(sku);
        basket.addItem(item);
        model.put(BASKET_ATTRIBUTE, basket);
        return new ModelAndView("redirect:/", model);
    }

    @GetMapping
    public ModelAndView checkout(Map<String, Object> model) {
        model.put(BASKET_ATTRIBUTE, basket);
        return new ModelAndView("basket");
    }

    @PostMapping("delete")
    public ModelAndView delete(Map<String, Object> model, String sku) {
        final var item = catalog.getItem(sku);
        basket.removeItem(item);
        model.put(BASKET_ATTRIBUTE, basket);
        return new ModelAndView("redirect:/basket");
    }
}

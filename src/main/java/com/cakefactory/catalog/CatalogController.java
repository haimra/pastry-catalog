package com.cakefactory.catalog;

import com.cakefactory.basket.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private final Catalog catalog;
    private final Basket basket;

    @Autowired
    CatalogController(Catalog catalog, Basket basket) {
        this.catalog = catalog;
        this.basket = basket;
    }

    @GetMapping("/")
    public ModelAndView catalog(Map<String, Object> model) {
        model.put("items", catalog.getItems());
        model.put("basketTotal", basket.getBasketTotal());
        return new ModelAndView("catalog", model);
    }
}

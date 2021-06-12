package com.cakefactory.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CatalogController {

    private final CatalogRepository catalog;

    @Autowired
    CatalogController(CatalogRepository catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/")
    public ModelAndView catalog(Map<String, Object> model) {
        model.put("items", catalog.findAll());
        return new ModelAndView("catalog", model);
    }
}

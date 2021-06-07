package com.cakefactory;

import com.cakefactory.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CatalogController {
    @GetMapping("/catalog")
    ModelAndView catalog(Map<String, Object> model) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("1", "p1", 2.7f));
        model.put("items", items);
        return new ModelAndView("index", model);
    }
}

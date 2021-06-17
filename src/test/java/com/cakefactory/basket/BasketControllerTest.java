package com.cakefactory.basket;

import com.gargoylesoftware.htmlunit.WebClient;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = BasketController.class)
class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebClient webClient;

    @Test
    @DisplayName("index page is populated with items and the route to catalog")
    void addItemsToBasketStayInTheSession() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket", Map.of("sku", "sku-1")))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(model().attribute("basketTotal", Is.is(1)))
                .andExpect(view().name("redirect:/"));

        mockMvc.perform(MockMvcRequestBuilders.post("/basket"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(model().attribute("basketTotal", Is.is(2)))
                .andExpect(view().name("redirect:/"));
    }
}
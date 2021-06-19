package com.cakefactory.basket;

import com.cakefactory.catalog.Catalog;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = BasketController.class)
class BasketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Basket basket;
    @MockBean
    private Catalog catalog;

    @Autowired
    private WebClient webClient;

    @Test
    @DisplayName("When add to basket redirect back to catalog page")
    void addItemsToBasketStayInTheSession() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket", Map.of("sku", "sku-1")))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("When click on basket got to order page")
    void clickOnBasketMoveToOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/basket"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("order"));
    }

    @Test
    @DisplayName("When click delete on basket item, item removed")
    void removeItemFromBasket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket/remove",Map.of("sku", "sku-1")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("order"));
    }
}
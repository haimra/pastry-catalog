package com.cakefactory.basket;

import com.cakefactory.catalog.Catalog;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = BasketController.class)
class BasketControllerTest {
    @Autowired
    private WebApplicationContext context;
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
        mockMvc.perform(MockMvcRequestBuilders
                .post("/basket", Map.of("sku", "sku-1"))
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("When click on basket got to order page")
    void clickOnBasketMoveToOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/basket"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("basket"));
    }

    @Test
    @DisplayName("When click delete on basket item, item removed")
    void removeItemFromBasket() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/basket/delete",Map.of("sku", "sku-1"))
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/basket"));
    }

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
}
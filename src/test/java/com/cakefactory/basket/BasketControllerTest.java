package com.cakefactory.basket;

import com.cakefactory.catalog.Catalog;
import com.cakefactory.signup.Address;
import com.cakefactory.signup.AddressService;
import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
    @MockBean
    private AddressService addressService;
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
        mockMvc.perform(MockMvcRequestBuilders.post("/basket/delete", Map.of("sku", "sku-1"))
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

    @Nested
    class AuthenticatedBasketControllerTest {
        @Test
        @DisplayName("When click on basket got to order page with login user")
        void clickOnBasketMoveToOrderLogin() throws Exception {
            when(addressService.findByEmail(anyString()))
                    .thenReturn(
                            Address.builder()
                                    .addressLine1("Line1")
                                    .addressLine2("Line1")
                                    .postcode("p c").build()
                    );
            mockMvc.perform(MockMvcRequestBuilders.get("/basket"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(view().name("basket"))
                    .andExpect(model().attribute("address", hasProperty("postcode")))
                    .andExpect(model().attribute("address", hasProperty("addressLine1")))
                    .andExpect(model().attribute("address", hasProperty("addressLine2")));
        }

        @BeforeEach
        void setup() {
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(context)
                    .defaultRequest(
                            MockMvcRequestBuilders.get("/")
                                    .with(user("user@rmail.com")
                                            .roles("USER")))
                    .apply(springSecurity())
                    .build();
        }
    }
}
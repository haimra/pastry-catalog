package com.cakefactory;

import com.cakefactory.catalog.Catalog;
import com.cakefactory.catalog.CatalogController;
import com.cakefactory.catalog.Item;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = CatalogController.class)
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebClient webClient;
    @MockBean
    private Catalog catalog;

    @Test
    @DisplayName("index page is populated with items and the route to catalog")
    void itemArePopulatedAndRoutToCatalog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("items", Matchers.hasSize(6)))
                .andExpect(view().name("catalog"));
    }

    @Test
    @DisplayName("index page returns the landing page with our pastries displayed")
    void returnsLandingPage() throws Exception {
        HtmlPage page = webClient.getPage("/");
        assertThat(page.getTitleText()).startsWith("Cake Factory");
        final DomNodeList<DomNode> nodes = page.querySelectorAll(".card-body");
        assertThat(nodes.size()).isEqualTo(6);
        assertThat(nodes.stream()
                .filter(node->node.getFirstByXPath("h4/a[text()='All Butter Croissant']/form/input/value")!=null)
                .count()
        ).isEqualTo(1);
    }
    @BeforeEach
    void beforeEach() {
        Mockito.when(catalog.getItems()).thenReturn(items);
    }

    private static final List<Item> items = Arrays.asList(
            new Item("sku-1","All Butter Croissant", new BigDecimal("0.75")),
            new Item("sku-2","Chocolate Croissant", new BigDecimal("0.95")),
            new Item("sku-3","Fresh Baguette", new BigDecimal("1.60")),
            new Item("sku-4","Red Velvet", new BigDecimal("3.95")),
            new Item("sku-5","Victoria Sponge", new BigDecimal("5.45")),
            new Item("sku-6","Carrot Cake", new BigDecimal("3.45"))
    );
}
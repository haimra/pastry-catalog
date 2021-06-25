package com.cakefactory.signup;

import com.cakefactory.basket.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = SignUpController.class)
class SignUpControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Basket basket;
    @MockBean
    private RegistrationService registrationService;

    @Test
    @DisplayName("when clicking on signup form tool bar open sign up page")
    void clickSignUpAndSeeForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signup"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("signup"));
    }

    @Test
    @DisplayName("when submit the signup form save and move to home page")
    void submitFormAndGotHomePage() throws Exception {
        //TODO this is not working need a nice way to test forms
        Map<String, Object> form = Map.of(
                "emailAddress", "address@domain.com",
                "password", "secret123",
                "addressLine1", "My home address",
                "addressLine2", "Second floor",
                "postcode", "WD4 ITV");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup", form)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }
}
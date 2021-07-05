package com.cakefactory.signup;

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

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@WebMvcTest(controllers = AccountController.class)
class UnauthenticatedAccountControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;


    @Test
    @DisplayName("when clicking on account form tool bar and the user hadn't login open sign account page")
    void whenUserLogLogoutGetLoginForm() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}

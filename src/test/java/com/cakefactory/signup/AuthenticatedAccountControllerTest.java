package com.cakefactory.signup;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AccountController.class)
class AuthenticatedAccountControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;

    private final static String EMAIL_ADDRESS = "user@email.com";
    private Address address;

    @Test
    @DisplayName("when clicking on account form tool bar and the user had login open sign account page")
    void whenUserLoginGetAccountForm() throws Exception {
        when(addressService.findByEmail(EMAIL_ADDRESS)).thenReturn(
                address);

        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("account"))
                .andExpect(model().attribute("address", hasProperty("postcode")))
                .andExpect(model().attribute("address", hasProperty("addressLine1")))
                .andExpect(model().attribute("address", hasProperty("addressLine2")));
    }

    @Test
    @DisplayName("after user update his account go to home page")
    void whenUserUpdateAccountGotoHome() throws Exception {
        final byte[] form = pojToForm(address);
        mockMvc.perform(MockMvcRequestBuilders.post("/account")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(form))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(addressService).update(address, EMAIL_ADDRESS);
    }

    @BeforeEach
    void beforeEach() {
        address = Address.builder()
                .addressLine1("address Line 1")
                .addressLine2("address Line 2")
                .postcode("Post Code")
                .build();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .defaultRequest(
                        MockMvcRequestBuilders.get("/")
                                .with(user(EMAIL_ADDRESS)
                                        .roles("USER")))
                .apply(springSecurity())
                .build();
    }

    private byte[] pojToForm(Address address) throws IOException {
        return EntityUtils.toByteArray(new UrlEncodedFormEntity(Arrays.asList(
                new BasicNameValuePair("addressLine1", address.getAddressLine1()),
                new BasicNameValuePair("addressLine2", address.getAddressLine2()),
                new BasicNameValuePair("postcode", address.getPostcode())
        )));
    }
}
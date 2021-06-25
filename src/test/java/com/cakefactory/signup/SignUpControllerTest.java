package com.cakefactory.signup;

import com.cakefactory.basket.Basket;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

        final Account account = new Account("address@domain.com", "secret123");
        final var address = new Address().toBuilder()
                .addressLine1("My home address")
                .postcode("WD4 ITV").build();
        final byte[] form = pojToForm(account, address);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(form))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
        verify(registrationService).register(account, address);
    }

    @Test
    @DisplayName("when submit the signup with existing email redirect to login")
    void submitFormExistingEmailAndGotLoginPage() throws Exception {
        final Account account = new Account("address@domain.com", "secret123");
        final var address = new Address().toBuilder()
                .addressLine1("My home address")
                .postcode("WD4 ITV").build();
        final byte[] form = pojToForm(account, address);

        when(registrationService.accountExists("address@domain.com"))
                .thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(form))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"));
    }

    private byte[] pojToForm(Account account, Address address) throws IOException {
        return EntityUtils.toByteArray(new UrlEncodedFormEntity(Arrays.asList(
                new BasicNameValuePair("emailAddress", account.getEmailAddress()),
                new BasicNameValuePair("password", account.getPassword()),
                new BasicNameValuePair("addressLine1", address.getAddressLine1()),
                new BasicNameValuePair("addressLine2", address.getAddressLine2()),
                new BasicNameValuePair("postcode", address.getPostcode())
        )));
    }
}
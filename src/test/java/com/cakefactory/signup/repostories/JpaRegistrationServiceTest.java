package com.cakefactory.signup.repostories;

import com.cakefactory.signup.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class JpaRegistrationServiceTest {
    private static final String ENCODED_PASSWORD = "secret123";
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    private RegistrationService registrationService;

    @BeforeEach
    void beforeEach() {
        AccountService accountService = new JpaAccountService(accountRepository, passwordEncoder);
        AddressService addressService = new JpaAddressService(addressRepository);
        registrationService = new JpaRegistrationService(addressService, accountService);
    }

    @Test
    void successfulRegister() {
        final String emailAddress = "email@test.com";
        final var account = new Account(emailAddress, ENCODED_PASSWORD);
        final String addressLine1 = "Address Line 1";
        final String postcode = "POST";
        final var address = Address.builder()
                .addressLine1(addressLine1)
                .postcode(postcode)
                .build();
        when(passwordEncoder.encode(anyString())).thenReturn("secret123");
        registrationService.register(account, address);

        final Optional<AddressEntity> addressRepositoryById = addressRepository.findByEmailAddress(emailAddress);
        assertThat(addressRepositoryById).isPresent();
        assertThat(addressRepositoryById.get().addressLine1).isEqualTo(addressLine1);
        assertThat(addressRepositoryById.get().postcode).isEqualTo(postcode);
    }
}
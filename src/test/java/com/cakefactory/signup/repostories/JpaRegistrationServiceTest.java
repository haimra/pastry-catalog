package com.cakefactory.signup.repostories;

import com.cakefactory.signup.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class JpaRegistrationServiceTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AddressRepository addressRepository;
    private RegistrationService registrationService;

    @BeforeEach
    void beforeEach() {
        AccountService accountService = new JpaAccountService(accountRepository);
        AddressService addressService = new JpaAddressService(addressRepository);
        registrationService = new JpaRegistrationService(addressService, accountService);
    }

    @Test
    void successfulRegister() {
        final String emailAddress = "email@test.com";
        final var account = new Account(emailAddress, "secreat123");
        final String addressLine1 = "Address Line 1";
        final String postcode = "POST";
        final var address = Address.builder()
                .addressLine1(addressLine1)
                .postcode(postcode)
                .build();
        registrationService.register(account, address);

        final Optional<AddressEntity> addressRepositoryById = addressRepository.findByEmailAddress(emailAddress);
        assertThat(addressRepositoryById).isPresent();
        assertThat(addressRepositoryById.get().addressLine1).isEqualTo(addressLine1);
        assertThat(addressRepositoryById.get().postcode).isEqualTo(postcode);
    }
}
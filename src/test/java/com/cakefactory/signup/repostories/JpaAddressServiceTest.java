package com.cakefactory.signup.repostories;

import com.cakefactory.signup.Address;
import com.cakefactory.signup.AddressService;
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
class JpaAddressServiceTest {
    @Autowired
    private AddressRepository addressRepository;
    private AddressService addressService;

    @BeforeEach
    void beforeEach() {
        addressService = new JpaAddressService(addressRepository);
    }

    @Test
    void saveAddress() {

        final String addressLine1 = "address1";
        final String addressLine2 = "address2";
        final String postcode = "WD6 UFF";
        final Address address = Address.builder()
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .postcode(postcode)
                .build();
        final Long id = addressService.save(address);

        final Optional<AddressEntity> addressRepositoryById = addressRepository.findById(id);
        assertThat(addressRepositoryById).isPresent();
        assertThat(addressRepositoryById.get().addressLine1).isEqualTo(addressLine1);
        assertThat(addressRepositoryById.get().addressLine2).isEqualTo(addressLine2);
        assertThat(addressRepositoryById.get().postcode).isEqualTo(postcode);
    }

    @Test
    void saveAddressWithOutAddressLine2() {

        final String addressLine1 = "address1";
        final String postcode = "WD6 UFF";
        final Address address = Address.builder()
                .addressLine1(addressLine1)
                .postcode(postcode)
                .build();
        final Long id = addressService.save(address);

        final Optional<AddressEntity> addressRepositoryById = addressRepository.findById(id);
        assertThat(addressRepositoryById).isPresent();
        assertThat(addressRepositoryById.get().addressLine1).isEqualTo(addressLine1);
        assertThat(addressRepositoryById.get().postcode).isEqualTo(postcode);
    }
}
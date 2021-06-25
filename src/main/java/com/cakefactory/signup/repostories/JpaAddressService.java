package com.cakefactory.signup.repostories;

import com.cakefactory.signup.Address;
import com.cakefactory.signup.AddressService;
import org.springframework.stereotype.Component;

@Component
public class JpaAddressService implements AddressService {

    private final AddressRepository addressRepository;

    public JpaAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Long save(Address address,String email) {
        final var addressEntity = addressRepository.save(AddressEntity.builder(email)
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .postcode(address.getPostcode()).build());
        return addressEntity.id;
    }
}
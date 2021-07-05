package com.cakefactory.signup;


public interface AddressService {
    Long save(Address address,String email);
    void update(Address address,String email);
    Address findByEmail(String email);
}

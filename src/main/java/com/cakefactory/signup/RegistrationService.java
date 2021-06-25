package com.cakefactory.signup;

public interface RegistrationService {
    boolean accountExists(String email);
    void register(Account account, Address address);
}

package com.cakefactory.signup.repostories;

import com.cakefactory.signup.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class JpaRegistrationService implements RegistrationService {

    private final AddressService addressService;
    private final AccountService accountService;

    @Autowired
    public JpaRegistrationService(AddressService addressService, AccountService accountService) {
        this.addressService = addressService;
        this.accountService = accountService;
    }

    @Override
    public boolean accountExists(String email) {
        return accountService.exists(email);
    }

    @Override
    @Transactional
    public void register(Account account, Address address) {
        accountService.save(account);
        addressService.save(address, account.getEmailAddress());
    }
}

package com.cakefactory.signup.repostories;

import com.cakefactory.signup.Account;
import com.cakefactory.signup.AccountService;
import org.springframework.stereotype.Component;


@Component
public class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;

    public JpaAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void save(Account account) {
        accountRepository.save(new AccountEntity(account.getEmailAddress(), account.getPassword()));
    }

    @Override
    public boolean exists(String emailAddress) {
        return accountRepository
                .findByEmailAddress(emailAddress)
                .isPresent();
    }
}

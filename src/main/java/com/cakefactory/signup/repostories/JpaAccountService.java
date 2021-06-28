package com.cakefactory.signup.repostories;

import com.cakefactory.signup.Account;
import com.cakefactory.signup.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class JpaAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public JpaAccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(Account account) {
        final String password = passwordEncoder.encode(account.getPassword());
        accountRepository.save(new AccountEntity(account.getEmailAddress(), password));
    }

    @Override
    public boolean exists(String emailAddress) {
        return accountRepository
                .findByEmailAddress(emailAddress)
                .isPresent();
    }
}

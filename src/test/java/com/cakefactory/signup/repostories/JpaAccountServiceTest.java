package com.cakefactory.signup.repostories;

import com.cakefactory.signup.Account;
import com.cakefactory.signup.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class JpaAccountServiceTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private AccountService accountService;

    @BeforeEach
    void beforeEach() {
        accountService = new JpaAccountService(accountRepository,passwordEncoder);
        final Iterable<AccountEntity> all = accountRepository.findAll();
        for (AccountEntity entity : all) {
            accountRepository.delete(entity);
        }
    }

    @Test
    void saveAccount() {
        final String emailAddress = "user@domain.com";
        final String password = "secret123^";
        accountService.save(new Account(emailAddress, password));

        final Optional<AccountEntity> accountRepositoryById = accountRepository.findById(emailAddress);
        assertThat(accountRepositoryById).isPresent();
        assertThat(accountRepositoryById.get().getEmailAddress()).isEqualTo(emailAddress);
        assertThat(accountRepositoryById.get().getPassword()).isEqualTo(mockEncode(password));
    }

    @Test
    void checkAccountExist() {
        final String emailAddress = "user@domain.com";
        final String password = "secret123^";
        accountService.save(new Account(emailAddress, password));

        assertThat(accountService.exists(emailAddress)).isTrue();
        assertThat(accountService.exists("not" + emailAddress)).isFalse();
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {

            return new PasswordEncoder() {
                @Override
                public String encode(CharSequence rawPassword) {
                    return mockEncode(rawPassword);
                }

                @Override
                public boolean matches(CharSequence rawPassword, String encodedPassword) {
                    return false;
                }

                @Override
                public boolean upgradeEncoding(String encodedPassword) {
                    return PasswordEncoder.super.upgradeEncoding(encodedPassword);
                }
            };
        }
    }

    private static String mockEncode(CharSequence rawPassword) {
        return new StringBuilder(rawPassword.toString())
                .reverse().toString();
    }
}
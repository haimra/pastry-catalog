package com.cakefactory.security.repostories;

import com.cakefactory.security.User;
import com.cakefactory.signup.repostories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public JpaUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmailAddress(username)
                .map(accountEntity -> new User(accountEntity.getEmailAddress(), accountEntity.getPassword()))
                .orElseThrow(new UsernameNotFoundExceptionSuppler(username));

    }


    private static class UsernameNotFoundExceptionSuppler implements Supplier<UsernameNotFoundException> {
        private final String username;

        UsernameNotFoundExceptionSuppler(String username) {
            this.username = username;
        }

        @Override
        public UsernameNotFoundException get() {
            return new UsernameNotFoundException("User " + username + " not found");
        }
    }
}

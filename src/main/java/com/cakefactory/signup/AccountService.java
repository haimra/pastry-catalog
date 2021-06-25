package com.cakefactory.signup;

public interface AccountService {
    void save(Account account);
    boolean exists(String emailAddress);
}

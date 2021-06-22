package com.cakefactory.signup;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String emailAddress;
    private String password;
}

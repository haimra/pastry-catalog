package com.cakefactory.signup;

import lombok.*;

@Data
@Builder(toBuilder = true, access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String postcode;
}

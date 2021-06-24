package com.cakefactory.signup;

import lombok.Data;

@Data
public class SignUp {

    private Address address = new Address();
    private Account account = new Account();

    public String getEmailAddress() {
        return account.getEmailAddress();
    }

    public void setEmailAddress(String emailAddress) {
        this.account.setEmailAddress(emailAddress);
    }

    public void setPassword(String password) {
        this.account.setPassword(password);
    }

    public String getAddressLine1() {
        return address.getAddressLine1();
    }

    public void setAddressLine1(String addressLine1) {
        address.setAddressLine1(addressLine1);
    }

    public String getAddressLine2() {
        return address.getAddressLine2();
    }

    public void setAddressLine2(String addressLine2) {
        address.setAddressLine2(addressLine2);
    }

    public String getPostcode() {
        return address.getPostcode();
    }

    public void setPostcode(String postcode) {
        address.setPostcode(postcode);
    }
}

package com.cakefactory.signup.repostories;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @NotBlank
    String emailAddress;
    @NotBlank
    String password;
}

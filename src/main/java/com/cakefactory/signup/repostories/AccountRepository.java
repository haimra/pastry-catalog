package com.cakefactory.signup.repostories;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    Optional<AccountEntity> findByEmailAddress(String emailAddress);
}

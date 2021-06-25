package com.cakefactory.signup.repostories;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    Optional<AddressEntity> findByEmailAddress(String emailAddress);
}

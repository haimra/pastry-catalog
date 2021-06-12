package com.cakefactory.catalog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class JpaCatalogRepositoryTest {

    @Autowired
    private CatalogRepository repository;

    @Test
    void findAll(){
        repository.findAll();
    }
}
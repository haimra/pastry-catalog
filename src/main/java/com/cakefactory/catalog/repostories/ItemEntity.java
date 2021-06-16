package com.cakefactory.catalog.repostories;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "catalog")
public class ItemEntity {
    @Id
    @NotBlank
    String sku;
    @NotBlank
    String title;
    @NotNull
    BigDecimal price;
}


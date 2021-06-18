package com.cakefactory.catalog;

import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Item {
    @EqualsAndHashCode.Include
    private final String sku;
    private final String title;
    private final BigDecimal price;
}

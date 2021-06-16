package com.cakefactory.catalog;

import lombok.*;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Item {
    private final String sku;
    private final String title;
    private final BigDecimal price;
}

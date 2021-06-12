package com.cakefactory.catalog;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "catalog" )
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date createdAt;
    private final String title;
    private final BigDecimal price;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

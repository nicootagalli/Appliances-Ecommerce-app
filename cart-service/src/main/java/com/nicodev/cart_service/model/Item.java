package com.nicodev.cart_service.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Item {

    private Long product_id;
    private Double unit_price;
    private Integer quantity;

}

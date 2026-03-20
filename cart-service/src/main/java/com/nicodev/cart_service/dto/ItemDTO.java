package com.nicodev.cart_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ItemDTO {

    private Long product_id;
    private Double unit_price;
    private Integer quantity;

}

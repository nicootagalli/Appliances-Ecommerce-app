package com.nicodev.product_service.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductDTO {

    private Long product_id;
    private String name;
    private String brand;
    private Double unit_price;

}

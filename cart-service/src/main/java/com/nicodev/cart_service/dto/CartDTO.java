package com.nicodev.cart_service.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CartDTO {

    private Long cart_id;
    private Long user_id;
    private List<ItemDTO> items;
    private Double total;

}

package com.nicodev.sale_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class CartDTO {

    private Long cart_id;
    private Long user_id;
    private List<ItemDTO> items;
    private Double total;

}

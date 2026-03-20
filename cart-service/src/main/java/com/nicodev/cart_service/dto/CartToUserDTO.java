package com.nicodev.cart_service.dto;

import com.nicodev.cart_service.model.Item;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CartToUserDTO {

    private List<Item> items;
    private Double total;

}

package com.nicodev.cart_service.mapper;

import com.nicodev.cart_service.dto.CartDTO;
import com.nicodev.cart_service.dto.CartToUserDTO;
import com.nicodev.cart_service.dto.ItemDTO;
import com.nicodev.cart_service.exception.BadRequestException;
import com.nicodev.cart_service.model.Cart;
import com.nicodev.cart_service.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartMapper {

    public static List<ItemDTO> toItemDTO(List<Item> itemsEntity){
        if (itemsEntity == null || itemsEntity.isEmpty()){
            throw new BadRequestException("Items required");
        }

        List<ItemDTO> listDTO = new ArrayList<>();
        ItemDTO itemDTO = new ItemDTO();
        for (Item i : itemsEntity){
            itemDTO.setProduct_id(i.getProduct_id());
            itemDTO.setUnit_price(i.getUnit_price());
            itemDTO.setQuantity(i.getQuantity());

            listDTO.add(itemDTO);
        }

        return listDTO;
    }

    public static CartDTO toDTO(Cart cart){
        if (cart == null){
            throw new BadRequestException("Cart required");
        }

        return CartDTO.builder()
                .cart_id(cart.getCart_id())
                .user_id(cart.getUser_id())
                .items(CartMapper.toItemDTO(cart.getItems()))
                .total(cart.getTotal())
                .build();


    }

    public static CartToUserDTO toUserDTO(Cart cart){
        if (cart == null){
            throw new BadRequestException("Cart required");
        }

        return CartToUserDTO.builder()
                .items(cart.getItems())
                .total(cart.getTotal())
                .build();

    }

}

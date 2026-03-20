package com.nicodev.cart_service.service;

import com.nicodev.cart_service.dto.CartDTO;
import com.nicodev.cart_service.dto.CartToUserDTO;
import com.nicodev.cart_service.model.Cart;

import java.util.List;

public interface ICartService {

    // POST
    public void saveCart(Cart cart);

    // FIND ALL
    public List<Cart> findCarts();

    // FIND BY ID
    public Cart findCart(Long cart_id);

    // FIND CART DTO
    public CartDTO findCartDTO(Long cart_id);

    // FIND CART DTO TO USER
    public CartToUserDTO findCartToUser(Long cart_id);

    // EDIT CART
    public CartDTO editCart(Long cart_id, CartToUserDTO newCart);

    // DELETE CART
    public void deleteCart(Long cart_id);

    // CART EXIST
    public Boolean cartExist(Long cart_id);

}

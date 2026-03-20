package com.nicodev.cart_service.controller;

import com.nicodev.cart_service.dto.CartDTO;
import com.nicodev.cart_service.dto.CartToUserDTO;
import com.nicodev.cart_service.model.Cart;
import com.nicodev.cart_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    // POST
    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody Cart cart){
        cartService.saveCart(cart);
        return ResponseEntity.created(URI.create("/cart/save")).body("Cart created successfully");
    }

    // FIND ALL
    @GetMapping("/find")
    public ResponseEntity<List<Cart>> findCarts(){
        return ResponseEntity.ok(cartService.findCarts());
    }

    // FIND BY ID
    @GetMapping("/find/{cart_id}")
    public ResponseEntity<Cart> findCart(@PathVariable Long cart_id){
        return ResponseEntity.ok(cartService.findCart(cart_id));
    }

    // FIND CART DTO
    @GetMapping("/find/dto/{cart_id}")
    public ResponseEntity<CartDTO> findCartDTO(@PathVariable Long cart_id){
        return ResponseEntity.ok(cartService.findCartDTO(cart_id));
    }

    // FIND CART TO USER
    @GetMapping("/find/dto/user/{cart_id}")
    public ResponseEntity<CartToUserDTO> findCartToUser(@PathVariable Long cart_id){
        return ResponseEntity.ok(cartService.findCartToUser(cart_id));
    }

    // EDIT CART
    @PutMapping("/edit/{cart_id}")
    public ResponseEntity<CartDTO> editCart(@PathVariable Long cart_id,
                                            @RequestBody CartToUserDTO newCart){
        return ResponseEntity.ok(cartService.editCart(cart_id,newCart));
    }

    // DELETE
    @DeleteMapping("/delete/{cart_id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cart_id){
        cartService.deleteCart(cart_id);
        return ResponseEntity.noContent().build();
    }

    // CART EXIST
    @GetMapping("/exist/{cart_id}")
    public ResponseEntity<Boolean> cartExist(@PathVariable Long cart_id){
        return ResponseEntity.ok(cartService.cartExist(cart_id));
    }


}

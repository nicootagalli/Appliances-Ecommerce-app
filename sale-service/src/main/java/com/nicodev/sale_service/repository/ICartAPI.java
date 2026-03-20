package com.nicodev.sale_service.repository;

import com.nicodev.sale_service.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service")
public interface ICartAPI {

    // CART EXIST
    @GetMapping("cart/exist/{cart_id}")
    public Boolean cartExist(@PathVariable("cart_id") Long cart_id);

    // FIND CART DTO
    @GetMapping("cart/find/dto/{cart_id}")
    public CartDTO findCartDTO(@PathVariable("cart_id") Long cart_id);

}

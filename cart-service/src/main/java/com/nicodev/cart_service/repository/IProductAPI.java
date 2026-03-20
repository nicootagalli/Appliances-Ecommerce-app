package com.nicodev.cart_service.repository;

import com.nicodev.cart_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface IProductAPI {

    @GetMapping("products/find/dto/{product_id}")
    public ProductDTO findProductDTO(@PathVariable("product_id") Long product_id);

    @GetMapping("products/exist/{product_id}")
    public Boolean productExist(@PathVariable("product_id") Long product_id);

}

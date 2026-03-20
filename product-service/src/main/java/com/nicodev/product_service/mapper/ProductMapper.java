package com.nicodev.product_service.mapper;

import com.nicodev.product_service.dto.ProductDTO;
import com.nicodev.product_service.exception.BadRequestException;
import com.nicodev.product_service.model.Product;

public class ProductMapper {

    public static ProductDTO toDTO(Product product){
        if (product == null) {
            throw new BadRequestException("Product required");
        }

        return ProductDTO.builder()
                .product_id(product.getProduct_id())
                .name(product.getName())
                .brand(product.getBrand())
                .unit_price(product.getUnit_price())
                .build();
    }

}

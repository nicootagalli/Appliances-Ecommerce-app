package com.nicodev.sale_service.service;

import com.nicodev.sale_service.dto.CartDTO;
import com.nicodev.sale_service.repository.ICartAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceClient {

    private final ICartAPI cartAPI;

    @CircuitBreaker(name = "sale-service", fallbackMethod = "fallbackCart")
    @Retry(name = "sale-service")
    public CartDTO findCartDTO(Long cart_id){
        return cartAPI.findCartDTO(cart_id);
    }

    public CartDTO fallbackCart(Long cartId, Throwable t) {
        log.warn("Cart service down for cartId {}", cartId);

        CartDTO fallbackCartDTO = new CartDTO();
        fallbackCartDTO.setCart_id(null);
        fallbackCartDTO.setUser_id(null);
        fallbackCartDTO.setItems(null);
        fallbackCartDTO.setTotal(null);
        return fallbackCartDTO;
    }

}

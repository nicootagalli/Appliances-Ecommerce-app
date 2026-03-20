package com.nicodev.cart_service.repository;

import com.nicodev.cart_service.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface IUserAPI {

    @GetMapping("users/find/dto/{user_id}")
    public UserDTO findUserDTO(@PathVariable("user_id") Long user_id);

    @GetMapping("users/exist/{user_id}")
    public Boolean userExist(@PathVariable("user_id") Long user_id);

}
